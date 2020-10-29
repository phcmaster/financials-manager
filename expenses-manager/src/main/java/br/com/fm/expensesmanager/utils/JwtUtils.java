package br.com.fm.expensesmanager.utils;

import br.com.fm.expensesmanager.dto.Session;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
public class JwtUtils implements Serializable {

    @Getter
    Session session;

    public JwtUtils() {
    }


    public Session obterSessao(Claims claims) {
        ObjectMapper mapper = new ObjectMapper();
        Session session = mapper.convertValue(claims.get("session"), Session.class);
        this.session = session;
        return session;
    }

}
