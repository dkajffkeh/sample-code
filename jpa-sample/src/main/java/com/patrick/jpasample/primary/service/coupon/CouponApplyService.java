package com.patrick.jpasample.primary.service.coupon;

import com.patrick.jpasample.infra.kafka.producer.CouponCreateProducer;
import com.patrick.jpasample.infra.redis.repository.CouponCountRepository;
import com.patrick.jpasample.primary.entity.coupon.Coupon;
import com.patrick.jpasample.primary.repository.coupon.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class CouponApplyService {

    private final CouponRepository couponRepository;

    private final CouponCountRepository couponCountRepository;

    private final CouponCreateProducer couponCreateProducer;

    public CouponApplyService(
            CouponRepository couponRepository,
            CouponCountRepository couponCountRepository,
            CouponCreateProducer couponCreateProducer) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
    }

    public void couponApply(Long userId) {
        long count = couponCountRepository.increment();

        if(count > 100) {
            return;
        }

        couponCreateProducer.create(userId);
    }
}
