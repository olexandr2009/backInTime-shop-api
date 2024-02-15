package ua.shop.backintime.user.service.exception;

import ua.shop.backintime.user.service.dto.UpdateUserDto;
import ua.shop.backintime.user.service.dto.UserDto;

public class UserAlreadyExistException extends Throwable {

    private static final String USER_ALREADY_EXIST_WITH_EMAIL_AND_FIRSTNAME_EXCEPTION_TEXT =
            "User with id = %d and email = %s already exist.";

    public UserAlreadyExistException(UserDto userDto) {
        super(String.format(USER_ALREADY_EXIST_WITH_EMAIL_AND_FIRSTNAME_EXCEPTION_TEXT, userDto.getId(), userDto.getEmail()));
    }

    public UserAlreadyExistException(UpdateUserDto updateUserDto) {
        super(String.format(USER_ALREADY_EXIST_WITH_EMAIL_AND_FIRSTNAME_EXCEPTION_TEXT, updateUserDto.getId(), updateUserDto.getNewEmail()));
    }
}