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
            go "/products"

        then:
            $("h1").text() == "Product List"
            $("thead")
    }

    def "should be at ProductsPage when navigating to it - Page style"() {
        expect:
            to ProductsPage
    }

    def "should have a table row for each product"() {
        setup:
            repository.save(new Product("Product 1", "Book", 10))

        when: "go to the products list page"
            to ProductsPage

        then: "list shows one product"
            products.size() == 1
    }

    def "should go from products page to form"() {
        when: "go to the products list page"
            to ProductsPage

        and: "click new product"
            newButton.click()

        then: "navigate to the new product form page"
            at FormPage
    }

    def "should go from form to products if no errors"() {
        when: "go to the form page"
            to FormPage

        and: "fill all fields correctly"
            fillForm("dummy name", "Book", 10)

        then: "navigate to products page"
            at ProductsPage

        and: "a new element is stored in the repository"
            repository.findAll().size() == old(repository.findAll().size()) + 1
    }

    def "should go from form to products when cancel"() {
        when: "go to ProductsPage"
            to ProductsPage

        and: "click the new product button"
            newButton.click()

        then: "navigate to the form"
            at FormPage

        when: "click the cancel button"
            cancelButton.click()

        then: "it is back to the previous page"
            at ProductsPage

        and: "no elements were added to the repository"
            repository.findAll().size() == old(repository.findAll().size())
    }

    def "should clean the form when reset"() {
        when: "go to the new product form page"
            to FormPage

        and: "fill the name and reset the form"
            name = "dummy name"
            resetButton.click()

        then: "stay in the form page with name empty again"
            at FormPage
            name == ""
    }

    def "should have errors in form when name is left empty"() {
        when: "going to FormPage"
            to FormPage

        and: "name field is left empty"
            fillForm("", "Book", 10)

        then: "stay at FormPage with errors"
            at FormPage
            mayNotBeEmptyError.present

        and: "no elements were added to the repository"
            repository.findAll().size() == old(repository.findAll().size())

        cleanup: "force a new driver instance to be created next time"
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

        and: "no elements were added to the repository"
            repository.findAll().size() == old(repository.findAll().size())

        cleanup: "force a new browser instance to be created next time"
            CachingDriverFactory.clearCache()
    }

}