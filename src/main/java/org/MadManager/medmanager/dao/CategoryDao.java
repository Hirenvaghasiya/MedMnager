package org.MadManager.medmanager.dao;

import org.MadManager.medmanager.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Hiren on 7/15/2017.
 */
@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {
    Category findByName(String name);
}
