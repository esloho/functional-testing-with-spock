package com.example.delivery.web.page

import geb.Page
import geb.module.Select

class FormPage extends Page {

    static url = "http://localhost:8080/new"

    static at = { heading.text() == "New Product" }

    static content = {
        heading { $("h1") }

        name { $("#name") }
        category { $(name: "category").module(Select) }
        amount { $("#amount") }

        save { $("#save-button") }
        cancel { $("#cancel-button") }
        reset { $("#reset-button") }

        errors(required: false) { $(".error") }
        mayNotBeEmptyError(required: false) {
            errors.filter(text: contains("may not be empty"))
        }
    }
}
