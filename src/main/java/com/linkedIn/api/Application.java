package com.linkedIn.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Create Spring Boot Application and set a default controller
 */

@SpringBootApplication
public class Application {

    public Application() {
    }

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
