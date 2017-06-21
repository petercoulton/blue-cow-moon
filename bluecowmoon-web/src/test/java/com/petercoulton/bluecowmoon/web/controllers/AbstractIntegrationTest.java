package com.petercoulton.bluecowmoon.web.controllers;

import com.petercoulton.bluecowmoon.BlueCowMoonWeb;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlueCowMoonWeb.class, webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureRestDocs(
        value = "build/generated-snippets",
        uriHost = "blue-cow-moon.mutabl.io",
        uriScheme = "https",
        uriPort = 443
)
public abstract class AbstractIntegrationTest {
    private static final Logger log = LoggerFactory.getLogger(AbstractIntegrationTest.class);

    @Autowired
    protected MockMvc mvc;
}
