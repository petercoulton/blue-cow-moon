package com.petercoulton.bluecowmoon.web.hateoas.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;

public class NameResource extends Resource<String> {
    private static final Logger log = LoggerFactory.getLogger(NameResource.class);

    public NameResource(final String name) {
        super(name);
    }

    @Override
    @JsonProperty("name")
    public String getContent() {
        return super.getContent();
    }
}
