package org.MadManager.medmanager.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    private MedicineType type;

    public Medicine(String name,Double price) {
        this.name = name;
        this.price = price;
    }
    public Medicine(){}

    public Integer getId() {
        return id;
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

    public MedicineType getType() {
        return type;
    }

    public void setType(MedicineType type) {
        this.type = type;
    }
}
