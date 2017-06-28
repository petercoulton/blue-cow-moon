package com.petercoulton.bluecowmoon.web.configuration;

import com.petercoulton.bluecowmoon.core.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public class NameGeneratorConfiguration {
    private static final Logger log = LoggerFactory.getLogger(NameGeneratorConfiguration.class);

    @Bean
    public NameGenerator nameGenerator() {
        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("words");
        return NameGenerator.Builder.fromInputStream(inputStream);
    }
}
