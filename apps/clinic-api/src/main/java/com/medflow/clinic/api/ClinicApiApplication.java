package com.medflow.clinic.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.medflow")
public class ClinicApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClinicApiApplication.class, args);
    }
}
