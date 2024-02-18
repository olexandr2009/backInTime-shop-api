package ua.shop.backintime.user.service.exception;

public class UserNotFoundException extends RuntimeException {

    private static final String USER_NOT_FOUND_EXCEPTION_TEXT = "User with email = %s not found.";
    private static final String USER_WITH_ID_NOT_FOUND_EXCEPTION_TEXT = "User with id = %s not found.";

    public UserNotFoundException(String email) {
        super(String.format(USER_NOT_FOUND_EXCEPTION_TEXT, email));
    }

    public UserNotFoundException(Long id) {
        super(String.format(USER_WITH_ID_NOT_FOUND_EXCEPTION_TEXT, id));
    }
}