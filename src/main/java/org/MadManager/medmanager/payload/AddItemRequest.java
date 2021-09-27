package org.MadManager.medmanager.payload;

import org.MadManager.medmanager.models.Category;

public class AddItemRequest {

    private String name;
    private Category category;
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
