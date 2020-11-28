package br.com.fm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class FinancialsManagerLoginApplication {


    public static void main(String[] args) {
        SpringApplication.run(FinancialsManagerLoginApplication.class, args);
    }


}
