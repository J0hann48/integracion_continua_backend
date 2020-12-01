package com.focus.spingboot.backend.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class SpringBootBackendApirestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBackendApirestApplication.class, args);
    }

}
