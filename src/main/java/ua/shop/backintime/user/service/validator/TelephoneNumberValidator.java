package ua.shop.backintime.user.service.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ua.shop.backintime.config.jwt.XssSanitizerService;

@Service
public class TelephoneNumberValidator {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private XssSanitizerService xssSanitizerService;
    @Value("${demo.app.telephoneCheck.api.key}")
    private String TELEPHONE_API_KEY;


    public String validate(String telephoneNumber) {
        telephoneNumber = xssSanitizerService.sanitize(telephoneNumber);

        String url = "https://phonevalidation.abstractapi.com/v1/?api_key=%s&phone=%s"
                .formatted(TELEPHONE_API_KEY, telephoneNumber);

        try {
            String response = restTemplate.getForObject(url, String.class);
            if (!response.contains("\"valid\":true")) {
                throw new TelephoneNumberInvalidException(telephoneNumber);
            }
        } catch (HttpClientErrorException ignored) {
        }
        return telephoneNumber;
    }

    protected static class TelephoneNumberInvalidException extends RuntimeException {
        public TelephoneNumberInvalidException(String telephoneNumber) {
            super(String.format("Telephone number %s is invalid", telephoneNumber));
        }
    }
}
