package com.patrick.elasticsearchsample.infra.elasticsearch.dto;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "es-log")
public class ElasticSearchTest {

    @Id
    private String id;

    private String timestamp;

    private List<String> message;

    public ElasticSearchTest(String id, String timestamp, List<String> message) {
        this.id = id;
        this.timestamp = timestamp;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ElasticSearchTest{" +
                "id='" + id + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", message=" + message +
                '}';
    }
}
