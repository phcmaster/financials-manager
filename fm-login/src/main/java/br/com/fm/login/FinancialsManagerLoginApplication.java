package br.com.fm.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class FinancialsManagerLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancialsManagerLoginApplication.class, args);
    }

}
