package org.MadManager.medmanager.controller;

import org.MadManager.medmanager.dao.InvoiceDao;
import org.MadManager.medmanager.dao.ItemDao;
import org.MadManager.medmanager.models.*;
import org.MadManager.medmanager.payload.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Created by hiren.vaghasiya on 7/18/2017.
 */
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);
    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private ItemDao itemDao;

//    @Autowired
//    private InvoiceMedicineRepository invoiceMedicineRepository;

    @GetMapping("/{id}")
    public Optional<Invoice> getInvoice(@PathVariable Integer id){
        return invoiceDao.findById(id);
    }

//    @GetMapping("/{id}/details")
//    public Iterable<Object> getDetailedInvoice(@PathVariable Integer id){
//        List<Object> result = new ArrayList<>();
//        result.add(getInvoice(id));
//        result.add(invoiceMedicineRepository.findByInvoiceId(id));
//        return result;
//    }

    @GetMapping
    Iterable<Invoice> getInvoices(){
        return invoiceDao.findAll();
    }
    @PostMapping
    public Invoice createInvoice(@Valid @RequestBody Invoice newInvoiceRequest){
        LOGGER.info("New invoice request");
        Invoice newInvoice = new Invoice();
        newInvoice.setCustomerName(newInvoiceRequest.getCustomerName());
        List<InvoiceItem> itemList = newInvoiceRequest.getItems();
        if(itemList != null && itemList.size() > 0)
        {
          for (int i=0; i<itemList.size(); i++)
          {
             Item item = itemDao.findById(itemList.get(i).getId()).orElse(new Item());
             InvoiceItem line = new InvoiceItem();
             line.setQuantity(itemList.get(i).getQuantity());
             line.setItem(item);
             newInvoice.addItem(line);
          }
          newInvoice.setAmount(newInvoice.getTotal());
          newInvoice.setDate(newInvoiceRequest.getDate());
        }
        Invoice result = invoiceDao.save(newInvoice);

        LOGGER.info("New invoice created with id: {}",newInvoice.getId());

        return result;


    }

//    @PostMapping("/addMedicine")
//    public ResponseEntity<?> addMedicineToInvoice(@Valid @RequestBody AddMedicineToInvoice addMedicineToInvoice){
//
//        Invoice invoice = invoiceDao.findOne(addMedicineToInvoice.getInvoiceId());
//        if(null == invoice){
//            LOGGER.info("Invoice not found with id: {}",addMedicineToInvoice.getInvoiceId());
//        }
//
//        Item newItem = itemDao.findByName(addMedicineToInvoice.getMedicineName())
//                                            .orElseThrow(()-> new UsernameNotFoundException("Medicine not found"));
//        InvoiceItem newInvoiceItem = new InvoiceItem();
//        newInvoiceItem.setUnitPrice(newItem.getPrice());
//        invoice.setAmount(invoice.getAmount() + (newItem.getPrice() * addMedicineToInvoice.getQuantity()));
//
//        InvoiceItem result = invoiceMedicineRepository.save(newInvoiceItem);
//
//        URI location = ServletUriComponentsBuilder
//                        .fromCurrentContextPath()
//                            .path("/invoice/{id}").buildAndExpand().toUri();
//        return ResponseEntity.created(location).body(new APIResponse(true,"Medicine added to invoice"));
//    }


}
