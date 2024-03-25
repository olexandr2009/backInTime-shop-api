package ua.shop.backintime.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.shop.backintime.user.service.dto.DataForSending;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String activeToken;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Setter
    private String email;

    @NotBlank
    @Setter
    private String password;

    @NotBlank
    @Setter
    @Column(name = "telephone_number")
    private String telephoneNumber;

    @NotBlank
    @Setter
    @Column(name = "city_name")
    private String cityName;

    @NotBlank
    @Setter
    @Column(name = "NP_department")
    private String NPdepartment;

    @Setter
    private LocalDateTime lastLoginDateTime = LocalDateTime.now();

    @Setter
    private LocalDate lastUpdatedDate = LocalDate.now();

    @Setter
    private LocalDate createdDate = LocalDate.now();

    @ManyToMany(fetch = FetchType.EAGER)
    @Setter
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    public UserEntity(String firstName, String lastName, String email, String password, DataForSending dataForSending) {
        if (firstName == null || lastName == null || email == null || password == null) {
            throw new NullPointerException(
                    "Cannot create user entity firstname=%s , lastname=%s,email =%s, password=%s"
                            .formatted(firstName, lastName, email, password));
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cityName = dataForSending.getCityName();
        this.NPdepartment = dataForSending.getNPdepartment();
        this.telephoneNumber = dataForSending.getTelephoneNumber();
        checkPassword(password);
        this.password = password;
    }

    private void checkPassword(String password) {
        if (password.length() < 8) {
            throw new RuntimeException("Password is incorect" + password);

        }
        if (password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
            return;
        }
        throw new RuntimeException("Password is incorect " + password);
    }
}
