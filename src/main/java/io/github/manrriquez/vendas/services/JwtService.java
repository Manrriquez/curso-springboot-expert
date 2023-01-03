package io.github.manrriquez.vendas.services;


import io.github.manrriquez.vendas.models.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {


    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.key-signature}")
    private String keySignature;

    public String generatedToken(UserModel user) {
        long expString = Long.valueOf(expiration);
        LocalDateTime dateHourExpiration = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dateHourExpiration.atZone(ZoneId.systemDefault()).toInstant();

        Date date = Date.from(instant);

        return Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, keySignature)
                .compact();
    }

    public Claims getClaims(String token) throws ExpiredJwtException {

        return Jwts
                .parser()
                .setSigningKey(keySignature)
                .parseClaimsJwt(token)
                .getBody();
    }

    public boolean tokenValid(String token) {

        try{
            Claims claims = getClaims(token);
            Date dateExpiration = claims.getExpiration();
            LocalDateTime localDateTime = dateExpiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            return !LocalDateTime.now().isAfter(localDateTime);
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoginUser(String token) throws ExpiredJwtException {

        return (String) getClaims(token).getSubject();
    }

}
