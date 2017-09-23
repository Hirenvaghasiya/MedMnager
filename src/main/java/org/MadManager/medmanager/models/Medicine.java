package org.MadManager.medmanager.models;



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
    private Category category;

    @ManyToMany(mappedBy = "medicines")
    private Set<Invoice> invoices;

    public Medicine(String name,Double price) {
        this.name = name;
        this.price = price;
    }
    public Medicine(){}

    public Integer getId() {
        return id;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public Medicine(String name, Set<Invoice> invoices){
        this.name = name;
        this.invoices = invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
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
