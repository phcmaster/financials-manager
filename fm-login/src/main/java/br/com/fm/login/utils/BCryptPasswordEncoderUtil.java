package br.com.fm.login.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BCryptPasswordEncoderUtil {


    public static String passwordEncoder(String password) {
        String encodePassword = new BCryptPasswordEncoder().encode(password);
        return encodePassword;
    }


}
