package ua.shop.backintime.user.controller.response;

import lombok.Getter;
import lombok.Setter;
import ua.shop.backintime.user.UserRole;
import ua.shop.backintime.user.service.dto.DataForSending;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private DataForSending dataForSending;
    private LocalDate lastUpdatedDate;
    private LocalDate createdDate;
    private Set<UserRole> roles = new HashSet<>();
}