package ua.shop.backintime.user.controller.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^[А-ЩЬЮЯҐЄІЇ][а-щьюяґєії]*(-[А-ЩЬЮЯҐЄІЇ][а-щьюяґєії]*)*$")
    private String firstName;

    @NotBlank
    @Size(min = 2,max = 50)
    @Pattern(regexp = "^[А-ЩЬЮЯҐЄІЇ][а-щьюяґєії]*(-[А-ЩЬЮЯҐЄІЇ][а-щьюяґєії]*)*$")
    private String lastName;

    @NotBlank
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}")
    private String email;

    @NotBlank
    @Size(min = 8, max = 100)
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])((?=.*\\W)|(?=.*_))^[^ ]+$")
    private String password;

}