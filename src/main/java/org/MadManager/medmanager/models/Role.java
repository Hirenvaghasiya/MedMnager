package org.MadManager.medmanager.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hiren.vaghasiya on 2/16/2018.
 */
@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany
    @JoinColumn(name = "role_id")
    private Set<Users> users;

   public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Users> getUsers() {
        return users;
    }

    }
