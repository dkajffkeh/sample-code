package com.patrick.jpasample.heap;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.Semaphore;
import org.junit.jupiter.api.Test;

public class PriorityQueueTest {

    @Test
    void priorityQueueTest() {
        PriorityQueue<Integer> integers = new PriorityQueue<>();
        Set<Integer> integerSet = new HashSet<>();
        integers.add(1);
        List<Integer> integerList = new LinkedList<>();
        integerList.add(1);
        Semaphore semaphore = new Semaphore(1);
    }

}
