package org.MadManager.medmanager.models;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Hiren on 7/11/2017.
 */
@Entity
public class Medicine {

    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    @Size(min = 1, message = "Enter Name")
    private String name;
    @NotNull
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    public Medicine(String name,Double price,Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    public Medicine(){}

    public Integer getId() {
        return id;
    }


    public Medicine(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

}
