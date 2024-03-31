package ua.shop.backintime.user.service.validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class EmailValidatorTest {

    @Autowired
    private EmailValidator emailValidator;
    @Test
    void testValid() {
        assertDoesNotThrow(() -> emailValidator.validate("dark24kot@gmail.com"));
    }
    @Test
    void testInvalid() {
        assertThrows(EmailValidator.EmailInvalidException.class,() -> emailValidator.validate("dark24kot"));
    }
}