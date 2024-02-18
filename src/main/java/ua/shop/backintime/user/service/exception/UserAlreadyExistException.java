package ua.shop.backintime.user.service.exception;

import ua.shop.backintime.user.service.dto.UpdateUserDto;
import ua.shop.backintime.user.service.dto.UserDto;

public class UserAlreadyExistException extends RuntimeException {

    private static final String USER_WITH_EMAIL_ALREADY_EXIST_MESSAGE =
            "User with email = %s already exist.";

    public UserAlreadyExistException(UserDto userDto) {
        super(String.format(USER_WITH_EMAIL_ALREADY_EXIST_MESSAGE, userDto.getEmail()));
    }

    public UserAlreadyExistException(UpdateUserDto updateUserDto) {
        super(String.format(USER_WITH_EMAIL_ALREADY_EXIST_MESSAGE, updateUserDto.getNewEmail()));
    }
}