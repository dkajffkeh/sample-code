package com.patrick.jpasample.primary.api;

import static com.patrick.jpasample.ApiDocumentUtils.getDocumentRequest;
import static com.patrick.jpasample.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;

import com.patrick.jpasample.primary.api.config.ControllerBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class ProductMstControllerTest extends ControllerBase {

    private final ManualRestDocumentation restDocumentation = new ManualRestDocumentation();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
        restDocumentation.beforeTest(getClass(), "ProductMstControllerTest");
    }


    @AfterEach
    void tearDown() {
        // REST Docs 테스트 종료
        restDocumentation.afterTest();
    }

    @Test
    @DisplayName("ProductTest")
    void mockTest() throws Exception {
        mockMvc.perform(post("/api/v1/artwork/screening/ocr-completion"))
                .andDo(document("complete-ocr-artwork-screening",
                        getDocumentRequest(),
                        getDocumentResponse()
                ));
        System.out.println(mockMvc);
    }

    @Test
    @DisplayName("whatThe")
    void mockTest2() throws Exception {
        mockMvc.perform(post("/api/v1/artwork/screening/ocr-completion"))
                .andDo(document("complete-ocr-artwork-screening",
                        getDocumentRequest(),
                        getDocumentResponse()
                ));
        System.out.println(mockMvc);
    }
}
