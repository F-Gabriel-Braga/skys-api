package skys.api.com.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import skys.api.com.model.Client;

import java.util.Date;

@Service
public class TokenService {

    @Value("${secret-jwt}")
    private String secretJwt;

    @Value("${validate-jwt}")
    private Long validateJwt;

    public String generateToken(Client client) {
        return JWT.create()
                .withSubject(client.getEmail())
                .withClaim("id", client.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + this.validateJwt))
                .sign(Algorithm.HMAC512(this.secretJwt));
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(this.secretJwt))
                    .build()
                    .verify(token);
            return true;
        }
        catch (JWTVerificationException ex) {
            return false;
        }
    }

    public String extractSubject(String token) {
        return JWT.require(Algorithm.HMAC512(this.secretJwt))
                .build()
                .verify(token)
                .getSubject();
    }
}
