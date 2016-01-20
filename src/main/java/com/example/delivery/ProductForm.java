package com.example.delivery;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class ProductForm {

    @NotEmpty
    private String name;

    @NotNull
    private Integer amount;

    @NotEmpty
    private String category;

    public ProductForm() {
        name = "";
        category = "";
        amount = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
