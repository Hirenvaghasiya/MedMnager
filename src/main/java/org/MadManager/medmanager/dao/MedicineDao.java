package org.MadManager.medmanager.dao;

import jdk.nashorn.internal.runtime.options.Option;
import org.MadManager.medmanager.models.Category;
import org.MadManager.medmanager.models.Medicine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Created by Hiren on 7/14/2017.
 */
@Repository
@Transactional
public interface MedicineDao extends CrudRepository<Medicine,Integer> {

    Optional<Medicine> findByName(String name);
}
