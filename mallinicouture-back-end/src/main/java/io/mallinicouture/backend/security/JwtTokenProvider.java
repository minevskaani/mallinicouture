package io.mallinicouture.backend.security;

import io.jsonwebtoken.*;
import io.mallinicouture.backend.domain.Client;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.mallinicouture.backend.security.SecurityConstants.SECRET;
import static io.mallinicouture.backend.security.SecurityConstants.TOKEN_EXPIRATION_TIME;

@Component
public class JwtTokenProvider {

    // Generate the token
    public String generateToken(Authentication auth) {
        Client user = (Client) auth.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime() + TOKEN_EXPIRATION_TIME);

        String userId = Long.toString(user.getId());

        Map<String, Object> claims = new HashMap<>();

        claims.put("id", userId);
        claims.put("username", user.getUsername());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT Signature");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT Token");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT Token");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty");
        }

        return false;
    }

    public long getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id = (String)claims.get("id");

        return Long.parseLong(id);
    }
}
