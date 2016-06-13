package com.example.content

import geb.Page
import geb.module.Select

class FormPage extends Page {

    static url = "/new"

    static at = { heading.text() == "New Product" }

    static content = {
        heading { $("h1") }

        name { $("#name") }
        category { $(name: "category").module(Select) }
        amount { $("#amount") }

        saveButton { $("#save-button") }
        cancelButton { $("#cancel-button") }
        resetButton { $("#reset-button") }

        errors(required: false) { $(".error") }
        mayNotBeEmptyError(required: false) {
            errors.filter(text: contains("may not be empty"))
        }
    }

    void fillForm (String productName, String productCaregory, int productAmount) {
        name = productName
        category.selected = productCaregory
        amount = productAmount
        saveButton.click()
    }
}
