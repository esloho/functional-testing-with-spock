package com.example.domain
import com.example.Application
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import javax.inject.Inject

@SpringApplicationConfiguration(classes = Application.class)
@Transactional
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

        //Cleanup phase can be substituted by the @Transactional Spring's annotation
//      cleanup:
//      repository.delete(newProduct);
    }

    def "checking rollback is working"() {
        when:
        final List<Product> products = repository.findAll();

        then:
        products.size() == 0;
    }

}