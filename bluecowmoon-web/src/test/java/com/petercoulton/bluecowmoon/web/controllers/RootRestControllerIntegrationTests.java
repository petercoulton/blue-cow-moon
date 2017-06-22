package com.petercoulton.bluecowmoon.web.controllers;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class RootRestControllerIntegrationTests extends AbstractIntegrationTest {

    @Test
    public void linksToResources() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/api/v1/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.*", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._links", Matchers.hasKey("names")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._links", Matchers.hasKey("short-urls")));
    }
}