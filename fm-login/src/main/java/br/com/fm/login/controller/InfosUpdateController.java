package br.com.fm.login.controller;


import br.com.fm.login.dto.InfosUpdate.PasswordUpdateRequest;
import br.com.fm.login.dto.InfosUpdate.UpdateUserRequest;
import br.com.fm.login.service.impl.InfosUpdateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/informations")
public class InfosUpdateController {

    @Autowired
    private InfosUpdateServiceImpl updateService;


    @PutMapping("/update")
    public ResponseEntity<?> informationUpdate(@Valid @RequestBody UpdateUserRequest request) {
        updateService.updateUser(request);
        return ResponseEntity.status(HttpStatus.OK).body("Update with success!");

    }

    @PostMapping("/otp")
    public ResponseEntity<?> otpValidation(@RequestParam String otp, @RequestParam String email) {
        updateService.otpValidation(otp, email);
        return ResponseEntity.status(HttpStatus.OK).body("OTP valid.");

    }


    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestParam PasswordUpdateRequest request) {
        updateService.changePassword(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Your password was updated.");

    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        updateService.forgotPassword(email);
        return ResponseEntity.status(HttpStatus.CREATED).body("An email was send to you to change your password.");

    }

}
