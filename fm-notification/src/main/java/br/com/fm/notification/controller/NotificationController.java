package br.com.fm.notification.controller;


import br.com.fm.notification.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public void sendEmail(String otp){

        emailService.sendEmail(otp);

    }

}
