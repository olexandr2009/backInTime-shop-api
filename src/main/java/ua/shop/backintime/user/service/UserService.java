package ua.shop.backintime.user.service;

import ua.shop.backintime.user.service.dto.UpdateUserDto;
import ua.shop.backintime.user.service.dto.UserDto;
import ua.shop.backintime.user.service.exception.UserAlreadyExistException;
import ua.shop.backintime.user.service.exception.UserIncorrectPasswordException;
import ua.shop.backintime.user.service.exception.UserNotFoundException;

import java.security.Principal;
import java.util.List;

public interface UserService {
    void registerUser(UserDto userDto, String password);

    UserDto updateUser(Long userId, UpdateUserDto updateUserDto)
            throws UserNotFoundException, UserIncorrectPasswordException, UserAlreadyExistException;

    void logout(Principal principal);

    List<UserDto> findAll();

    UserDto findByEmail(String email);
}