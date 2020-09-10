package br.com.fm.login.service;


import br.com.fm.login.dto.TokenResponse;
import br.com.fm.login.dto.UserRequest;
import br.com.fm.mongodb.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${jwt.expiration}")
    private String jwtExpiration;

    @Value("${jwt.secret}")
    private String jwtSecret;


    public TokenResponse authenticationUser(UserRequest userRequest) throws AuthenticationException {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = requestToAuth(userRequest);


        try {
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            String token = generateToken(authenticate);

            return new TokenResponse(token, "Bearer");

        } catch (AuthenticationException e) {
            return null;
        }


    }


    private UsernamePasswordAuthenticationToken requestToAuth(UserRequest userRequest) {
        return new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword());
    }


    public String generateToken(Authentication authentication) {

        UserEntity loggedUser = (UserEntity) authentication.getPrincipal();

        return Jwts.builder()
                .setIssuer("Fm login")
                .setSubject(loggedUser.getId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpiration)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }


}
