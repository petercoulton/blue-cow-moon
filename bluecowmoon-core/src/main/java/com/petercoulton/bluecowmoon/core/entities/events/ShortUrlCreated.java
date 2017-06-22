package com.petercoulton.bluecowmoon.core.entities.events;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ShortUrlCreated extends BaseEvent {

    private String shortUrlName;
    private String longUrl;

    public ShortUrlCreated() {
        super();
    }

    public ShortUrlCreated(final ObjectId correlationId, final String shortUrlName, final String longUrl) {
        super(correlationId);
        this.shortUrlName = shortUrlName;
        this.longUrl = longUrl;
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

    @Override
    public String toString() {
        return "ShortUrlCreated{" +
                "shortUrlName='" + shortUrlName + '\'' +
                ", longUrl='" + longUrl + '\'' +
                "} " + super.toString();
    }
}
