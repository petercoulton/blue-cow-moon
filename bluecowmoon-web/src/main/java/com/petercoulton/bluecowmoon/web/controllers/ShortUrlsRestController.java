package com.petercoulton.bluecowmoon.web.controllers;

import com.petercoulton.bluecowmoon.core.dto.ShortUrl;
import com.petercoulton.bluecowmoon.core.exceptions.BlueCowMoonException;
import com.petercoulton.bluecowmoon.core.services.ShortUrlService;
import com.petercoulton.bluecowmoon.web.hateoas.assemblers.ShortUrlResourceAssembler;
import com.petercoulton.bluecowmoon.web.hateoas.resources.ShortUrlResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ExposesResourceFor(ShortUrl.class)
@RequestMapping(path="/api/v1/short-urls")
public class ShortUrlsRestController {
    private static final Logger log = LoggerFactory.getLogger(ShortUrlsRestController.class);

    private final ShortUrlService shortUrlService;
    private final ShortUrlResourceAssembler shortUrlResourceAssembler;

    @Autowired
    public ShortUrlsRestController(final ShortUrlService shortUrlService,
                                   final ShortUrlResourceAssembler shortUrlResourceAssembler) {
        this.shortUrlService = shortUrlService;
        this.shortUrlResourceAssembler = shortUrlResourceAssembler;
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ShortUrlResource createShortUrl(@RequestParam("url") final String url) throws BlueCowMoonException {
        return this.shortUrlResourceAssembler.toResource(this.shortUrlService.createShortUrl(url));
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PagedResources<ShortUrlResource> listShortUrls(
            @PageableDefault(size = 20, direction = Sort.Direction.DESC) final Pageable page,
            final PagedResourcesAssembler<ShortUrl> assembler
    ) throws BlueCowMoonException {
        return assembler.toResource(this.shortUrlService.list(page), this.shortUrlResourceAssembler);
    }
}
