package org.MadManager.medmanager.controller;

import org.MadManager.medmanager.dao.UserRepository;
import org.MadManager.medmanager.models.User;
import org.MadManager.medmanager.payload.UserSummary;
import org.MadManager.medmanager.security.CurrentUser;
import org.MadManager.medmanager.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hiren.vaghasiya on 2/17/2018.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser){
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }
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
