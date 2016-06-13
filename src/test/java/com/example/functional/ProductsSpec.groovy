package com.example.functional

import com.example.Application
import geb.Browser
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import spock.lang.Specification

@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest //these 2 annotations allows the test to start the app instead of doing it manually before running the test
class ProductsSpec extends Specification {

    def "checking heading in products page"() {
        given:
            final Browser browser = new Browser();

        when:
            browser.go("/products");

        then:
            browser.page.$("h1").text() == "Product List";
    }

}