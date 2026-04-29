package com.semihkurucay.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${security.jwt.token-expiration-time}")
    private Long TOKEN_EXPIRATION_TIME;

    private SecretKey getSecretKey() {
        byte[] key = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    public String generateToken(UserDetails userDetails){
        return Jwts
                .builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(getSecretKey(), Jwts.SIG.HS256)
                .compact();
    }

    private Claims getClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T exportToken(String token, Function<Claims, T> claimsTFunction){
        Claims claims = getClaims(token);
        return claimsTFunction.apply(claims);
    }

    public String getUsername(String token){
        return exportToken(token, Claims::getSubject);
    }

    public Boolean isTokenExpired(String token){
        return exportToken(token, Claims::getExpiration).before(new Date());
    }

    public Boolean isValidToken(String token, UserDetails userDetails){
        final String username = getUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
