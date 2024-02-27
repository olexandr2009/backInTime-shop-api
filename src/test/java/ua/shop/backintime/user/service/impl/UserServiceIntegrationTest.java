package ua.shop.backintime.user.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.shop.backintime.user.repository.UserRepository;
import ua.shop.backintime.user.service.UserService;
import ua.shop.backintime.user.service.dto.UpdateUserDto;
import ua.shop.backintime.user.service.dto.UserDto;
import ua.shop.backintime.user.service.exception.UserAlreadyExistException;
import ua.shop.backintime.user.service.exception.UserIncorrectPasswordException;
import ua.shop.backintime.user.service.exception.UserNotFoundException;
import ua.shop.backintime.user.service.mapper.UserMapper;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceIntegrationTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void dropTable() {
        userRepository.deleteAll();
    }

    @Test
    void testUpdateUser() {
        UpdateUserDto updateUserDto = newTestUpdateUserDto();
        userService.registerUser(newUserDto(updateUserDto.getOldEmail()), updateUserDto.getOldPassword());
        long id = userRepository.findMaxId();
        assertEquals(updateUserDto.getNewEmail(), userService.updateUser(id, updateUserDto).getEmail());
    }

    @Test
    void testRegisterUser() {
        assertDoesNotThrow(() -> userService.registerUser(newUserDto("email@example.com"), "pass"));
    }

    @Test
    void testRegisterUserThrowsUserAlreadyExistEx() {
        String pass = "pass";
        String email = "email@example.com";
        userService.registerUser(newUserDto(email), pass);
        assertThrows(UserAlreadyExistException.class, () -> userService.registerUser(newUserDto(email), pass));
    }

    @Test
    void testUpdateUserThrowsUserNotFoundExByOldUsername() {
        UpdateUserDto updateUserDto = newTestUpdateUserDto();
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(1L, updateUserDto));
    }

    @Test
    void testUpdateUserThrowsUserAlreadyExistExByNewUsername() {
        UpdateUserDto updateUserDto = newTestUpdateUserDto();
        userService.registerUser(newUserDto(updateUserDto.getOldEmail()), updateUserDto.getOldPassword());
        userService.registerUser(newUserDto(updateUserDto.getNewEmail()), updateUserDto.getNewPassword());

        assertThrows(UserAlreadyExistException.class, () -> userService.updateUser(1L, updateUserDto));
    }

    @Test
    void testUpdateUserThrowsUserIncorrectPasswordEx() {
        UpdateUserDto updateUserDto = newTestUpdateUserDto();
        userService.registerUser(newUserDto(updateUserDto.getOldEmail()), "null");
        assertThrows(UserIncorrectPasswordException.class, () -> userService.updateUser(1L, updateUserDto));
    }

    private static UserDto newUserDto(String email) {
        UserDto userDto = new UserDto();
        userDto.setFirstName("firstName");
        userDto.setLastName("lastName");
        userDto.setEmail(email);
        return userDto;
    }

    private UpdateUserDto newTestUpdateUserDto() {
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setOldEmail("old@example.com");
        updateUserDto.setNewEmail("new@example.com");
        updateUserDto.setNewPassword("newPass1");
        updateUserDto.setOldPassword("oldPass1");
        return updateUserDto;
    }
}
