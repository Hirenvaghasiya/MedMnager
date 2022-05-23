package org.MadManager.medmanager.service;

import java.util.Optional;

import org.MadManager.medmanager.dao.UserRepository;
import org.MadManager.medmanager.models.User;
import org.MadManager.medmanager.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by hiren.vaghasiya on 2/17/2018.
 */
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
            User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                            .orElseThrow(()->
                                    new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail)
                            );
            return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id){
        User user = userRepository.findById(id)
                        .orElseThrow(()->
                                    new UsernameNotFoundException("User not found with userId: "+ id));
        return UserPrincipal.create(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return  userRepository.findByUsernameOrEmail(username,"");
    }

}
