package org.MadManager.medmanager.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
public class InvoiceMedicine {

    @EmbeddedId
    private InvoiceMedicineId id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(
                    name = "invoice_id",
                    referencedColumnName = "id",
                    insertable = false,
                    updatable = false
            )
    })
    private Invoice invoice;
    private Integer quanitity;
    private Double unitPrice;

    public InvoiceMedicine() {
    }

    public InvoiceMedicine(Invoice invoice, Medicine medicine, Integer quanitity) {
        this.id= new InvoiceMedicineId(invoice.getId(),medicine.getId());
        this.quanitity = quanitity;
    }

    public InvoiceMedicineId getId() {
        return id;
    }

    public void setId(InvoiceMedicineId id) {
        this.id = id;
    }

    public Integer getQuanitity() {
        return quanitity;
    }

    public void setQuanitity(Integer quanitity) {
        this.quanitity = quanitity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
