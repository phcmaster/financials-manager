package br.com.fm.login.controller;


import br.com.fm.login.dto.TokenResponse;
import br.com.fm.login.dto.UserRequest;
import br.com.fm.login.service.LoginService;
import br.com.fm.mongodb.entity.UserEntity;
import br.com.fm.mongodb.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid UserRequest UserRequest) {
        var tokenResponse = loginService.authenticationUser(UserRequest);
        return ResponseEntity.ok().body(tokenResponse.getBody());

    }


    @PostMapping("/create")
    public ResponseEntity<UserEntity> create(@RequestBody @Valid UserRequest request) {


        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(request.getEmail());
        userEntity.setName(request.getName());
        userEntity.setPassword(request.getPassword());

        userRepository.save(userEntity);


        return ResponseEntity.ok().body(userEntity);

    }



    @GetMapping("/testToken")
    public ResponseEntity<String> create() {
        return ResponseEntity.ok().body("Token ok");
    }



}
