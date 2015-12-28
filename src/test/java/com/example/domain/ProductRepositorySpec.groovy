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
        given:
        repository.save(new Product("dummy name", 10));

        when:
        final List<Product> products = repository.findAll();

        then:
        products.size() == 4;
    }

}