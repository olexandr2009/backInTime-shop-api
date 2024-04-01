package ua.shop.backintime.user.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import ua.shop.backintime.user.UserRole;
import ua.shop.backintime.user.controller.request.DeliveryData;

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
    @Min(0)
    private Long id;
    @Pattern(regexp = "[а-яєїіА-ЯҐЄІЇ]")
    private String firstName;
    @Pattern(regexp = "[а-яєїіА-ЯҐЄІЇ]")
    private String lastName;
    @Email
    private String email;
    private DeliveryData deliveryData;
    @Past
    private LocalDate lastUpdatedDate;
    @Past
    private LocalDate createdDate;
    private Set<UserRole> roles = new HashSet<>();
}
//annotations on field are not required it is for lighter code