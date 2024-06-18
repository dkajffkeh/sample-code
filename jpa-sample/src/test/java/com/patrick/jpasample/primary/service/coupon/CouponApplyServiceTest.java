package com.patrick.jpasample.primary.service.coupon;

import static org.assertj.core.api.Assertions.assertThat;

import com.patrick.jpasample.primary.repository.coupon.CouponRepository;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CouponApplyServiceTest {

    @Autowired
    private CouponApplyService couponApplyService;

    @Autowired
    private CouponRepository couponRepository;

    @Test
    @Transactional
    void applyOnce() {
        couponApplyService.couponApply(1L);

        long count = couponRepository.count();

        assertThat(count).isEqualTo(1L);
    }

    @Test
    @Transactional
    void multipleApply() throws InterruptedException {
        int threadCount = 1000;

        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0 ; i < threadCount; i++) {
            long userid = i;
            executorService.submit(() -> {
                try {
                    couponApplyService.couponApply(userid);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        long count = couponRepository.count();

        assertThat(count).isEqualTo(100);
    }
}
