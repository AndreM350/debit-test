package org.bank.debittest.infrastructure.adapter.in.rest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/auth")
//A classe abaixo é para simular um issuer de token jwt com as credenciais
public class AuthController {

    private final String secretKey = "chaveAleatoriaGeradaEmAlgumLugar";

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> generateToken(@RequestBody Map<String, String> loginData) {

        String username = loginData.get("username");
        String password = loginData.get("password");

        if ("user-teste".equals(username) && "teste123456".equals(password)) {
            String token = Jwts.builder()
                    .setSubject(username)
                    .setIssuer("debit-api")
                    .setExpiration(Date.from(Instant.now().plusSeconds(3600)))
                    .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .compact();

            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
