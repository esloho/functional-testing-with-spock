package com.example.functional.standard.spec

import com.example.Application
import com.example.functional.standard.page.ProductsPage
import geb.Browser
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import spock.lang.Specification

@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
class ProductsSpec extends Specification {

    def "checking heading in products page"() {
        given:
        final Browser browser = new Browser();

        when:
        browser.to ProductsPage

        then:
        browser.at ProductsPage // same as: browser.page.heading.text() == "Product List"
    }

}