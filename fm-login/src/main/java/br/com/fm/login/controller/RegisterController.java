package br.com.fm.login.controller;


import br.com.fm.login.dto.register.newUserRequest;
import br.com.fm.login.dto.register.ProfileRequest;
import br.com.fm.login.service.impl.RegisterUserServiceImpl;
import br.com.fm.mongodb.entity.ProfileEntity;
import br.com.fm.mongodb.repository.ProfileRepository;
import lombok.var;
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
    private RegisterUserServiceImpl registerUserService;

    @Autowired
    private ProfileRepository repository;


    @PostMapping("/createNewAccount")
    public ResponseEntity<?> create(@RequestBody @Valid newUserRequest request) {
        registerUserService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created with success.");

    }

    @PostMapping("/roles")
    public ResponseEntity<?> roles(@RequestBody ProfileRequest role) {
        var entity = new ProfileEntity();
        entity.setRole(role.getRole());
        repository.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body("Role created with success.");

    }


}
