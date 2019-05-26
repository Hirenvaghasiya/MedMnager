package org.MadManager.medmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.MadManager.medmanager.dao.RoleRepository;
import org.MadManager.medmanager.dao.UserRepository;
import org.MadManager.medmanager.models.Role;
import org.MadManager.medmanager.models.User;
import org.MadManager.medmanager.service.SecurityService;
import org.MadManager.medmanager.service.UserService;
import org.MadManager.medmanager.validator.UserValidator;
import org.owasp.esapi.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{id}")
   public User getUserById(@PathVariable Long id){
        LOGGER.info("Get Request recieved for userId: {}",id);
        return userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found with id :"+id));
   }

   @GetMapping
   public Iterable<?> getAllUser(){
        LOGGER.info("Get request recieved for all users");
        return userRepository.findAll();
   }
}
