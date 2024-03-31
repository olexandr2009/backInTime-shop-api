package ua.shop.backintime.user.service.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DataForDelivery {
    @Pattern(regexp = "/+[\\d{10}]")
    private String telephoneNumber;
    @Pattern(regexp = "^[А-ЯҐЄІЇ][а-яєїі]")
    private String cityName;
    @Pattern(regexp = "^[А-ЯҐЄІЇ][а-яєїі] +\\d")
    private String NPdepartment;
}
