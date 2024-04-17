package com.patrick.jpasample.secondary.api;

import com.patrick.jpasample.secondary.service.OrderMstService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/orders")
@RestController
public class OrderController {

    private final OrderMstService orderMstService;

    public OrderController(OrderMstService orderMstService) {
        this.orderMstService = orderMstService;
    }

    @GetMapping
    public void changeOrderStatus() {
        orderMstService.changeProductName();
    }
}
