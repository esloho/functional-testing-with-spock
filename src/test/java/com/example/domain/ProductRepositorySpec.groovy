package com.example.domain
import com.example.Application
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification
import spock.lang.Stepwise

import javax.inject.Inject

@SpringApplicationConfiguration(classes = Application.class)
@Transactional
@Stepwise
class ProductRepositorySpec extends Specification {

    @Inject
    private ProductRepository repository;

    def "should add a product in the repository"() {
        given: "a new product"
            final Product newProduct = new Product("dummy title", "Book", 10);

        when: "a the element is added to the empty repository"
            repository.save(newProduct);

        then: "number of stored products is 1"
            repository.findAll().size() == 1;

        //Cleanup phase can be substituted by the @Transactional Spring's annotation
//      cleanup:
//      repository.delete(newProduct);
    }

    def "check rollback is working"() {
        when: "retrieving the existing products in the repo"
            final List<Product> products = repository.findAll();

        then: "the list (and thus the repo) is empty"
            products.size() == 0;
    }

}