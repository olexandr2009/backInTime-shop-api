package ua.shop.backintime.user.service.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.shop.backintime.config.jwt.XssSanitizerService;

@Service
public class EmailValidator {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private XssSanitizerService xssSanitizerService;

    @Value("${demo.app.emailCheck.api.key}")
    private String EMAIL_API_KEY;

    public String validate(String email) {
//        email = xssSanitizerService.sanitize(email);

        String url = "https://emailvalidation.abstractapi.com/v1/?api_key=%s&email=%s".formatted(EMAIL_API_KEY, email);
        try {
            String response = restTemplate.getForObject(url, String.class);
            if (!response.contains("true")) {
                throw new EmailInvalidException(email);
            }
        } catch (Exception ignored) {
            if (ignored instanceof EmailInvalidException) {
//                throw (EmailInvalidException) ignored;
            }
        }
        return email;
    }

    public static class EmailInvalidException extends RuntimeException {
        public EmailInvalidException(String email) {
            super(String.format("Email %s is invalid", email));
        }
    }
}
