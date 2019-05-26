package org.MadManager.medmanager.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InvoiceMedicineId implements Serializable {

    @Column(name = "invoice_id")
    private Integer invoiceId;
    @Column(name = "medicine_id")
    private Integer medicineId;

    public InvoiceMedicineId() {
    }

    public InvoiceMedicineId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public InvoiceMedicineId(Integer invoiceId, Integer medicineId) {
        this.invoiceId = invoiceId;
        this.medicineId = medicineId;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        InvoiceMedicineId that = (InvoiceMedicineId) obj;

        return Objects.equals(invoiceId, that.invoiceId) &&
                Objects.equals(medicineId, that.medicineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId,medicineId);
    }
}
