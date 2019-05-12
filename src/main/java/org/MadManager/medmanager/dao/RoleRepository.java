package org.MadManager.medmanager.dao;

import org.MadManager.medmanager.models.Role;
import org.MadManager.medmanager.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by hiren.vaghasiya on 2/16/2018.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findByName(RoleName roleName);
}
