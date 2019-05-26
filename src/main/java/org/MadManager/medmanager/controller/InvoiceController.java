package org.MadManager.medmanager.controller;

import org.MadManager.medmanager.dao.CategoryDao;
import org.MadManager.medmanager.dao.InvoiceDao;
import org.MadManager.medmanager.dao.InvoiceMedicineRepository;
import org.MadManager.medmanager.dao.MedicineDao;
import org.MadManager.medmanager.models.*;
import org.MadManager.medmanager.payload.APIResponse;
import org.MadManager.medmanager.payload.AddMedicineToInvoice;
import org.MadManager.medmanager.payload.NewInvoiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;

/**
 * Created by hiren.vaghasiya on 7/18/2017.
 */
@RestController
@RequestMapping("invoice")
public class InvoiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);
    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private MedicineDao medicineDao;

    @Autowired
    private InvoiceMedicineRepository invoiceMedicineRepository;

    @GetMapping("/{id}")
    public Invoice getInvoice(@PathVariable Integer id){
        return invoiceDao.findOne(id);
    }

    @GetMapping("/{id}/details")
    public Iterable<Object> getDetailedInvoice(@PathVariable Integer id){
        List<Object> result = new ArrayList<>();
        result.add(getInvoice(id));
        result.add(invoiceMedicineRepository.findByInvoiceId(id));
        return result;
    }

    @PostMapping
    public ResponseEntity<?> createInvoice(@Valid @RequestBody NewInvoiceRequest newInvoiceRequest){
        LOGGER.info("New invoice request");
        Invoice newInvoice = new Invoice(newInvoiceRequest.getPatientName());
        Invoice result = invoiceDao.save(newInvoice);

        LOGGER.info("New invoice created with id: {}",newInvoice.getId());

        URI location = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/invoice/{id}")
                        .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).body( new APIResponse(true,"Invoice created successflly"));


    }

    @PostMapping("/addMedicine")
    public ResponseEntity<?> addMedicineToInvoice(@Valid @RequestBody AddMedicineToInvoice addMedicineToInvoice){

        Invoice invoice = invoiceDao.findOne(addMedicineToInvoice.getInvoiceId());
        if(null == invoice){
            LOGGER.info("Invoice not found with id: {}",addMedicineToInvoice.getInvoiceId());
        }

        Medicine newMedicine = medicineDao.findByName(addMedicineToInvoice.getMedicineName())
                                            .orElseThrow(()-> new UsernameNotFoundException("Medicine not found"));
        InvoiceMedicine newInvoiceMedicine = new InvoiceMedicine(invoice,newMedicine,addMedicineToInvoice.getQuantity());
        newInvoiceMedicine.setUnitPrice(newMedicine.getPrice());
        invoice.setAmount(invoice.getAmount() + (newMedicine.getPrice() * addMedicineToInvoice.getQuantity()));

        InvoiceMedicine result = invoiceMedicineRepository.save(newInvoiceMedicine);

        URI location = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                            .path("/invoice/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).body(new APIResponse(true,"Medicine added to invoice"));
    }


}
