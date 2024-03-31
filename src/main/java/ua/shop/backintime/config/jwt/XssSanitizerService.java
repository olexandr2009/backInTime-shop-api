package ua.shop.backintime.config.jwt;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.stereotype.Service;

@Service
public class XssSanitizerService {
    public String sanitize(String input) {
        PolicyFactory policyFactory = new HtmlPolicyBuilder()
                .allowStandardUrlProtocols()
                .allowStyling()
                .allowCommonBlockElements()
                .allowCommonInlineFormattingElements()
                .allowElements("a")
                .allowAttributes("href").onElements("a")
                .toFactory();
        return policyFactory.sanitize(input);
    }
}
