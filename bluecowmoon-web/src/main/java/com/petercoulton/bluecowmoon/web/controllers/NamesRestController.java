package com.petercoulton.bluecowmoon.web.controllers;

import com.petercoulton.bluecowmoon.core.NameGenerator;
import com.petercoulton.bluecowmoon.web.hateoas.assemblers.NameResourceAssembler;
import com.petercoulton.bluecowmoon.web.hateoas.resources.NameResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/names")
public class NamesRestController {
    private static final Logger log = LoggerFactory.getLogger(NamesRestController.class);

    private final NameGenerator nameGenerator;
    private final NameResourceAssembler nameResourceAssembler;

    @Autowired
    public NamesRestController(final NameGenerator nameGenerator,
                               final NameResourceAssembler nameResourceAssembler) {
        this.nameGenerator = nameGenerator;
        this.nameResourceAssembler = nameResourceAssembler;
    }

    /**
     * Generates a new random name.
     *
     * @param format whether to return the name hyphenated or capitalised.
     * @param size the number of words to include in the name.
     * @return a random name.
     */
    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String generateName(@RequestParam(name = "format", defaultValue = "capitalized") String format,
                               @RequestParam(name = "size", defaultValue = "3") int size) {
        if (format.trim().equalsIgnoreCase("hyphenated")) {
            return nameGenerator.hyphenatedName(size);
        }
        return nameGenerator.capitalizedName(size);
    }

    /**
     * Generates a new random name.
     *
     * @param format whether to return the name hyphenated or capitalised.
     * @param size the number of words to include in the name.
     * @return a random name.
     */
    @GetMapping(produces = { MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public NameResource generateNameResource(@RequestParam(name = "format", defaultValue = "capitalized") String format,
                                             @RequestParam(name = "size", defaultValue = "3") int size) {
        return nameResourceAssembler.toResource(generateName(format, size));
    }
}
