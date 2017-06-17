package com.petercoulton.bluecowmoon.web.hateoas.assemblers;

import com.fasterxml.jackson.databind.JsonNode;
import com.petercoulton.bluecowmoon.web.hateoas.resources.RootResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class RootResourceAssembler implements ResourceAssembler<JsonNode, RootResource> {
    private static final Logger log = LoggerFactory.getLogger(RootResourceAssembler.class);

    @Override
    public RootResource toResource(final JsonNode metadata) {
        return new RootResource(metadata);
    }
}
