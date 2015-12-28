package com.example.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Product findById(Integer id);

    Product findByName(String name);

    @Override
    List<Product> findAll();
}
