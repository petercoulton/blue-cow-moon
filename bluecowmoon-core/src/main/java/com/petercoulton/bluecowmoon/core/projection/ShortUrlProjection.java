package com.petercoulton.bluecowmoon.core.projection;

import com.petercoulton.bluecowmoon.core.entities.ShortUrlEntity;
import com.petercoulton.bluecowmoon.core.entities.events.ShortUrlCreated;
import com.petercoulton.bluecowmoon.core.repositories.ShortUrlRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlProjection {
    private static final Logger log = LoggerFactory.getLogger(ShortUrlProjection.class);

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @EventListener
    public void onShortUrlCreated(final ShortUrlCreated event) {
        this.shortUrlRepository.save(convert(event));
    }

    @NotNull
    private ShortUrlEntity convert(final ShortUrlCreated event) {
        return new ShortUrlEntity(event.getCorrelationId(), event.getShortUrlName(), event.getLongUrl());
    }
}
