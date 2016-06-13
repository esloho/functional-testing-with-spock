package com.example.content

import geb.Page

class ProductsPage extends Page {

    static url = "/products"

    static at = {
        heading.text() == "Product List"
        tableHeader
    }

    static content = {
        heading { $("h1") }
        tableHeader { $("thead") }
        products { $("tbody > tr") }
        newButton { $("#new-product") }
    }

}
