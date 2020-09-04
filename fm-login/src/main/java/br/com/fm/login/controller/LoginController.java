package br.com.fm.login.controller;


import br.com.fm.login.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("￿/auth")
public class LoginController {


    @PostMapping("￿/￿login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request){

        return ResponseEntity.ok().body("Endpoint test");

    }


    @GetMapping("￿/test￿")
    public ResponseEntity<String> login(){

        return ResponseEntity.ok().body("Endpoint test");

    }


}
