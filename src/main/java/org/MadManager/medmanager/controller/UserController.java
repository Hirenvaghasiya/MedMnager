package org.MadManager.medmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.MadManager.medmanager.dao.RoleRepository;
import org.MadManager.medmanager.models.Role;
import org.MadManager.medmanager.models.User;
import org.MadManager.medmanager.service.SecurityService;
import org.MadManager.medmanager.service.UserService;
import org.MadManager.medmanager.validator.UserValidator;
import org.owasp.esapi.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hiren.vaghasiya on 2/17/2018.
 */

@RestController
@RequestMapping("/user")
public class UserController {

//    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private SecurityService securityService;
//
//    @Autowired
//    private UserValidator userValidator;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    ObjectMapper mapper = new ObjectMapper();
//
////    @RequestMapping(value = "/registration", method = RequestMethod.GET)
////    public String registration(Model model){
////        model.addAttribute("userForm", new Users());
////        model.addAttribute("roles",roleRepository.findAll());
////        return "registration";
////    }
////
////    @RequestMapping(value = "/registration",method = RequestMethod.POST)
////    public String registration(@ModelAttribute("userForm") Users userForm, BindingResult bindingResult, @RequestParam Integer roleId, Model model){
////        userValidator.validate(userForm, bindingResult);
////
////        if (bindingResult.hasErrors()){
////            model.addAttribute("roles",roleRepository.findAll());
////            return "registration";
////
////        }
////
////        Role role = roleRepository.findOne(roleId);
////        userForm.setRoles(role);
////        userService.save(userForm);
////
////        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());
////
////        return "redirect:/welcome";
////    }
////
////    @RequestMapping(value = "/login", method = RequestMethod.GET)
////    public String login(Model model, String error, String logout){
////        if(error != null){
////            model.addAttribute("error","Username or Password is Invalid");
////        }
////
////        if(logout != null){
////            model.addAttribute("message","You have logged out successfully.");
////        }
////
////        return "login";
////    }
////
////    @RequestMapping(value = "/login", method = RequestMethod.POST)
////    public String login(Model model,  String error,@RequestParam("username") String username, @RequestParam("password") String passwod){
////        securityService.autoLogin(username,passwod);
////        return "invoice/add";
////    }
////    @RequestMapping(value = {"/","/welcome"},method = RequestMethod.GET)
////    public String welcome(Model model){
////        return "welcome";
////    }
//
//    @PostMapping("/login")
//    public @ResponseBody boolean validateUser(@RequestParam String username, @RequestParam String password){
//        LOGGER.info("Login request received for username: {}",username);
//        boolean loginStatus = securityService.autoLogin(username,password);
//        LOGGER.info("Login request processed for username: {} ",username,loginStatus);
//        return loginStatus;
//    }

//    @PostMapping("/register")
//    public @ResponseBody String addUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) throws JsonProcessingException {
//        LOGGER.info("New registration request for username: {}",username);
//        if(null != userService.findByUsername(username)){
//            LOGGER.info("Duplicat useranme: {}",username);
//            return "Username "+ username +" already exists" ;
//        }
//        User newUser = new User();
//        Role userRole = roleRepository.findByName(role);
//        if(null == userRole){
//            LOGGER.info("Invalid role type {}",role);
//            return "Role "+ role +" not found";
//        }
//
//        newUser.setRoles(userRole);
//        newUser.setUsername(username);
//        newUser.setPassword(password);
//        userService.save(newUser);
//        LOGGER.info("User added with id {}: username{}",newUser.getId(),newUser.getUsername());
//        return "User Created";
//    }
}
