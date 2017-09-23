package org.MadManager.medmanager.controller;

import org.MadManager.medmanager.models.Invoice;
import org.MadManager.medmanager.models.Medicine;
import org.MadManager.medmanager.models.dao.InvoiceDao;
import org.MadManager.medmanager.models.dao.MedicineDao;
import org.MadManager.medmanager.models.forms.AddInvoiceMedicineForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by hiren.vaghasiya on 7/18/2017.
 */
@Controller
@RequestMapping("invoice")
public class InvoiceController {

    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private MedicineDao medicineDao;


    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("title","Invoice");
        model.addAttribute("invoices",invoiceDao.findAll());
        model.addAttribute(new Invoice());
        model.addAttribute("medicines",medicineDao.findAll());

        return "invoice/index";
    }

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String addDisplay(Model model){
        model.addAttribute("title","Add Invoice");
        model.addAttribute(new Invoice());
        model.addAttribute("medicines",medicineDao.findAll());

        return "invoice/add";
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String processAdd(@ModelAttribute @Valid Invoice newInvoice, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title","Add Invoice");
            return "invoice/add";
        }

        invoiceDao.save(newInvoice);
        return "redirect:add-medicine?invoiceId="+newInvoice.getId();
    }

    @RequestMapping(value = "add-medicine", method = RequestMethod.GET, params = {"invoiceId"})
    public String addMedicine(Model model, @RequestParam("invoiceId") Integer invoiceId){
        Invoice invoice = invoiceDao.findOne(invoiceId);

        AddInvoiceMedicineForm form = new AddInvoiceMedicineForm(medicineDao.findAll(),invoice);
        model.addAttribute("title","Add Medicien to Invoice: " + invoice.getPatientName());
        model.addAttribute("form",form);
        model.addAttribute("invoice",invoice);
        return "invoice/add-medicine";
    }

    @RequestMapping(value = "add-medicine",method = RequestMethod.POST)
    public String addMedicine(Model model, @ModelAttribute @Valid AddInvoiceMedicineForm form, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("title","Add Medicine to Invoice");
            model.addAttribute("form",form);
            return "redirect:invoice/add-medicine";
        }

        Medicine theMedicine = medicineDao.findOne(form.getMedicineId());
        Invoice theInvoice = invoiceDao.findOne(form.getInvoiceId());
        theInvoice.addMedicine(theMedicine);
        invoiceDao.save(theInvoice);
       Integer res =  invoiceDao.updateAmount(theMedicine.getPrice(),theInvoice.getId());
        return "redirect:/invoice/add-medicine?invoiceId=" + form.getInvoiceId();
    }
}
