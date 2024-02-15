package com.patrick.elasticsearchsample.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class LogGenerator {

    private static final Logger log = LoggerFactory.getLogger(LogGenerator.class);

    @Scheduled(cron = "0/30 * * * * *")
    void exceptionLogGen() {
        throw new RuntimeException("Exception!!");
    }

    @Scheduled(cron = "0/50 * * * * *")
    void normalLogGen() {
        log.info("Hello World!");
    }
}
