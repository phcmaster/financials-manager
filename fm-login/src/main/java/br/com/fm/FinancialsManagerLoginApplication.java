package br.com.fm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
@EnableHystrix
@EnableCircuitBreaker
public class FinancialsManagerLoginApplication {


    public static void main(String[] args) {
        SpringApplication.run(FinancialsManagerLoginApplication.class, args);
    }


}
