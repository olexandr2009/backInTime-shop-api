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
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ua.shop.backintime.config.jwt.UserDetailsImpl;
import ua.shop.backintime.user.controller.request.UpdateUserRequest;
import ua.shop.backintime.user.controller.response.UserResponse;
import ua.shop.backintime.user.service.UserService;
import ua.shop.backintime.user.service.exception.UserAlreadyExistException;
import ua.shop.backintime.user.service.exception.UserIncorrectPasswordException;
import ua.shop.backintime.user.service.exception.UserNotFoundException;
import ua.shop.backintime.user.service.mapper.UserMapper;

import java.security.Principal;
import java.util.List;

@Slf4j
@Tag(name = "Users", description = "User controller to manage usernames, passwords and roles")
@RestController
@RequestMapping(path = "/api/v1/test/users")
@Profile({"test", "dev"})
public class UserTesterController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Operation(
            summary = "Find all users",
            description = "Find all users only for testers",
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
            description = "Find by email only for testers",
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
    @GetMapping("/find")
    public ResponseEntity<UserResponse> findByEmail(@RequestParam String email) {
        try {
            return ResponseEntity.ok(userMapper.toUserResponse(userService.findByEmail(email)));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Set ofline",
            description = "Set ofline for email only for testers",
            tags = {"Users"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "User found setted ofline",
                    content = @Content(
                            schema = @Schema(implementation = List.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "User not found"
            )
    })
    @PutMapping("/setOfline")
    public ResponseEntity<UserResponse> setOfline(@RequestParam String email) {
        try {
            userService.setLoggout(email);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
