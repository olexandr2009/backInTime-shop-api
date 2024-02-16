package ua.shop.backintime.user.service.exception;

public class UserIsOnlineException extends RuntimeException{
    private static final String USER_IS_ONLINE_EXCEPTION_TEXT = "Account is online for email %s";

    public UserIsOnlineException(String email) {
        super(String.format(USER_IS_ONLINE_EXCEPTION_TEXT, email));
    }
}
