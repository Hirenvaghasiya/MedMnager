package org.MadManager.medmanager.service;

import org.MadManager.medmanager.models.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by hiren.vaghasiya on 2/17/2018.
 */
public interface UserService {

    void save(User user);

    Optional<User> findByUsername(String username);

}
