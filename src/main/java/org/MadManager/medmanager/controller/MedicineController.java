package org.MadManager.medmanager.controller;

import org.MadManager.medmanager.models.Medicine;
import org.MadManager.medmanager.models.MedicineType;
import org.MadManager.medmanager.models.dao.MedicineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by Hiren on 7/9/2017.
 */
@Controller
@RequestMapping("medicine")
public class MedicineController {

    @Autowired
    private MedicineDao medicineDao;

    @RequestMapping(value = "")
    public  String index(Model model){

        model.addAttribute("title","Add New Medicine");
        model.addAttribute("medicines", medicineDao.findAll());
        return "medicine/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAdd(Model model){

        model.addAttribute("title","Add Medicine");
        model.addAttribute(new Medicine());
        model.addAttribute("medicineTypes", MedicineType.values());

        return "medicine/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAdd(@ModelAttribute  @Valid Medicine newMedicine, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title","Add Medicine");
            return "medicine/add";
        }

        medicineDao.save(newMedicine);
        return  "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemove(Model model){
        model.addAttribute("title","Remove Medicine");
        model.addAttribute("medicines",medicineDao.findAll());
        return "medicine/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemove(@RequestParam Integer[] ids){
        for(Integer medId : ids){
            medicineDao.delete(medId);

            }
        return "redirect:";
    }
}
