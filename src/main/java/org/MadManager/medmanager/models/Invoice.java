package org.MadManager.medmanager.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
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
    private String date;

    private Double amount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "invoice_medicine", joinColumns = @JoinColumn(name ="invoice_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "medicine_id",referencedColumnName = "id"))
    private Set<Medicine> medicines;

    public Invoice(){}

    public Invoice(String patientName){
        this.patientName = patientName;
    }

    public Invoice(String patientName, Set<Medicine> medicines){
        this.patientName = patientName;
        this.medicines = medicines;
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

    public Set<Medicine> getMedicines() {
        return medicines;
    }


    public void setMedicines(Set<Medicine> medicines) {
        this.medicines = medicines;
    }

    public void addMedicine(Medicine medicine){
        medicines.add(medicine);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


}
