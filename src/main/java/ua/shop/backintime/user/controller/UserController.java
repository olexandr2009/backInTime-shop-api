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
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping(path = "/api/v1/users")
@Profile("prod")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest, Principal principal)
            throws UserNotFoundException, UserAlreadyExistException, UserIncorrectPasswordException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
        UserDetailsImpl authentication = (UserDetailsImpl) usernamePasswordAuthenticationToken.getPrincipal();
        return ResponseEntity.ok(userMapper.toUserResponse(
                userService.updateUser(authentication.getId(), userMapper.toUpdateUserDto(updateUserRequest))));
    }
}
