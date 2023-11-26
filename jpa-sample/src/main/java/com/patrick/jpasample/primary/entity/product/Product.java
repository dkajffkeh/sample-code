package com.patrick.jpasample.primary.entity.product;

import com.patrick.jpasample.primary.entity.category.CategoryList;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="product_mst")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productName;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<CategoryList> categories = new ArrayList<>();

    public Product() {}

    public Product(Long id, String productName) {
        this.id = id;
        this.productName = productName;
    }

    public void changeProductName(String productName) {
        this.productName = productName;
    }
}
