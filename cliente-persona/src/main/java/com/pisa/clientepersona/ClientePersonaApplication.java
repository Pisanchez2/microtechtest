package com.pisa.clientepersona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.pisa.clientepersona"})
public class ClientePersonaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientePersonaApplication.class, args);
    }

}
