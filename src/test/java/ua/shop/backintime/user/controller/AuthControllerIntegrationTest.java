package ua.shop.backintime.user.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.junit.jupiter.Testcontainers;
import ua.shop.backintime.user.UserEntity;
import ua.shop.backintime.user.controller.auth.request.LoginRequest;
import ua.shop.backintime.user.controller.auth.request.SignupRequest;
import ua.shop.backintime.user.repository.UserRepository;
import ua.shop.backintime.user.service.dto.DataForSending;
import ua.shop.backintime.user.service.dto.UserDto;
import ua.shop.backintime.user.service.exception.UserAlreadyExistException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@SpringBootTest
@RunWith(JUnit4.class)
class AuthControllerIntegrationTest {
    private final String testEmail = "test_email";
    private final String testPassword = "testPassword1";

    @Autowired
    private AuthController authController;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void clearTableUsers() {
        userRepository.deleteAll();
    }

    @Test
    void testAuthenticateUserThrowsEx() {
        assertEquals(HttpStatus.BAD_REQUEST, authController.authenticateUser(
                createTestLoginRequest()).getStatusCode());
    }

    @Test
    void testRegisterUser() {
        assertEquals(new ResponseEntity<>(HttpStatus.CREATED), authController.registerUser(
                createTestSignUpRequest()));
    }

    @Test
    void testRegisterUserThrowsEx() {
        UserEntity entity = new UserEntity("igor", "petrovych", testEmail, testPassword, new DataForSending("+380987654321","City", "NPdepartment"));
        entity.setLastLoginDateTime(LocalDateTime.now());
        entity.setCreatedDate(LocalDate.now());
        entity.setLastUpdatedDate(LocalDate.now());
        userRepository.save(entity);
        UserDto userDto = new UserDto();
        userDto.setEmail(testEmail);
        assertThrows(UserAlreadyExistException.class,
                () -> authController.registerUser(createTestSignUpRequest()));
    }

    private LoginRequest createTestLoginRequest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword(testPassword);
        loginRequest.setEmail(testEmail);
        return loginRequest;
    }

    private SignupRequest createTestSignUpRequest() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setPassword(testPassword);
        signupRequest.setEmail(testEmail);
        signupRequest.setFirstName("firstname");
        signupRequest.setLastName("lastName");
        signupRequest.setDataForSending(new DataForSending("number","cityName","npdepartment"));
        return signupRequest;
    }
}