package ua.shop.backintime.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailValidator {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${demo.app.emailCheck.api.key}")
    private String EMAIL_API_KEY;
    public boolean isValid(String email){
        String url = "https://www.ipqualityscore.com/api/json/phone/%s/%s".formatted(EMAIL_API_KEY, email);
        String response = restTemplate.getForObject(url, String.class);
        return response.contains("valid");
    }
}
