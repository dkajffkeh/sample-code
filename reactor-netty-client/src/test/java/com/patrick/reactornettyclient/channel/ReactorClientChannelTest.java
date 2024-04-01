package com.patrick.reactornettyclient.channel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.Test;

class ReactorClientChannelTest {

    @Test
    void startupTest() throws Exception {
        new ReactorClientChannel("127.0.0.1", 9992, true).startup(1);
    }

}
