package com.petercoulton.bluecowmoon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class BlueCowMoonWeb {
    private static final Logger log = LoggerFactory.getLogger(BlueCowMoonWeb.class);

    public static void main(String... args) {
        SpringApplication.run(BlueCowMoonWeb.class, args);
    }
}
