package org.MadManager.medmanager.payload;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Set;

public class NewInvoiceRequest {

    private String patientName;

    private Double amount;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
