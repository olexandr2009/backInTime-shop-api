package ua.shop.backintime.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ua.shop.backintime.user.service.UserService;
import ua.shop.backintime.user.service.dto.UpdateUserDto;
import ua.shop.backintime.user.service.mapper.UserMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest(UserController.class)
@RunWith(MockitoJUnitRunner.class)
public class UserControllerUnitTest {
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;
    @Autowired
    private UserController controller;
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setup() {
        openMocks(this);
    }

//    @Test
//    void testUpdateUserWorksCorrectly() throws Exception {
//        int status = mvc.perform(put("/api/v1/users/update").with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(newTestUpdateUserDto())))
//                .andReturn().getResponse().getStatus();
//        assertEquals(200, status);
//    }
    @Test
    @WithMockUser
    void testUpdateUserBadRequest() throws Exception {
        int status = mvc.perform(put("/api/v1/users/update").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(null)))
                .andReturn().getResponse().getStatus();
        assertEquals(400, status);
    }
    private UpdateUserDto newTestUpdateUserDto() {
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setOldEmail("old@example.com");
        updateUserDto.setNewEmail("new@example.com");
        updateUserDto.setNewPassword("newPass1");
        updateUserDto.setOldPassword("oldPass1");
        return updateUserDto;
    }

    public static String toJson(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}