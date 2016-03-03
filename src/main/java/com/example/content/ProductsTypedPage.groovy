package com.example.content

import geb.Page

class ProductsTypedPage extends Page {

    static url = "/products"

    static at = {heading.text() == "Product List"}

    static content = {
        heading { $("h1") }
        newButton(to: FormTypedPage) { $("#new-product") }
    }

    FormTypedPage clickNewButton() {
        newButton.click()

        return browser.page
    }

}
