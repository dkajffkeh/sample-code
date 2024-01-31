package com.patrick.elasticsearchsample.infra.elasticsearch.repository;

import com.patrick.elasticsearchsample.infra.elasticsearch.dto.ElasticSearchTest;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticSearchTestRepository extends ElasticsearchRepository<ElasticSearchTest, String> {

    @NotNull
    List<ElasticSearchTest> findAll();

    List<ElasticSearchTest> findByMessageContaining(String str);

    List<ElasticSearchTest> findByMessageIn(List<String> searchStrings);

}
