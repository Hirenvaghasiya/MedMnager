package org.MadManager.medmanager.models.dao;

import org.MadManager.medmanager.models.Invoice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hiren.vaghasiya on 7/18/2017.
 */
@Repository
@Transactional
public interface InvoiceDao extends CrudRepository<Invoice, Integer > {
    @Modifying
    @Transactional(readOnly = false)
    @Query("update Invoice I set I.amount=( select amount + :price from Invoice I2 where I2.id= :invoiceId ) where I.id= :invoiceId")
    Integer updateAmount(@Param("price") double price, @Param("invoiceId") int invoiceId );
}
