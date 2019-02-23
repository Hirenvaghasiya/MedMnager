package org.MadManager.medmanager.dao;

import org.MadManager.medmanager.models.Users;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by hiren.vaghasiya on 2/16/2018.
 */

public interface UserRepository extends CrudRepository<Users, Integer> {
    Users findByUsername(String username);

}
