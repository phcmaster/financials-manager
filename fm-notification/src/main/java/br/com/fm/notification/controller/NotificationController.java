package br.com.fm.notification.controller;


import br.com.fm.notification.dto.UserOtpRequest;
import br.com.fm.notification.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestParam String otp, @RequestBody UserOtpRequest userOtpRequest){
        emailService.sendEmailtoLoginValidation(otp, userOtpRequest);
        return ResponseEntity.ok().body("Email sended!");
    }

}
