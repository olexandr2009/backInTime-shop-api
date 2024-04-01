package ua.shop.backintime.user.service;

import ua.shop.backintime.user.controller.request.DeliveryData;
import ua.shop.backintime.user.service.dto.UpdateUserDto;
import ua.shop.backintime.user.service.dto.UserDto;
import ua.shop.backintime.user.service.exception.UserAlreadyExistException;
import ua.shop.backintime.user.service.exception.UserIncorrectPasswordException;
import ua.shop.backintime.user.service.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    void registerUser(UserDto userDto, String password);

    UserDto updateUser(Long userId, UpdateUserDto updateUserDto)
            throws UserNotFoundException, UserIncorrectPasswordException, UserAlreadyExistException;

    List<UserDto> findAll();

    UserDto findByEmail(String email);

    void setLoggout(String email);

    void login(String email, String token);

    boolean canLogin(String email, String jwt);

    void deleteUserByEmail(String email);

    void deleteAllUsers();

     UserDto addDeliveryData(String email, DeliveryData deliveryData);
}