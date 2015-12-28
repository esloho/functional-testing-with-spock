package com.example.delivery.web.spec

import com.example.Application
import com.example.delivery.web.page.ProductsPage
import geb.spock.GebReportingSpec
import org.springframework.boot.test.SpringApplicationConfiguration

@SpringApplicationConfiguration(classes = Application.class)
class ProductsReportingSpec extends GebReportingSpec {

    def "checking heading in products page"() {
        when:
        to ProductsPage

        then:
        heading == "Product Lists"
    }

}