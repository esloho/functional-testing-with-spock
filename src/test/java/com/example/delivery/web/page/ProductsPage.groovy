package com.example.delivery.web.page

import geb.Page

class ProductsPage extends Page {

    static url = "http://localhost:8080/products"; // it is the same that having: url = "products"

    static at = {heading.text() == "Product List"}

    static content = {
        heading { $("h1") }
        new_product { $("#new-link") }
    }

}
