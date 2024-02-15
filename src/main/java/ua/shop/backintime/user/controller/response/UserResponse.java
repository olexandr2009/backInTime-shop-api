package ua.shop.backintime.user.controller.response;

import lombok.Getter;
import lombok.Setter;
import ua.shop.backintime.user.UserRole;

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
    private LocalDate lastUpdatedDate;
    private LocalDate createdDate;
    private Set<UserRole> roles = new HashSet<>();
}