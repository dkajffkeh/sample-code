package com.patrick.jpasample.primary.service.product;

import com.patrick.jpasample.primary.entity.product.Product;
import com.patrick.jpasample.primary.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> returnAll() {
        return productRepository.findAll();
    }

    @Transactional
    public List<Product> dirtyCheckingTest() {
        Product product = productRepository.findById(1L);
        product.changeProductName("요넥스 시발");
        return returnAll();
    }
}
