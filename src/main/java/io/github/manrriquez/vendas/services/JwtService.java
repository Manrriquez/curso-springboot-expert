package io.github.manrriquez.vendas.services;


import io.github.manrriquez.vendas.models.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class JwtService {


    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.key-signature}")
    private String keySignature;


    public String gerarToken(UserModel usuario) {
        long expString = Long.parseLong(expiration);
        return Jwts
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expString).toInstant()))
                .signWith(SignatureAlgorithm.HS512, keySignature)
                .compact();
    }

    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(keySignature)
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean tokenValido(String token) {
        try {
            obterClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException {
        return obterClaims(token).getSubject();
    }
}
