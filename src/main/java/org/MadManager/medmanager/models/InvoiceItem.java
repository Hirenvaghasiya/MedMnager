package org.MadManager.medmanager.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class InvoiceItem implements Serializable {

    private static final Long serialVersionID = 1L;

    @Id
    @GeneratedValue
    private Integer id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item item;
    private Integer quantity;
    private Double unitPrice;

    public InvoiceItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double calculateImport(){
        return quantity.doubleValue() * item.getPrice();
   }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
