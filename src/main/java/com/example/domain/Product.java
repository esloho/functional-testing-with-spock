package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final Integer id;
    private String name;
    private String category;
    private Integer amount;

    /**
     * Used by JPA
     */
    private Product(){
        id = null;
    }

    Product(Integer id, String name, String category, Integer amount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.amount = amount;
    }

    public Product(String name, String category, Integer amount) {
        this(null, name, category, amount);
    }

    public Integer getId() {
        return id;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        final Product other = (Product) obj;

        return id.equals(other.id)
                && name.equals(other.name)
                && category.equals(other.category)
                && amount == other.amount;

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}
