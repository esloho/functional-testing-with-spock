package com.example.standard.spec

import com.example.Application
import com.example.standard.page.FormPage
import com.example.standard.page.ProductsPage
import geb.spock.GebReportingSpec
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import spock.lang.Ignore

@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
class ProductsReportingSpec extends GebReportingSpec {

    @Ignore
    def "checking heading in products page - example of failure and reporting"() {
        when:
        to ProductsPage

        then:
        at FormPage
    }

}