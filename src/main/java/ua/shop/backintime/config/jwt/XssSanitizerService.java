package ua.shop.backintime.config.jwt;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Service
public class XssSanitizerService {
    private PolicyFactory policyFactory = policyFactory();

    public XssSanitizerService(){
        this.policyFactory = policyFactory();
    }
    public String sanitize(String input) {
        return policyFactory.sanitize(input);
    }

    public <T> T sanitizeObject(T input) {
        List<Field> fields = Arrays.stream(input.getClass().getDeclaredFields()).toList();

        fields.stream().filter(field -> field.getType().isAssignableFrom(String.class))
                .peek(field -> field.setAccessible(true))
                .forEach(field -> {
                    try {
                        String sanitize = policyFactory.sanitize((String) field.get(input));

                        field.set(input, sanitize.replace("&#64;","@"));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });

        return input;
    }

    private PolicyFactory policyFactory() {
        return new HtmlPolicyBuilder()
                .allowStandardUrlProtocols()
                .allowStyling()
                .allowCommonBlockElements()
                .allowCommonInlineFormattingElements()
                .allowElements("a")
                .allowAttributes("href").onElements("a")
                .toFactory();

    }
}
