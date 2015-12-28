package com.example.delivery.web;

import com.example.domain.Product;
import com.example.domain.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Controller
public class ProductsController {

    private final ProductRepository repository;

    @Inject
    ProductsController(ProductRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/products")
    public String getProducts(Map<String, Object> model) {
        final List<Product> products = repository.findAll();
        model.put("products", products);

        return "products";
    }

    @RequestMapping("/hello")
    public String getHello() {
        return "hello";
    }

}
