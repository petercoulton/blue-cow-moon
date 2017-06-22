package com.petercoulton.bluecowmoon.core.services;

import com.petercoulton.bluecowmoon.core.NameGenerator;
import com.petercoulton.bluecowmoon.core.dto.ShortUrl;
import com.petercoulton.bluecowmoon.core.entities.ShortUrlEntity;
import com.petercoulton.bluecowmoon.core.entities.events.ShortUrlCreated;
import com.petercoulton.bluecowmoon.core.exceptions.BlueCowMoonException;
import com.petercoulton.bluecowmoon.core.exceptions.BlueCowMoonNotFoundException;
import com.petercoulton.bluecowmoon.core.repositories.EventRepository;
import com.petercoulton.bluecowmoon.core.repositories.ShortUrlRepository;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventedShortUrlService implements ShortUrlService {
    private static final Logger log = LoggerFactory.getLogger(EventedShortUrlService.class);

    private final NameGenerator nameGenerator;
    private final EventRepository eventRepository;
    private final ShortUrlRepository shortUrlRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public EventedShortUrlService(final NameGenerator nameGenerator,
                                  final EventRepository eventRepository,
                                  final ShortUrlRepository shortUrlRepository,
                                  final ApplicationEventPublisher applicationEventPublisher) {
        this.nameGenerator = nameGenerator;
        this.eventRepository = eventRepository;
        this.shortUrlRepository = shortUrlRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShortUrl createShortUrl(final String longUrl) throws BlueCowMoonException {
        final ShortUrl shortUrl = new ShortUrl(
                nextShortUrlName(),
                longUrl
        );

        final ShortUrlCreated event = shortUrlCreatedEvent(shortUrl);
        this.eventRepository.save(event);
        this.applicationEventPublisher.publishEvent(event);

        return shortUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShortUrl expandShortUrlName(final String shortUrlName) throws BlueCowMoonException {
        return Optional.ofNullable(this.shortUrlRepository.findOneByShortUrlName(shortUrlName))
                .map(this::convert)
                .orElseThrow(() -> new BlueCowMoonNotFoundException("Unknown short url name '" + shortUrlName + "'"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ShortUrl> list(final Pageable page) throws BlueCowMoonException {
        final Page<ShortUrlEntity> shortUrls = this.shortUrlRepository.findAll(page);
        return new PageImpl<>(convert(shortUrls), page, shortUrls.getTotalElements());
    }


    @NotNull
    private ShortUrlCreated shortUrlCreatedEvent(final ShortUrl shortUrl) {
        return new ShortUrlCreated(
                new ObjectId(),
                shortUrl.getShortUrlName(),
                shortUrl.getLongUrl()
        );
    }

    @NotNull
    private String nextShortUrlName() {
        return nameGenerator.capitalizedName(3);
    }

    private List<ShortUrl> convert(final Page<ShortUrlEntity> shortUrls) {
        return shortUrls.getContent().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @NotNull
    private ShortUrl convert(final ShortUrlEntity e) {
        return new ShortUrl(
                e.getShortUrlName(),
                e.getLongUrl()
        );
    }
}
