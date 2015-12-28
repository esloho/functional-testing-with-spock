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
    private Integer amount;

    /**
     * Used by JPA
     */
    private Product(){
        id = null;
    }

    Product(Integer id, String name, Integer amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public Product(String name, Integer amount) {
        this(null, name, amount);
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
                ", amount=" + amount +
                '}';
    }
}
