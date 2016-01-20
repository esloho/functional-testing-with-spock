package com.example.functional.standard.page

import geb.Page

class ProductsPage extends Page {

    static url = "/products"

    static at = {heading.text() == "Product List"}

    static content = {
        heading { $("h1") }
        tableHeader { $("th") }

        newProduct { $("#new-link") }
    }

}
