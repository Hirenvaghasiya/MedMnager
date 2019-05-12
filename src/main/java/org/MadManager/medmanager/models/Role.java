package org.MadManager.medmanager.models;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.lang.annotation.Native;
import java.util.Set;

/**
 * Created by hiren.vaghasiya on 2/16/2018.
 */
@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    public Role(){}

    public Role(RoleName name){
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    }
