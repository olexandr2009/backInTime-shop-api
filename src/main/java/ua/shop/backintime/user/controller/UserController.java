package ua.shop.backintime.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.shop.backintime.config.jwt.UserDetailsImpl;
import ua.shop.backintime.user.controller.request.UpdateUserRequest;
import ua.shop.backintime.user.controller.response.UserResponse;
import ua.shop.backintime.user.service.UserService;
import ua.shop.backintime.user.service.dto.UserDto;
import ua.shop.backintime.user.service.exception.UserAlreadyExistException;
import ua.shop.backintime.user.service.exception.UserIncorrectPasswordException;
import ua.shop.backintime.user.service.exception.UserNotFoundException;
import ua.shop.backintime.user.service.mapper.UserMapper;

import java.security.Principal;
import java.util.List;

@Slf4j
@Tag(name = "Users", description = "User controller to manage usernames, passwords and roles")
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Operation(
            summary = "Rename user and reset password to newer one",
            description = "Update password and username ",
            tags = {"Users"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "202",
                    description = "Username, password changed",
                    content = @Content(
                            schema = @Schema(implementation = UserDto.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            @ApiResponse(responseCode = "400",
                    description = "oldUsername not found or newUsername alreadyExists or oldPassword doesn't matches with existing"
            ),
            @ApiResponse(responseCode = "403", description = "Unauthorized authorize in Authentication login")
    })
    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest, Principal principal)
            throws UserNotFoundException, UserAlreadyExistException, UserIncorrectPasswordException {
        UserDetailsImpl authentication = (UserDetailsImpl) principal;
        return ResponseEntity.ok(userMapper.toUserResponse(
                userService.updateUser(authentication.getId(), userMapper.toUpdateUserDto(updateUserRequest))));
    }

    @Operation(
            summary = "Find all users",
            description = "Find all users",
            tags = {"Users"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Users found",
                    content = @Content(
                            schema = @Schema(implementation = List.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
    })
    @GetMapping("/find/all")
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userMapper.toUserResponses(userService.findAll()));
    }

    @Operation(
            summary = "Find by email",
            description = "Find by email",
            tags = {"Users"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "User found",
                    content = @Content(
                            schema = @Schema(implementation = List.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "User not found"
            )
    })
    @GetMapping("/find/{email}")
    public ResponseEntity<UserResponse> findByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(userMapper.toUserResponse(userService.findByEmail(email)));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
