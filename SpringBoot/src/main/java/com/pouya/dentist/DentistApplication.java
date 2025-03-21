package com.pouya.dentist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Dental Social Network Spring Boot Application.
 * This class serves as the entry point for the application and
 * configures Spring Boot's autoconfiguration and component scanning.
 */
@SpringBootApplication
public class DentistApplication {

    /**
     * Main method that launches the Spring Boot application.
     *
     * @param args Command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(DentistApplication.class, args);
    }

}