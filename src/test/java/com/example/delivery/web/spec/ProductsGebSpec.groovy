package com.example.delivery.web.spec
import com.example.Application
import com.example.delivery.web.page.FormPage
import com.example.delivery.web.page.ProductsPage
import geb.spock.GebSpec
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest

@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest     //this allows the test to start the app instead of doing it manually before running the test
class ProductsGebSpec extends GebSpec {

    def "should go from products page to form"() {
        when:
        to ProductsPage

        then:
        at ProductsPage

        when: "click new product link"
        new_product.click()

        then:
        at FormPage
    }

    def "should go from form to products if no errors"() {
        when:
        to FormPage

        then:
        at FormPage

        when:
        name = "test product"
        category.selected = "1"
        amount = 10
        save.click()

        then:
        at ProductsPage
    }

    def "should go from form to products when cancel"() {
        when:
        to ProductsPage
        to FormPage

        then:
        at FormPage

        when:
        cancel.click()

        then:
        at ProductsPage
    }

    def "should clean the form when reset"() {
        when:
        to FormPage

        then:
        at FormPage

        when: "reset the form"
        name = "test"
        reset.click()

        then: "name is empty again"
        at FormPage
        name == ""
    }

}