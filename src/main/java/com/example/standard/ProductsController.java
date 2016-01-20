package com.example.standard;

import com.example.domain.Product;
import com.example.domain.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class ProductsController {

    private final ProductRepository repository;

    @Inject
    ProductsController(ProductRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getProducts(Map<String, Object> model) {
        final List<Product> products = repository.findAll();
        model.put("products", products);

        return "products";
    }

    @RequestMapping("/new")
    public String getProductForm(Map<String, Object> model) {
        final ProductForm productForm = new ProductForm();
        model.put("productForm", productForm);

        return "product-form";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String createProduct(@Valid ProductForm productForm, BindingResult formBinding) {
        if (formBinding.hasErrors()) {
            return "product-form";
        }

        repository.save(new Product(productForm.getName(), productForm.getCategory(), productForm.getAmount()));

        return "redirect:/products";
    }

    @RequestMapping("/hello")
    public String getHello() {
        return "hello";
    }

}
