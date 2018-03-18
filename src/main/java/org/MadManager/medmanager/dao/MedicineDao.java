package org.MadManager.medmanager.dao;

import org.MadManager.medmanager.models.Medicine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Hiren on 7/14/2017.
 */
@Repository
@Transactional
public interface MedicineDao extends CrudRepository<Medicine,Integer> {
}
