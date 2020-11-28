package br.com.fm.login.controller;


import br.com.fm.login.dto.login.Session;
import br.com.fm.login.dto.login.UserLoginRequest;
import br.com.fm.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserLoginRequest UserLoginRequest) throws Exception {
        ResponseEntity<Object> tokenResponse = loginService.authenticationUser(UserLoginRequest);
        return ResponseEntity.ok().body(tokenResponse.getBody());

    }

    @GetMapping("/user")
    public ResponseEntity<Session> listUser() {
        return ResponseEntity.ok().body(loginService.listUser());
    }


}
