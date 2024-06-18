package com.patrick.reactor.parellel;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ParallelismTest {

    @Test
    void parallelismTest() {
        int cpuCores = Runtime.getRuntime().availableProcessors() * 2;
        List<Integer> integers = new ArrayList<>();

    }

}
