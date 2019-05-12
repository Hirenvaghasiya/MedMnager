package org.MadManager.medmanager.dao;

import org.MadManager.medmanager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by hiren.vaghasiya on 2/16/2018.
 */

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findById(Long Id);

}
