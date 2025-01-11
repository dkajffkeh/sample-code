package com.patrick.jpasample.sort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class SortTestClass {

    @Test
    void sortTestMethod() {
        List<WrapperSort> list = new ArrayList<>();
        list.add(new WrapperSort(new SortTest(2L, new BigDecimal("17.30000")), null));
        list.add(new WrapperSort(new SortTest(7L, new BigDecimal("16.8")), null));
        list.add(new WrapperSort(new SortTest(3L, new BigDecimal("17.20000")), null));
        list.add(new WrapperSort(new SortTest(1L, new BigDecimal("17.4")), null));
        list.add(new WrapperSort(new SortTest(9L, null), null));
        list.add(new WrapperSort(new SortTest(5L, new BigDecimal("17.0")), null));
        list.add(new WrapperSort(new SortTest(4L, new BigDecimal("17.1000")), null));
        list.add(new WrapperSort(new SortTest(6L, new BigDecimal("16.9")), null));
        list.add(new WrapperSort(new SortTest(8L, new BigDecimal("16.7")), null));
        list.add(new WrapperSort(new SortTest(10L, null), null));
        List<WrapperSort> newList = list.stream()
                .filter(element -> element.getSortAnother() == null)
                .sorted(Comparator.comparing(
                element -> element.getSortTest().getWt(),
                Comparator.nullsLast(Comparator.reverseOrder())
        )).collect(Collectors.toList());
        System.out.println(newList);
    }

    private static class WrapperSort {

        private SortTest sortTest;

        private SortAnother sortAnother;

        public WrapperSort(SortTest sortTest,
                SortAnother sortAnother) {
            this.sortTest = sortTest;
            this.sortAnother = sortAnother;
        }

        public SortAnother getSortAnother() {
            return sortAnother;
        }

        public SortTest getSortTest() {
            return sortTest;
        }

        @Override
        public String toString() {
            return "WrapperSort{" +
                    "sortTest=" + sortTest +
                    '}';
        }
    }

    private static class SortAnother {
        private Long id;
        private BigDecimal wt;

        public SortAnother(Long id, BigDecimal wt) {
            this.id = id;
            this.wt = wt;
        }

        @Override
        public String toString() {
            return "SortAnother{" +
                    "id=" + id +
                    ", wt=" + wt +
                    '}';
        }
    }

    private static class SortTest {

        private Long id;

        private BigDecimal wt;

        public SortTest(Long id, BigDecimal wt) {
            this.id = id;
            this.wt = wt;
        }

        public Long getId() {
            return id;
        }

        public BigDecimal getWt() {
            return wt;
        }

        @Override
        public String toString() {
            return "SortTest{" +
                    "id=" + id +
                    ", wt=" + wt +
                    '}';
        }
    }

}
