package org.MadManager.medmanager.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * Created by hiren.vaghasiya on 7/16/2017.
 */
@Entity
public class Invoice {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String patientName;

    @NotNull
    private Date date;

    private Double amount;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "invoice_medicine",
//            joinColumns = @JoinColumn(name="invoice_id"),
//            inverseJoinColumns = @JoinColumn(name = "medicine_id"))
//    private Set<Medicine> medicines;

    public Invoice(){}

    public Invoice(String patientName){
        this.patientName = patientName;
        this.date = new Date();
        this.amount = 0D;
    }

    public Invoice(String patientName, Set<InvoiceMedicine> invoiceMedicines){
        this.patientName = patientName;
    }

    public Integer getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
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


}
