package com.petercoulton.bluecowmoon.web.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.petercoulton.bluecowmoon.web.hateoas.assemblers.RootResourceAssembler;
import com.petercoulton.bluecowmoon.web.hateoas.resources.RootResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1", produces = MediaTypes.HAL_JSON_VALUE)
public class RootRestController {
    private static final Logger log = LoggerFactory.getLogger(RootRestController.class);

    private final RootResourceAssembler rootResourceAssembler;

    @Autowired
    public RootRestController(final RootResourceAssembler rootResourceAssembler) {
        this.rootResourceAssembler = rootResourceAssembler;
    }

    /**
     * Returns api metadata and links to the other endpoints.
     *
     * @return root resource
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RootResource root() {
        final JsonNodeFactory factory = JsonNodeFactory.instance;
        final JsonNode metadata = factory.objectNode().set("description", factory.textNode("Blue Cow Moon v1 API"));
        return this.rootResourceAssembler.toResource(metadata);
    }
}
