package com.example.functional

import com.example.Application
import com.example.content.FormPage
import com.example.content.ProductsPage
import com.example.domain.Product
import com.example.domain.ProductRepository
import geb.driver.CachingDriverFactory
import geb.spock.GebSpec
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest

import javax.inject.Inject

@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
class ProductsGebSpec extends GebSpec {

    @Inject
    private ProductRepository repository;

    def "should be at ProductsPage when navigating to it"() {
        when:
            to ProductsPage

        then:
            at ProductsPage
    }

    def "should have a table row for each products"() {
        setup:
            repository.save(new Product("Product 1", "Book", 10))

        when:
            to ProductsPage

        then:
            at ProductsPage

        and: "table shows one product"
            products.size() == 1
    }

    def "should go from products page to form"() {
        when:
            to ProductsPage

        and: "click new product"
            newButton.click()

        then:
            at FormPage
    }

    def "should go from form to products if no errors"() {
        when: "go to the form page"
            to FormPage

        and: "fill all fields correctly"
            fillForm("dummy name", "Book", 10)

        then:
            at ProductsPage

        and:
            repository.findAll().size() == old(repository.findAll().size()) + 1
    }

    def "should go from form to products when cancel"() {
        when: "go to ProductsPage"
            to ProductsPage

        and:
            newButton.click()

        then:
            at FormPage

        when:
            cancelButton.click()

        then: "it is back to the previous page"
            at ProductsPage

        and:
            repository.findAll().size() == old(repository.findAll().size())
    }

    def "should clean the form when reset"() {
        when:
            to FormPage

        and: "fill the name"
            name = "dummy name"

        and:
            resetButton.click()

        then: "name is empty again"
            at FormPage
            name == ""
    }

    def "should have errors in form when name is left empty"() {
        when: "going to FormPage"
            to FormPage

        and: "name field is left empty"
            fillForm("", "Book", 10)

        then: "at FormPage with errors"
            at FormPage
            mayNotBeEmptyError.present

        and:
            repository.findAll().size() == old(repository.findAll().size())

        cleanup:
            CachingDriverFactory.clearCache()
    }

    def "should have errors in form when category is not selected"() {
        when: "go to FormPage"
            to FormPage

        and: "category field is not selected"
            fillForm("dummy name", "", 10)

        then: "at FormPage with errors"
            at FormPage
            mayNotBeEmptyError.present

        and:
            repository.findAll().size() == old(repository.findAll().size())

        cleanup:
            CachingDriverFactory.clearCache()
    }

}