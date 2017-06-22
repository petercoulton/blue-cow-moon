package com.petercoulton.bluecowmoon.core.services;

import com.petercoulton.bluecowmoon.core.dto.ShortUrl;
import com.petercoulton.bluecowmoon.core.exceptions.BlueCowMoonException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShortUrlService {
    /**
     * Create new shortened url.
     *
     * @param longUrl long url to shorten
     * @return shortened url
     */
    ShortUrl createShortUrl(String longUrl) throws BlueCowMoonException;

    /**
     * Looks up the original long url given it's short url.
     *
     * @param shortUrl short url to lookup
     * @return original long url
     */
    ShortUrl expandShortUrlName(String shortUrl) throws BlueCowMoonException;

    /**
     * List registered short urls.
     *
     * @param page
     * @return registered short urls
     */
    Page<ShortUrl> list(Pageable page) throws BlueCowMoonException;
}
