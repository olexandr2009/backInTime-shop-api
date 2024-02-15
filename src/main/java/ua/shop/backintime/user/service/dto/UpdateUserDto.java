package ua.shop.backintime.user.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {
    private long id;
    private String oldFirstName;
    private String newFirstName;
    private String oldLastName;
    private String newLastName;
    private String oldEmail;
    private String newEmail;
    private String oldPassword;
    private String newPassword;
}