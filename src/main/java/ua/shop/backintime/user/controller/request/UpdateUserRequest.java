package ua.shop.backintime.user.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String oldPassword;

    @NotBlank
    @Email
    private String oldEmail;


    @NotBlank
    @Size(min = 3, max = 100)
    private String newPassword;

    @NotBlank
    @Email
    private String newEmail;
}