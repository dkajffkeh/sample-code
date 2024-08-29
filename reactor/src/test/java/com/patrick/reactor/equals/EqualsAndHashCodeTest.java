package com.patrick.reactor.equals;

import org.junit.jupiter.api.Test;

public class EqualsAndHashCodeTest {


    @Test
    void equalsAndHashcodeTest() {
        EqualsHashCodeTest a = new EqualsHashCodeTest("a", "b");
        EqualsHashCodeTest b = new EqualsHashCodeTest("a", "b");

        System.out.println(a.equals(b));
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }


    private static class EqualsHashCodeTest {
        private String a1;

        private String a2;

        public EqualsHashCodeTest(String a1, String a2) {
            this.a1 = a1;
            this.a2 = a2;
        }


    }

}
