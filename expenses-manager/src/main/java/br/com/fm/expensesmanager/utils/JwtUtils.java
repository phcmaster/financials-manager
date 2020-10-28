package br.com.fm.expensesmanager.utils;


import br.com.fm.expensesmanager.dto.Session;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils implements Serializable {



    @Value("${jwt.secret}")
    private String jwtSecret;

    private transient Claims claims;

    @Getter
    public Session session;


    public JwtUtils() {
    }


    public void setClaims(String token) {
        if(null == token)
            throw new NullPointerException("JWT is required.");
        this.claims = jwtInformations(token);

        if(expirado(token))
            throw new IllegalArgumentException("JWT expired!");

        this.session = obterSessao();
    }


    public Claims jwtInformations(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }


    public Map<String, Object> gerarClaims(Session authorization) {
        Map<String, Object> map = new HashMap<>();
        map.put("session", authorization);
        return map;
    }

    private Boolean expirado(String token) {
        final Date expiration = getDataExpiracaoToken(token);
        return expiration.before(new Date());
    }


    public Date getDataExpiracaoToken(String token) {
        return getInformacaoToken(token, Claims::getExpiration);
    }


    private <T> T getInformacaoToken(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(claims);
    }

    private Session obterSessao() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(this.claims.get("session"), Session.class);
    }

}
