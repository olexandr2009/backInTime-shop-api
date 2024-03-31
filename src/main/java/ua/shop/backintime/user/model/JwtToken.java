package ua.shop.backintime.user.model;

public class JwtToken {
    private final String token;

    public JwtToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Token cannot be null or empty");
        }
        this.token = token;
    }
}
