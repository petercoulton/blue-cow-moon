package com.petercoulton.bluecowmoon.web.controllers;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class NamesRestControllerIntegrationTests extends AbstractIntegrationTest {

    @Test
    public void generatesPlainTextNames() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/api/v1/names"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void generatesNames() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/api/v1/names").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.*", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._links", Matchers.hasKey("names")));
    }
}