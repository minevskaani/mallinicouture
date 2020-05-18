package io.mallinicouture.backend.security;

public class SecurityConstants {

    public static final String SIGN_UP_URL = "/api/users/register";
    public static final String SIGN_IN_URL = "/api/users/login";
    public static final String CREDIT_CARD_URL = "/api/users/credit-card";

    // public static final String H2_URL = "h2-console/**";
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX = "Bearer "; // Should end with space
    public static final String HEADER_STRING = "Authorization";
    public static final long  TOKEN_EXPIRATION_TIME = 5 * 60 * 60 * 1_000; // 5 hours

}
