package ua.shop.backintime.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TelephoneNumberValidator {
    @Value("${demo.app.telephoneCheck.api.key}")
    private String TELEPHONE_API_KEY;

    @Autowired
    private RestTemplate restTemplate;

    public boolean isValid(String telephoneNumber) {
        String url = "https://www.ipqualityscore.com/api/json/phone/%s/%s".formatted(TELEPHONE_API_KEY, telephoneNumber);
        String response = restTemplate.getForObject(url, String.class);
        return response.contains("valid");
    }
}
