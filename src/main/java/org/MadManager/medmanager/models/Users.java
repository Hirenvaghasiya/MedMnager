package org.MadManager.medmanager.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hiren.vaghasiya on 2/16/2018.
 */
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
    private String confirmPassword;

    @ManyToOne
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRoles(Role role) {
        this.role = role;
    }
}
