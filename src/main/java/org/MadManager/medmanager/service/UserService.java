package org.MadManager.medmanager.service;

import org.MadManager.medmanager.models.Users;

/**
 * Created by hiren.vaghasiya on 2/17/2018.
 */
public interface UserService {

    void save(Users user);

    Users findByUsername(String username);
}
