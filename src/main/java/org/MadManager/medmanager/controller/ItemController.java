package org.MadManager.medmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.MadManager.medmanager.dao.CategoryDao;
import org.MadManager.medmanager.dao.ItemDao;
import org.MadManager.medmanager.models.Category;
import org.MadManager.medmanager.models.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Hiren on 7/9/2017.
 */
@RestController
@RequestMapping("/api/item")
@Tag(name = "Item")
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemDao itemDao;

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
    public Item getItemById(@PathVariable Integer id){
        LOGGER.info("GET Request receive for nedicineId: {}",id);

        return itemDao.findById(id).orElse(new Item());
    }

    @GetMapping
    public Iterable<Item> getItems(){
        return itemDao.findAll();
    }

    @PostMapping
    public Item  addItem(@Valid @RequestBody Item addItemRequest) throws URISyntaxException {
        LOGGER.info("POST request receive for item with name: {}, price: {}, category: {}", addItemRequest.getName(), addItemRequest.getPrice(), addItemRequest.getCategory().getId());

        Category itemCategory = categoryDao.findById(addItemRequest.getCategory().getId()).orElse(null);
        if(null == itemCategory){

            LOGGER.info("Medicine category {} not found", addItemRequest.getCategory().getId());
        }

        Item newItem = new Item(addItemRequest.getName(), addItemRequest.getPrice(),itemCategory);
        Item result = itemDao.save(newItem);

        LOGGER.info("New item added with id {}", newItem.getId());

        return newItem;
    }

    @PutMapping("{id}")
    public Item updateItem(@PathVariable Integer id, @RequestBody Item itemToUpdate) throws IOException, URISyntaxException {
        Item old = itemDao.findById(id).orElse(new Item());
        Category newCat = categoryDao.findById(itemToUpdate.getCategory().getId()).orElse(new Category());

        if(null!= itemToUpdate.getName())
            old.setName(itemToUpdate.getName());

        if(null!= itemToUpdate.getPrice())
            old.setPrice(itemToUpdate.getPrice());

        if(null!= newCat)
            old.setCategory(itemToUpdate.getCategory());
        else
        {
            LOGGER.info("Item category {} not found", itemToUpdate.getCategory().getId());
            throw new RuntimeException("Item Category does not exists");
        }

      return   itemDao.save(old);



    }
    @DeleteMapping(value = "{id}")
    public Item deleteItem(@PathVariable Integer id){
        LOGGER.info("Delete request receive for itemId: {}",id);
        Item itemToDelete = getItemById(id);
        itemDao.deleteById(id);
        LOGGER.info("Item Deleted {}", itemToDelete);
        return itemToDelete;
    }
}
