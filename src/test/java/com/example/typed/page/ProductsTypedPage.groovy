package com.example.typed.page

import geb.Page

class ProductsTypedPage extends Page {

    static url = "http://localhost:8080/products"; // it is the same that having: url = "products"

    static at = {heading.text() == "Product List"}

    static content = {
        heading { $("h1") }
        newProduct(to: FormTypedPage) { $("#new-link") }
    }

    FormTypedPage clickNewLink() {
        newProduct.click()

        return browser.page
    }

}
