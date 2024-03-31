package ua.shop.backintime.user.service.validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TelephoneNumberValidatorTest {
    @Autowired
    private TelephoneNumberValidator telephoneNumberValidator;
    @Test
    void testValid() {
        assertDoesNotThrow(() -> telephoneNumberValidator.validate("14152007986"));
    }
    @Test
    void testInvalid() {
        assertThrows(TelephoneNumberValidator.TelephoneNumberInvalidException.class,() ->telephoneNumberValidator.validate("dark24kot"));
    }
}