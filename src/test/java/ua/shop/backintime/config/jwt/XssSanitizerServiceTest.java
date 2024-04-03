package ua.shop.backintime.config.jwt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.shop.backintime.user.controller.request.DeliveryData;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

class XssSanitizerServiceTest {

    @Test
    void testSanitizeObject() throws Exception {
        // given
        XssSanitizerService xssSanitizerService = new XssSanitizerService();
        DeliveryData input = new DeliveryData();
        input.setCityName("<script>alert('xss')</script>Name");
        input.setNPdepartment("http://example.com@");

        // when
        DeliveryData sanitizedInput = xssSanitizerService.sanitizeObject(input);

        System.out.print(sanitizedInput);
        // then
        List<Field> fields = Arrays.stream(sanitizedInput.getClass().getDeclaredFields()).toList();
        Assertions.assertEquals(3, fields.size());
        Assertions.assertEquals("Name", input.getCityName());
        Assertions.assertEquals("http://example.com@", input.getNPdepartment());
    }

}