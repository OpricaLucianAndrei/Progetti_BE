package it.epicode.gestioneEventi.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.epicode.gestioneEventi.entity.User;
import it.epicode.gestioneEventi.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JwtTool {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.duration}")
    private Long duration;

    public String createToken(User utente) {
        return Jwts.builder().issuedAt(new Date(System.currentTimeMillis())).
                expiration(new Date(System.currentTimeMillis() + duration)).
                subject(String.valueOf(utente.getId())).
                signWith(Keys.hmacShaKeyFor(secret.getBytes())).
                compact();
    }


    public void verifyToken(String token) {
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).
                    build().parse(token);
        } catch (Exception e) {
            throw  new UnauthorizedException("Errore nell'autorizzazione, bisogna rifare il login!");
        }
    }

    public int getIdFromToken(String token) {
        return Integer.parseInt(Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).
                build().parseSignedClaims(token).getPayload().getSubject());
    }
}
