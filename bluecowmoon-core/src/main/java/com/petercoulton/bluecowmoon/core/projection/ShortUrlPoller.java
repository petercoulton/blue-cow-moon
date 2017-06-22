package com.petercoulton.bluecowmoon.core.projection;

import com.petercoulton.bluecowmoon.core.entities.events.BaseEvent;
import com.petercoulton.bluecowmoon.core.entities.events.ShortUrlCreated;
import com.petercoulton.bluecowmoon.core.repositories.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class ShortUrlPoller {
    private static final Logger log = LoggerFactory.getLogger(ShortUrlPoller.class);

    private Date lastProcessedEventDate;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private EventRepository eventRepository;

    @PostConstruct
    void init() {
        lastProcessedEventDate = new Date();
    }

    @Scheduled(fixedRate = 1000)
    void readNewEvents() {
        eventRepository.findByTypeAndCreatedDateAfter(ShortUrlCreated.class.getCanonicalName(), lastProcessedEventDate)
                .forEach(this::emitEvent);
    }

    private void emitEvent(final BaseEvent baseEvent) {
        log.info("Emitting new Event: {}", baseEvent);
        this.lastProcessedEventDate = baseEvent.getCreatedDate();
        this.eventPublisher.publishEvent(baseEvent);
    }
}
