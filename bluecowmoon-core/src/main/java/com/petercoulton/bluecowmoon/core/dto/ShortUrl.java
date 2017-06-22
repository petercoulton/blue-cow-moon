package com.petercoulton.bluecowmoon.core.dto;

import org.springframework.hateoas.core.Relation;

@Relation(value = "short-url", collectionRelation = "short-urls")
public class ShortUrl {
    private final String shortUrlName;
    private final String longUrl;

    public ShortUrl(final String shortUrlName, final String longUrl) {
        this.shortUrlName = shortUrlName;
        this.longUrl = longUrl;
    }

    public String getShortUrlName() {
        return shortUrlName;
    }

    public String getLongUrl() {
        return longUrl;
    }
}
