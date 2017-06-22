package com.petercoulton.bluecowmoon.web.configuration;

import com.petercoulton.bluecowmoon.core.NameGenerator;
import com.petercoulton.bluecowmoon.core.services.EventedShortUrlService;
import com.petercoulton.bluecowmoon.core.repositories.EventRepository;
import com.petercoulton.bluecowmoon.core.repositories.ShortUrlRepository;
import com.petercoulton.bluecowmoon.core.services.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfiguration {
    private static final Logger log = LoggerFactory.getLogger(ServicesConfiguration.class);

    @Bean
    public ShortUrlService shortUrlService(final NameGenerator nameGenerator,
                                           final EventRepository eventRepository,
                                           final ShortUrlRepository shortUrlRepository,
                                           final ApplicationEventPublisher applicationEventPublisher) {
        return new EventedShortUrlService(
                nameGenerator,
                eventRepository,
                shortUrlRepository,
                applicationEventPublisher);
    }
}
