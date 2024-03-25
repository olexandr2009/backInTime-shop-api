package ua.shop.backintime.user.service.dto;

import lombok.*;
import ua.shop.backintime.user.UserRole;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private DataForSending dataForSending;
    private LocalDate lastUpdatedDate;
    private LocalDate createdDate;
    private Set<UserRole> roles = new HashSet<>();
}