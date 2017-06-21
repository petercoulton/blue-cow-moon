package com.petercoulton.bluecowmoon.web.hateoas.assemblers;

import com.petercoulton.bluecowmoon.web.controllers.NamesRestController;
import com.petercoulton.bluecowmoon.web.hateoas.resources.NameResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class NameResourceAssembler implements ResourceAssembler<String, NameResource> {
    private static final Logger log = LoggerFactory.getLogger(NameResourceAssembler.class);

    @Override
    public NameResource toResource(final String name) {
        final NameResource resource = new NameResource(name);
        resource.add(
                ControllerLinkBuilder.linkTo(NamesRestController.class).withRel("names")
        );
        return resource;
    }
}
