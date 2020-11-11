package br.com.fm.login.service.impl;


import br.com.fm.login.dto.login.Session;
import br.com.fm.login.dto.login.TokenResponse;
import br.com.fm.login.dto.login.UserLoginRequest;
import br.com.fm.login.utils.JwtUtils;
import br.com.fm.mongodb.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LoginService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${jwt.expiration}")
    private String jwtExpiration;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    private JwtUtils jwtUtils;


    public ResponseEntity<Object> authenticationUser(UserLoginRequest userLoginRequest) throws Exception {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = requestToAuth(userLoginRequest);

        try {
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            String token = generateToken(authenticate);
            log.info("Auth user - token generated!");
            return ResponseEntity.ok().body(new TokenResponse(token, "Bearer", LocalDateTime.now()));

        } catch (AuthenticationException e) {
            log.error("Auth user -  could not generate token!");
            throw new IllegalArgumentException(e.getMessage());
        }


    }


    private UsernamePasswordAuthenticationToken requestToAuth(UserLoginRequest userLoginRequest) {
        return new UsernamePasswordAuthenticationToken(userLoginRequest.getEmail(), userLoginRequest.getPassword());
    }

    public String generateToken(Authentication authentication) {

        UserEntity loggedUser = (UserEntity) authentication.getPrincipal();

        return Jwts.builder()
                .setIssuer("Fm login")
                .setSubject(loggedUser.getId())
                .addClaims(generateClaims(loggedUser))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpiration)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    private Map<String, Object> generateClaims(UserEntity sessionEntity) {
        Map<String, Object> map = new HashMap<>();
        Session session = new Session();

        List<String> authorities = sessionEntity.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        session.setId(sessionEntity.getId());
        session.setUserName(sessionEntity.getUsername());
        session.setName(sessionEntity.getName());
        session.setAuthorities(authorities);

        map.put("session", session);
        return map;
    }

    public boolean isTokenValid(String token) {

        try {
            Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("Token is not valid!");
            return false;
        }

    }

    public String tokenGetUserId(String token) {
        Claims body = Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token).getBody();
        jwtUtils.getUserSession(body);
        return body.getSubject();
    }


}
