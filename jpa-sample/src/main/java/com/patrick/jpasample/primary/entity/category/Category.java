package com.patrick.jpasample.primary.entity.category;

import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(fetch = LAZY, mappedBy = "category")
    private List<CategoryList> categories = new ArrayList<>();

}
