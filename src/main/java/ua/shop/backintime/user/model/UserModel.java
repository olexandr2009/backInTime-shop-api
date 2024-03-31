package ua.shop.backintime.user.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ua.shop.backintime.user.service.dto.DataForDelivery;

import java.time.LocalDate;
import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class UserModel {
    private final Id id;
    private final JwtToken activeToken;
    private final String firstName;
    private final String lastName;
    private final Email email;
    private final Password password;
    private final DataForDelivery dataForDelivery;
    private LocalDateTime lastLoginDateTime = LocalDateTime.now();
    private LocalDate lastUpdatedDate = LocalDate.now();
    private LocalDate createdDate = LocalDate.now();

}