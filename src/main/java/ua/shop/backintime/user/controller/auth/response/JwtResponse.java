package ua.shop.backintime.user.controller.auth.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class JwtResponse {

    private String token;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles = new ArrayList<>();

    public JwtResponse () {
    }

    public JwtResponse (String token, Long id, String firstName, String lastName, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }
}