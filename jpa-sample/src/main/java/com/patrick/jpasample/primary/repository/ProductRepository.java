package com.patrick.jpasample.primary.repository;

import com.patrick.jpasample.primary.entity.product.Product;
import java.util.List;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends Repository<Product, Long> {

    Product findById(Long id);

    List<Product> findAll();
}
