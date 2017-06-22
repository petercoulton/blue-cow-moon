package com.petercoulton.bluecowmoon.web.hateoas.assemblers;

import com.petercoulton.bluecowmoon.core.dto.ShortUrl;
import com.petercoulton.bluecowmoon.web.controllers.ShortUrlController;
import com.petercoulton.bluecowmoon.web.controllers.ShortUrlsRestController;
import com.petercoulton.bluecowmoon.web.hateoas.resources.ShortUrlResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlResourceAssembler implements ResourceAssembler<ShortUrl, ShortUrlResource> {
    private static final Logger log = LoggerFactory.getLogger(ShortUrlResourceAssembler.class);

    @Override
    public ShortUrlResource toResource(final ShortUrl shortUrl) {
        final ShortUrlResource resource = new ShortUrlResource(shortUrl);
        resource.add(
                ControllerLinkBuilder
                        .linkTo(ShortUrlsRestController.class)
                        .withRel("short-urls")
        );
        resource.add(
                ControllerLinkBuilder
                        .linkTo(ShortUrlController.class)
                        .slash(shortUrl.getShortUrlName())
                        .withRel("short-url")
        );
        return resource;
    }
}
