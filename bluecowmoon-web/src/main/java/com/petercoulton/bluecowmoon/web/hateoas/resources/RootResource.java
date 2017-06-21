package com.petercoulton.bluecowmoon.web.hateoas.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.hateoas.Resource;


public class RootResource extends Resource<JsonNode> {
    @JsonCreator
    public RootResource(final JsonNode metadata) {
        super(metadata);
    }

    @Override
    @JsonProperty("metadata")
    public JsonNode getContent() {
        return super.getContent();
    }
}
