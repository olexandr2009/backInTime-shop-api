package ua.shop.backintime.user.controller.request;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DeliveryData {
    @Pattern(regexp = "^\\+\\d{12,13}$")
    private String telephoneNumber;
    @Pattern(regexp = "^[А-ЯҐЄІЇ][а-яєїі]*")
    private String cityName;
    @Pattern(regexp = "^[А-ЯҐЄІЇ][а-яєїі]* \\d")
    private String NPdepartment;
}
