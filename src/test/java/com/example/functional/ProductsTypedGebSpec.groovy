package com.example.functional

import com.example.Application
import com.example.domain.ProductRepository
import com.example.content.FormTypedPage
import com.example.content.ProductsTypedPage
import geb.driver.CachingDriverFactory
import geb.spock.GebSpec
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest

import javax.inject.Inject

@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
class ProductsTypedGebSpec extends GebSpec {

    @Inject
    private ProductRepository repository;

    def "should go from products page to form"() {
        when:
            final ProductsTypedPage productsPage = to ProductsTypedPage

        then: "click new product link"
            final FormTypedPage formPage = productsPage.clickNewButton()
    }

    def "should go from form to products if no errors"() {
        when: "go to the form page"
            final FormTypedPage formPage = to FormTypedPage

        and: "fill all fields correctly"
            formPage.fillForm("dummy name", "", 10)

        then: "click the save button and should be at Products page"
            final ProductsTypedPage productsPage = formPage.clickSave()

        and:
            repository.findAll().size() == old(repository.findAll().size()) + 1
    }

    def "should go from form to products when cancel"() {
        when: "go to ProductsPage"
            final ProductsTypedPage productsPage = to ProductsTypedPage

        and: "go to FormPage"
            final FormTypedPage formPage = productsPage.clickNewButton()

        then: "click the cancel button and should be at ProductsPage"
            final ProductsTypedPage newProductsPage = formPage.clickCancel()
    }

    def "should clean the form when reset"() {
        when:
            final FormTypedPage formPage = to FormTypedPage

        and: "fill the name"
            formPage.fillForm("test", "", null)

        then: "click the reset button and should stay at form page"
            final FormTypedPage newFormPage = formPage.clickReset()

        and: "name is empty again"
            newFormPage.areFieldsEmpty()
    }

    def "should have errors in form when name is left empty"() {
        when: "going to FormPage"
            final FormTypedPage formPage = to FormTypedPage

        and: "name field is left empty"
            formPage.fillForm("", "Book", 10)

        then: "at FormPage with errors"
            final FormTypedPage newFormPage = formPage.clickSave()
            newFormPage.hasMayNotBeEmptyErrors()

        cleanup:
            CachingDriverFactory.clearCache()
    }

}