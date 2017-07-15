package org.MadManager.medmanager.controller;

import org.MadManager.medmanager.models.Category;
import org.MadManager.medmanager.models.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Hiren on 7/15/2017.
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("title","Category");
        model.addAttribute("categories",categoryDao.findAll());

        return "category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addDisplay(Model model){
        model.addAttribute("title","AddCategory");
        model.addAttribute(new Category());

        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAdd(@ModelAttribute @Valid Category newCategory, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title","AddCategory");
            return "category/add";
        }
        categoryDao.save(newCategory);
        return "redirect:";
    }
}
