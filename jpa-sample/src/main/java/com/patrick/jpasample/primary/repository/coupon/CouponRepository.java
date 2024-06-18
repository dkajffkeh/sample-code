package com.patrick.jpasample.primary.repository.coupon;

import com.patrick.jpasample.primary.entity.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
