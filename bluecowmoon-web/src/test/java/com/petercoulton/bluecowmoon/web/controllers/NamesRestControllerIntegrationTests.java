package com.petercoulton.bluecowmoon.web.controllers;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.restdocs.hypermedia.HypermediaDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

public class NamesRestControllerIntegrationTests extends AbstractIntegrationTest {

    @Test
    public void generatesPlainTextNames() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/api/v1/names"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void generatesNames() throws Exception {
        final RestDocumentationResultHandler documentationResultHandler = MockMvcRestDocumentation.document(
                "{class-name}/{method-name}/{step}",
                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                RequestDocumentation.requestParameters(
                        Arrays.asList(
                                RequestDocumentation.parameterWithName("format")
                                        .description("Format of the name, hyphenated or capitalised")
                                        .optional(),
                                RequestDocumentation.parameterWithName("size")
                                        .description("The number of words to include in the name")
                                        .optional()
                        )
                ),
                HeaderDocumentation.responseHeaders(
                        HeaderDocumentation.headerWithName(HttpHeaders.CONTENT_TYPE).description(MediaTypes.HAL_JSON_VALUE)
                ),
                PayloadDocumentation.responseFields(
                        PayloadDocumentation
                                .fieldWithPath("name")
                                .description("The new name generated")
                                .attributes(Attributes.key("contraints").value(""))

                ).and(
                        PayloadDocumentation
                                .fieldWithPath("_links")
                                .attributes(Attributes.key("constraints").value(""))
                                .description("<<_hateoas,Links>> to other resources.")
                                .ignored()
                ),
                HypermediaDocumentation.links(
                        HypermediaDocumentation
                                .linkWithRel("names")
                                .description("Name generation endpoint")
                )
        );

        this.mvc.perform(MockMvcRequestBuilders.get("/api/v1/names").accept(MediaTypes.HAL_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.*", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._links", Matchers.hasKey("names")))
                .andDo(documentationResultHandler);
    }
}