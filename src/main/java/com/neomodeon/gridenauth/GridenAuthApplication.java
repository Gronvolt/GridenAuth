package com.neomodeon.gridenauth;

import com.neomodeon.gridenauth.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class GridenAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(GridenAuthApplication.class, args);
    }

}
