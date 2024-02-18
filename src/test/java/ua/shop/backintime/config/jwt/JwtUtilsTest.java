package ua.shop.backintime.config.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtUtilsTest {
    @Autowired
    JwtUtils jwtUtils;
    private String jwtToken;
    private final Long id = 1L;
    private final String firstName = "firstName";
    private final String lastName = "lastName";
    private final String email = "email";
    private final List<GrantedAuthority> authorities = List.of();
    private final String password = "pass";

    @BeforeEach
    void initToken() {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                new UserDetailsImpl(id, firstName, lastName, email, password, authorities), password);
        jwtToken = jwtUtils.generateJwtToken(authentication);
    }

    @Test
    void testGenerateJwtTokenThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> jwtUtils.generateJwtToken(null));
    }

    @Test
    void testGetUserIdFromJwtToken() {
        assertEquals(id, jwtUtils.getUserIdFromJwtToken(jwtToken));
    }

    @Test
    void testGetUserEmailFromJwtToken() {
        assertEquals(email, jwtUtils.getUserEmailFromJwtToken(jwtToken));
    }

    @Test
    void testGetUserRolesFromJwtToken() {
        assertEquals(authorities, jwtUtils.getUserRolesFromJwtToken(jwtToken).get("Authorities"));
    }

    @Test
    void testValidateJwtTokenThrowsMalformedJwtException() {
        assertFalse(jwtUtils.validateJwtToken("sjfls"));
    }

    @Test
    void testValidateJwtTokenThrowsIllegalArgumentException() {
        assertFalse(jwtUtils.validateJwtToken(null));
    }

    @Test
    void testValidateJwtToken() {
        assertTrue(jwtUtils.validateJwtToken(jwtToken));
    }
}