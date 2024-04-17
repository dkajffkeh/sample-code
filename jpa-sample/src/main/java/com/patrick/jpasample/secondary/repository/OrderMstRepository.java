package com.patrick.jpasample.secondary.repository;

import com.patrick.jpasample.secondary.entity.order.OrderMst;
import org.springframework.data.repository.Repository;

public interface OrderMstRepository extends Repository<OrderMst, Long> {

    OrderMst findFirstById(Long id);

    OrderMst save(OrderMst orderMst);
}
