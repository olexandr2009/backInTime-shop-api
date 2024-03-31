package ua.shop.backintime.user.service.validator;

import org.springframework.stereotype.Service;

@Service
public class PasswordValidator {
    public void validatePassword(String password) {
        if (password.length() < 8) {
            throw new RuntimeException("Password is incorect" + password);
        }
        if (!password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
            throw new RuntimeException("Password is incorect " + password);
        }
    }
}
