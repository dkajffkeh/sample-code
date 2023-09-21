package com.patrick.reactornettyclient.reactive.sync;

import java.util.function.Consumer;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTest.class);

    @Test
    void asyncTest() {
        LOGGER.info("Start Main");
        getResult(integer -> {
            var nextValue = integer + 1;
            assert nextValue == 1;
        });
        LOGGER.info("Finish Main");
    }

    public static void getResult(Consumer<Integer> cb) {
        LOGGER.info("Start getResult");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var result = 0;
        cb.accept(result);
        LOGGER.info("Finish getResult");
    }

}
