package com.example.functional.typed.page

import geb.Page
import geb.module.Select

class FormTypedPage extends Page {

    static url = "/new"

    static at = { heading.text() == "New Product" }

    static content = {
        heading { $("h1") }

        name { $("#name") }
        category { $(name: "category").module(Select) }
        amount { $("#amount") }

        save(to: [ProductsTypedPage, FormTypedPage]) { $("#save-button") }
        cancel { $("#cancel-button") }
        reset(to: FormTypedPage) { $("#reset-button") }

        errors(required: false) { $(".error") }
        mayNotBeEmptyError(required: false) {
            errors.filter(text: contains("may not be empty"))
        }
    }

    void fillForm(String inputName, String inputCategory, Integer inputAmount) {
        name.value(inputName)
        category.selected = inputCategory

        if (inputAmount != null)
        {
            amount.value(inputAmount)
        }
    }

    boolean areFieldsEmpty() {
        return (name.value() == "" || name.value() == null) && category.selected == "" && amount.value() == "0"
    }

    Object clickSave() {
        save.click()
        return browser.page
    }

    Object clickCancel() {
        cancel.click()
        return browser.page
    }

    FormTypedPage clickReset() {
        reset.click()
        return browser.page
    }
}
