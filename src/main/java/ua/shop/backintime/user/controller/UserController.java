package ua.shop.backintime.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ua.shop.backintime.config.jwt.UserDetailsImpl;
import ua.shop.backintime.user.controller.request.DeliveryData;
import ua.shop.backintime.user.controller.request.RestorePasswordRequest;
import ua.shop.backintime.user.controller.request.UpdateUserRequest;
import ua.shop.backintime.user.controller.response.UserResponse;
import ua.shop.backintime.user.service.UserService;
import ua.shop.backintime.user.service.exception.UserAlreadyExistException;
import ua.shop.backintime.user.service.exception.UserNotFoundException;
import ua.shop.backintime.user.service.mapper.UserMapper;

import java.security.Principal;

@Slf4j
@Tag(name = "Users", description = "User controller to manage usernames, passwords and roles")
@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest, Principal principal)
            throws UserNotFoundException, UserAlreadyExistException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
        UserDetailsImpl authentication = (UserDetailsImpl) usernamePasswordAuthenticationToken.getPrincipal();
        return ResponseEntity.ok(userMapper.toUserResponse(
                userService.updateUser(authentication.getId(), userMapper.toUpdateUserDto(updateUserRequest))));
    }
//    @PutMapping("/restorePassword")
//    public ResponseEntity<?> restorePassword(@Valid @RequestBody RestorePasswordRequest restorePasswordRequest, Principal principal){
//        return ResponseEntity.ok().build();
//    }

    @Operation(
            summary = "addDeliveryData for user",
            description = "firstly you need to login than set Authorization and add delivery data",
            tags = {"Authentication"}
    )
    @PostMapping("/addDeliveryData")
    public ResponseEntity<?> addDeliveryData(@Valid @RequestBody DeliveryData deliveryData, Principal principal)
            throws UserNotFoundException {
        return ResponseEntity.ok(userMapper.toUserResponse(
                userService.addDeliveryData(principal.getName(), deliveryData)));
    }
}
