package br.com.fm.notification.service;

import br.com.fm.notification.dto.UserOtpRequest;
import br.com.fm.notification.utils.JwtUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.smtp}")
    private String smtp;

    @Value("${spring.username.email.sender}")
    private String emailsender;

   @Value("${spring.password.email.sender}")
    private String passwordSender;


    public void sendEmailtoLoginValidation(String otp, UserOtpRequest request){


        try {
            Email email = new SimpleEmail();
            email.setHostName(smtp);
            email.setSmtpPort(465);

            email.setAuthenticator(new DefaultAuthenticator(emailsender, passwordSender));
            email.setSSLOnConnect(true);

            email.setFrom(emailsender);
            email.setSubject("Financials Manager - Validação de conta");
            email.setMsg("Olá " + request.getName() + " seu código para validação é: " + otp);
            email.addTo(request.getEmail());
            email.send();

        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

}
