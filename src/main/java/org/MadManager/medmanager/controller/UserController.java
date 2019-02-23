package org.MadManager.medmanager.controller;

import org.MadManager.medmanager.dao.RoleRepository;
import org.MadManager.medmanager.models.Role;
import org.MadManager.medmanager.models.Users;
import org.MadManager.medmanager.service.SecurityService;
import org.MadManager.medmanager.service.UserService;
import org.MadManager.medmanager.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hiren.vaghasiya on 2/17/2018.
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("userForm", new Users());
        model.addAttribute("roles",roleRepository.findAll());
        return "registration";
    }

    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") Users userForm, BindingResult bindingResult, @RequestParam Integer roleId, Model model){
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()){
            model.addAttribute("roles",roleRepository.findAll());
            return "registration";

        }

        Role role = roleRepository.findOne(roleId);
        userForm.setRoles(role);
        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout){
        if(error != null){
            model.addAttribute("error","Username or Password is Invalid");
        }

        if(logout != null){
            model.addAttribute("message","You have logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model,  String error,@RequestParam("username") String username, @RequestParam("password") String passwod){
        securityService.autoLogin(username,passwod);
        return "invoice/add";
    }
    @RequestMapping(value = {"/","/welcome"},method = RequestMethod.GET)
    public String welcome(Model model){
        return "/invoice";
    }
}
