package org.MadManager.medmanager.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by hiren.vaghasiya on 7/16/2017.
 */
@Entity
public class Invoice implements Serializable {

    private static final Long serialVersionID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String customerName;

    @NotNull
    private Date date;

    private Double amount;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "invoice_medicine",
//            joinColumns = @JoinColumn(name="invoice_id"),
//            inverseJoinColumns = @JoinColumn(name = "medicine_id"))
//    private Set<Medicine> medicines;



    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItem> items;

    public Invoice(){
        this.items = new ArrayList<>();
    }

    public Invoice(String customerName){
        this.customerName = customerName;
        this.date = new Date();
        this.amount = 0D;
    }

    public Invoice(String customerName, Set<InvoiceItem> invoiceItems){
        this.customerName = customerName;
    }

    public Double getTotal(){
        Double total = 0.0;
        for(int i=0; i<items.size();i++){
            total += items.get(i).calculateImport();
        }

        return total;
    }
    public Integer getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    public void addItem(InvoiceItem invoiceItem){
        this.items.add(invoiceItem);
    }


}
