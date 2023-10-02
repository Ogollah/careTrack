package com.stephen.careTrack.security.jwt;

import com.stephen.careTrack.service.UserPrincipal;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * The `JwtProvider` class is responsible for handling JSON Web Tokens (JWT) in a Spring Security setup.
 * It provides methods to generate tokens, extract user details from tokens, and validate token authenticity.
 *
 * This component is used to generate, validate, and handle JWT tokens for user authentication and authorization.
 *
 * @author Ogolla
 * @version 1.0
 * @see org.springframework.security.core.Authentication
 * @see org.springframework.security.core.userdetails.UserDetails
 * @see io.jsonwebtoken.Jwts
 * @see io.jsonwebtoken.SignatureAlgorithm
 * @see io.jsonwebtoken.Claims
 * @see io.jsonwebtoken.SignatureException
 * @see io.jsonwebtoken.MalformedJwtException
 * @see io.jsonwebtoken.ExpiredJwtException
 * @see io.jsonwebtoken.UnsupportedJwtException
 * @see org.springframework.stereotype.Component
 */
@Component
public class JwtProvider {
    @Value("${app.jwtSecret}")
    private String jwtSecret;
    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public String getUserUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
        } catch (MalformedJwtException ex) {
        } catch (ExpiredJwtException ex) {
        } catch (UnsupportedJwtException ex) {
        } catch (IllegalArgumentException ex) {
        }
        return false;
    }
}
