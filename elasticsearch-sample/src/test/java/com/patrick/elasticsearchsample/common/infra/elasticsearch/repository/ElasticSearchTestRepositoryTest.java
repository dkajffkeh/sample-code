package com.patrick.elasticsearchsample.common.infra.elasticsearch.repository;

import com.patrick.elasticsearchsample.infra.elasticsearch.dto.ElasticSearchTest;
import com.patrick.elasticsearchsample.infra.elasticsearch.repository.ElasticSearchTestRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElasticSearchTestRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchTestRepositoryTest.class);

    @Autowired
    private ElasticSearchTestRepository elasticSearchTestRepository;

    @Test
    void selectTest() {
        List<ElasticSearchTest> responses = elasticSearchTestRepository.findAll();
        LOGGER.info(responses.toString());
    }

    @Test
    void selectContaning() {
        List<ElasticSearchTest> responses = elasticSearchTestRepository.findByMessageContaining("Users");
        LOGGER.info(responses.toString());
    }

    @Test
    void selectIns() {
        List<ElasticSearchTest> responses = elasticSearchTestRepository.findByMessageIn(
                Arrays.asList("Users","django"));
        LOGGER.info(responses.toString());
    }
}
