package br.com.fm.login.service;

import br.com.fm.login.dto.login.Session;
import br.com.fm.login.dto.login.UserLoginRequest;
import br.com.fm.mongodb.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface LoginService {

    ResponseEntity<Object> authenticationUser(UserLoginRequest userLoginRequest) throws Exception;

    String generateToken(Authentication authentication);

    boolean isTokenValid(String token);

    String tokenGetUserId(String token);

    Session listUser();


}
