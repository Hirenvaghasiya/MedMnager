package org.MadManager.medmanager.dao;

import org.MadManager.medmanager.models.InvoiceMedicine;
import org.MadManager.medmanager.models.InvoiceMedicineId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceMedicineRepository extends CrudRepository<InvoiceMedicine, InvoiceMedicineId> {
    List<InvoiceMedicine> findByInvoiceId(Integer invoiceId);
}
