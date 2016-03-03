package com.example.functional

import com.example.Application
import com.example.content.FormPage
import com.example.content.ProductsPage
import geb.spock.GebReportingSpec
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest

@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
class ProductsReportingSpec extends GebReportingSpec {

//    @Ignore
    def "checking heading in products page - example of failure and reporting"() {
        when:
        to ProductsPage

        then:
        at FormPage
    }

}