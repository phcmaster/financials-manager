package br.com.fm.investment.util;

import br.com.fm.investment.dto.Session;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
public class JwtUtils implements Serializable {

    @Getter
    Session session;

    public JwtUtils() {
    }


    public Session getUserSession(Claims claims) {
        ObjectMapper mapper = new ObjectMapper();
        Session session = mapper.convertValue(claims.get("session"), Session.class);
        this.session = session;
        return session;
    }

}
