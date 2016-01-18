package com.example.delivery.web.spec
import com.example.Application
import com.example.delivery.web.page.FormPage
import com.example.delivery.web.page.ProductsPage
import com.example.domain.Product
import com.example.domain.ProductRepository
import geb.driver.CachingDriverFactory
import geb.spock.GebSpec
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest

import javax.inject.Inject

@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest     //this allows the test to start the app instead of doing it manually before running the test
class ProductsGebSpec extends GebSpec {

    @Inject
    private ProductRepository repository;

    def "should have a table head at products page"() {
        given:
        final newProduct = repository.save(new Product("Product 1", "Category", 10));

        when:
        to ProductsPage

        then:
        at ProductsPage

        and: "table head has left aligning and 10px right padding"
        table_header.css("text-align") == "left"
        table_header.css("padding-right") == "10px"

        cleanup:
        repository.delete(newProduct);

    }

    def "should go from products page to form"() {
        when:
        to ProductsPage

        and: "click new product link"
        new_product.click()

        then:
        at FormPage
    }

    def "should go from form to products if no errors"() {
        when: "go to the form page"
        to FormPage

        and: "fill all fields correctly"
        name = "test product"
        category.selected = "1"
        amount = 10

        and: "hit the save button"
        save.click()

        then:
        at ProductsPage
    }

    def "should go from form to products when cancel"() {
        when: "go to ProductsPage"
        to ProductsPage

        and: "go to FormPage"
        to FormPage

        and: "hit the cancel button"
        cancel.click()

        then: "back to the previous page"
        at ProductsPage
    }

    def "should clean the form when reset"() {
        when:
        to FormPage

        and: "fill the name"
        name = "test"

        and: "reset the form"
        reset.click()

        then: "name is empty again"
        at FormPage
        name == ""
    }

    def "should have errors in form when name is left empty"() {
        when: "going to FormPage"
        to FormPage

        and: "name field is left empty"
        category.selected = "1"
        amount = 10
        save.click()

        then: "at FormPage with errors"
        at FormPage
        mayNotBeEmptyError.present

        cleanup:
        CachingDriverFactory.clearCache()
    }

    def "should have errors in form when category is not selected"() {
        when: "go to FormPage"
        to FormPage

        and: "category field is not selected"
        name = "test name"
        amount = 10
        save.click()

        then: "at FormPage with errors"
        at FormPage
        mayNotBeEmptyError.present

        cleanup:
        CachingDriverFactory.clearCache()
    }

}