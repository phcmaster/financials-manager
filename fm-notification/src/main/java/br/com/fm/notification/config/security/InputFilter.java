package br.com.fm.notification.config.security;

import br.com.fm.notification.dto.Session;
import br.com.fm.notification.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Component
public class InputFilter extends OncePerRequestFilter {


    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = retrieveToken(httpServletRequest);
        boolean tokenValid = isTokenValid(token);

        if(tokenValid){
          requestToAuth(httpServletRequest, jwtSecret, token);
          log.info("Token is valid!");

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }


    private String retrieveToken(HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        String tokenFinal = token.substring(7, token.length());

        return tokenFinal;

    }

    private void requestToAuth(HttpServletRequest request, String secret, String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        Session session = jwtUtils.getUserSession(claims);

        if(session != null) {
            List<String> authorities = session.getAuthorities();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    session, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

            SecurityContextHolder.getContext().setAuthentication(auth);
            log.info("User Authentication");
        }

    }

    public boolean isTokenValid(String token) {

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("Token is not valid!");
            return false;
        }

    }

}