package com.patrick.jpasample.secondary.service;

import com.patrick.jpasample.secondary.entity.order.OrderMst;
import com.patrick.jpasample.secondary.repository.OrderMstRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderMstService {

    private final OrderMstRepository orderMstRepository;

    public OrderMstService(
            OrderMstRepository orderMstRepository) {
        this.orderMstRepository = orderMstRepository;
    }

    @Transactional
    public void changeProductName() {
        OrderMst orderMst = orderMstRepository.findFirstById(1L);
        orderMst.changeName();
    }
}
