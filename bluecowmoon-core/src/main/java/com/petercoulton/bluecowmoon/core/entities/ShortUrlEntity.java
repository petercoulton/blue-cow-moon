package com.petercoulton.bluecowmoon.core.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ShortUrlEntity {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String shortUrlName;

    private String longUrl;

    public ShortUrlEntity() {
    }

    public ShortUrlEntity(final String shortUrlName, final String longUrl) {
        this.shortUrlName = shortUrlName;
        this.longUrl = longUrl;
    }

    public ShortUrlEntity(final ObjectId id, final String shortUrlName, final String longUrl) {
        this(shortUrlName, longUrl);
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(final ObjectId id) {
        this.id = id;
    }

    public String getShortUrlName() {
        return shortUrlName;
    }

    public void setShortUrlName(final String shortUrlName) {
        this.shortUrlName = shortUrlName;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(final String longUrl) {
        this.longUrl = longUrl;
    }
}
