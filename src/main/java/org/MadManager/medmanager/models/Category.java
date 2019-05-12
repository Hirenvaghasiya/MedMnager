package org.MadManager.medmanager.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hiren on 7/15/2017.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue
    private  Integer id;
    @NotNull
    private  String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
