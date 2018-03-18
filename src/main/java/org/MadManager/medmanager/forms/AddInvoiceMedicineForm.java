package org.MadManager.medmanager.forms;

import org.MadManager.medmanager.models.Invoice;
import org.MadManager.medmanager.models.Medicine;

import javax.validation.constraints.NotNull;

/**
 * Created by hiren.vaghasiya on 7/23/2017.
 */
public class AddInvoiceMedicineForm {

    @NotNull
    private Integer invoiceId;

    @NotNull
    private Integer medicineId;

    private Iterable<Medicine> medicines;

    private Invoice invoice;

    public AddInvoiceMedicineForm() {  }

    public AddInvoiceMedicineForm(Iterable<Medicine> medicines, Invoice invoice) {
        this.medicines = medicines;
        this.invoice = invoice;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public Iterable<Medicine> getMedicines() {
        return medicines;
    }

    public Invoice getInvoice() {
        return invoice;
    }
}
