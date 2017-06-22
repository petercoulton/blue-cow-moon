package com.petercoulton.bluecowmoon.web.hateoas.resources;

import com.petercoulton.bluecowmoon.core.dto.ShortUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;

public class ShortUrlResource extends Resource<ShortUrl> {
    private static final Logger log = LoggerFactory.getLogger(ShortUrlResource.class);

    public ShortUrlResource(final ShortUrl shortUrl) {
        super(shortUrl);
    }
}
