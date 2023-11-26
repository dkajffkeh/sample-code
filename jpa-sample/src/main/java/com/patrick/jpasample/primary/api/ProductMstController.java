package com.patrick.jpasample.primary.api;

import com.patrick.jpasample.primary.service.product.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductMstController {

    private final ProductService productService;

    public ProductMstController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/product/dirty-checking")
    public void dirtyCheckingTest() {
        productService.dirtyCheckingTest();
    }

}
