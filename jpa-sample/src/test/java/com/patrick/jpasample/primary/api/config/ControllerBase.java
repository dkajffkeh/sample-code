package com.patrick.jpasample.primary.api.config;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

import com.patrick.jpasample.primary.api.MemberMstController;
import com.patrick.jpasample.primary.api.ProductMstController;
import com.patrick.jpasample.primary.l1.L1CacheService;
import com.patrick.jpasample.primary.repository.MemberMstRepository;
import com.patrick.jpasample.primary.service.member.MemberMstService;
import com.patrick.jpasample.primary.service.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(controllers = {ProductMstController.class, MemberMstController.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Execution(ExecutionMode.CONCURRENT) // 클래스 레벨 병렬 실행
public class ControllerBase {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @MockBean
    protected MemberMstRepository memberMstRepository;

    @MockBean
    protected L1CacheService l1CacheService;

    @MockBean
    protected MemberMstService memberMstService;

    @MockBean
    protected ProductService productService;

}
