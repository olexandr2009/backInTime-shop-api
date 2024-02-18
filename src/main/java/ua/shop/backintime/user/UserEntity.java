package ua.shop.backintime.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

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

    @Setter
    private LocalDateTime lastLoginDateTime;

    @Setter
    private LocalDate lastUpdatedDate;

    @CreatedDate
    @Setter
    private LocalDate createdDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @Setter
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    public UserEntity(String firstName, String lastName, String email, String password) {
        if (firstName == null || lastName == null ||email == null || password == null){
            throw new NullPointerException(
                    "Cannot create user entity firstname=%s , lastname=%s,email =%s, password=%s"
                            .formatted(firstName, lastName, email, password));
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        checkPassword(password);
        this.password = password;
    }
    private void checkPassword(String password) {
        if (password.length() < 8) {
            throw new RuntimeException("Password is incorect" + password);

        }
        if (password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")){
            return;
        }
        throw new RuntimeException("Password is incorect " + password);
    }
}
