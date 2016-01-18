package com.example.delivery.web.spec
import com.example.Application
import com.example.delivery.web.page.ProductsPage
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
        browser.page.heading.text() == "Product List"
    }

}