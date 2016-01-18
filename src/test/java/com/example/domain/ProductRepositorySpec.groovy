package com.example.domain
import com.example.Application
import org.springframework.boot.test.SpringApplicationConfiguration
import spock.lang.Specification

import javax.inject.Inject

@SpringApplicationConfiguration(classes = Application.class)
class ProductRepositorySpec extends Specification {

    @Inject
    private ProductRepository repository;

    def "checking init data"() {
        given: "a new element added to the empty repository"
        final Product newProduct = repository.save(new Product("dummy title", "Book", 10));

        when: "asking to find all products"
        final List<Product> products = repository.findAll();

        then: "number of stored products is 1"
        products.size() == 1;

        cleanup:
        repository.delete(newProduct);
    }

}