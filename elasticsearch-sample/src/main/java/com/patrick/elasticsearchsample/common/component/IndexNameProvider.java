package com.patrick.elasticsearchsample.common.component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class IndexNameProvider {

    public String getIndexName() {
        // Calculate the index name dynamically based on the current date
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return "test-" + currentDate.format(formatter);
    }
}
