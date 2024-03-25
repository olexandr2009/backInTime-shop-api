package ua.shop.backintime.user.service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DataForSending {
    private String telephoneNumber;
    private String cityName;
    private String NPdepartment;
}
