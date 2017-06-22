package com.petercoulton.bluecowmoon.web.controllers;

import com.petercoulton.bluecowmoon.core.dto.ShortUrl;
import com.petercoulton.bluecowmoon.core.exceptions.BlueCowMoonException;
import com.petercoulton.bluecowmoon.core.services.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/s")
public class ShortUrlController {
    private static final Logger log = LoggerFactory.getLogger(ShortUrlController.class);

    private final ShortUrlService shortUrlService;

    @Autowired
    public ShortUrlController(final ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping(path="/{name}")
    public String resolveShortUrl(@PathVariable("name") final String shortUrlName) throws BlueCowMoonException {
        final ShortUrl shortUrl = this.shortUrlService.expandShortUrlName(shortUrlName);
        return "redirect:" + shortUrl.getLongUrl();
    }
}
