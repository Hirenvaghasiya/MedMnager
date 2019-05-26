package org.MadManager.medmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.MadManager.medmanager.dao.CategoryDao;
import org.MadManager.medmanager.dao.MedicineDao;
import org.MadManager.medmanager.models.Category;
import org.MadManager.medmanager.models.Medicine;
import org.MadManager.medmanager.payload.APIResponse;
import org.MadManager.medmanager.payload.AddMedicineRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

/**
 * Created by Hiren on 7/9/2017.
 */
@RestController
@RequestMapping("medicine")
public class MedicineController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicineController.class);

    @Autowired
    private MedicineDao medicineDao;

    @Autowired
    private CategoryDao categoryDao;

    ObjectMapper mapper = new ObjectMapper();

//    @RequestMapping(value = "")
//    public  String index(Model model){
//
//        model.addAttribute("title","Add New Medicine");
//        model.addAttribute("medicines", medicineDao.findAll());
//        return "medicine/index";
//    }
//
//    @RequestMapping(value = "add", method = RequestMethod.GET)
//    public String displayAdd(Model model){
//
//        model.addAttribute("title","Add Medicine");
//        model.addAttribute(new Medicine());
//        model.addAttribute("categories", categoryDao.findAll());
//
//        return "medicine/add";
//    }
//
//    @RequestMapping(value = "add", method = RequestMethod.POST)
//    public String processAdd(@ModelAttribute  @Valid Medicine newMedicine, Errors errors, Model model, @RequestParam Integer categoryId){
//        if(errors.hasErrors()){
//            model.addAttribute("title","Add Medicine");
//            model.addAttribute("categories",categoryDao.findAll());
//            return "medicine/add";
//        }
//
//        Category cat = categoryDao.findOne(categoryId);
//        newMedicine.setCategory(cat);
//        medicineDao.save(newMedicine);
//        return  "redirect:";
//    }
//
//    @RequestMapping(value = "remove", method = RequestMethod.GET)
//    public String displayRemove(Model model){
//        model.addAttribute("title","Remove Medicine");
//        model.addAttribute("medicines",medicineDao.findAll());
//        return "medicine/remove";
//    }
//
//    @RequestMapping(value = "remove", method = RequestMethod.POST)
//    public String processRemove(@RequestParam Integer[] ids){
//        for(Integer medId : ids){
//            medicineDao.delete(medId);
//
//            }
//        return "redirect:";
//    }
//    @RequestMapping(value = "category",method = RequestMethod.GET)
//    public String category(Model model, @RequestParam Integer id){
//
//        Category cat = categoryDao.findOne(id);
//        List<Medicine> medicines = cat.getMedicines();
//        model.addAttribute("medicines",medicines);
//        model.addAttribute("title","Medicine in Category "+cat.getName());
//        return "medicine/index";
//    }

    @GetMapping(value = "/{id}")
    public Medicine getMedicineById(@PathVariable Integer id){
        LOGGER.info("GET Request receive for nedicineId: {}",id);

        return medicineDao.findOne(id);
    }

    @GetMapping
    public Iterable<Medicine> getMedicines(){
        return medicineDao.findAll();
    }

    @PostMapping
    public ResponseEntity<?>  addMedicine(@Valid @RequestBody AddMedicineRequest addMedicineRequest) {
        LOGGER.info("POST resquest receive for medicien with name: {}, price: {}, category: {}",addMedicineRequest.getName(), addMedicineRequest.getPrice(), addMedicineRequest.getCategory().getName());

        Category medicineCategory = categoryDao.findByName(addMedicineRequest.getCategory().getName());

        if(null == medicineCategory){
            LOGGER.info("Medicine category {} not found",addMedicineRequest.getCategory().getName());
        }

        Medicine newMedicine = new Medicine(addMedicineRequest.getName(),addMedicineRequest.getPrice(),medicineCategory);
        Medicine result = medicineDao.save(newMedicine);

        LOGGER.info("New medicine added with id {}",newMedicine.getId());

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/medicine/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).body(new APIResponse(true,"Medicine Added successfully"));
    }

    @PutMapping("{id}")
    public @ResponseBody String updateMedicine(@PathVariable Integer id, @RequestBody Medicine medicineToUpdate) throws IOException {
        Medicine old = medicineDao.findOne(id);

        if(null!=medicineToUpdate.getName())
            old.setName(medicineToUpdate.getName());

        if(null!=medicineToUpdate.getPrice())
            old.setPrice(medicineToUpdate.getPrice());

        if(null!=medicineToUpdate.getCategory())
            old.setCategory(medicineToUpdate.getCategory());

        medicineDao.save(old);

        return mapper.writeValueAsString(old);
    }
    @DeleteMapping(value = "{id}")
    public String deleteMedicine(@PathVariable Integer id){
        LOGGER.info("Delete request receive for medicineId: {}",id);
        medicineDao.delete(id);
        return "Medicine deleted "+id;
    }
}
