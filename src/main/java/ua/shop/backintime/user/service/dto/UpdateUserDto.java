package ua.shop.backintime.user.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {
    private String oldEmail;
    private String newEmail;
    private String oldPassword;
    private String newPassword;
}