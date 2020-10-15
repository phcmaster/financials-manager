package br.com.fm.login.controller;


import br.com.fm.login.dto.NewUserRequest;
import br.com.fm.login.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterUserService registerUserService;

    @PostMapping("/createNewAccount")
    public ResponseEntity<?> create(@RequestBody @Valid NewUserRequest request) {
        registerUserService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created with success.");

    }
}
