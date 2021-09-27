package org.MadManager.medmanager.dao;

import org.MadManager.medmanager.models.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Created by Hiren on 7/14/2017.
 */
@Repository
@Transactional
public interface ItemDao extends CrudRepository<Item,Integer> {

    Optional<Item> findByName(String name);
}
