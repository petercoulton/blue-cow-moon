package com.petercoulton.bluecowmoon.web.configuration;

import com.petercoulton.bluecowmoon.core.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NameGeneratorConfiguration {
    private static final Logger log = LoggerFactory.getLogger(NameGeneratorConfiguration.class);

    @Bean
    public NameGenerator nameGenerator() {
        return NameGenerator.Builder.fromResourceFile("words");
    }
}
