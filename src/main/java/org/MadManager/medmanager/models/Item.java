package org.MadManager.medmanager.models;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * Created by Hiren on 7/11/2017.
 */
@Entity
@Table(name = "item")
@SQLDelete(sql = "UPDATE item set delete_date = current_timestamp where id = ?")
@Where(clause = "delete_date is null")
public class Item {

    @Id
    @GeneratedValue()
    private Integer id;
    @NotNull
    @Size(min = 1, message = "Enter Name")
    private String name;
    @NotNull
    private Double price;

    private Date deleteDate = null;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    public Item(String name, Double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    public Item(){}

    public Integer getId() {
        return id;
    }


    public Item(String name){
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

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", deleteDate=" + deleteDate +
                ", category=" + category +
                '}';
    }
}
