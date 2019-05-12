package org.MadManager.medmanager.service;

/**
 * Created by hiren.vaghasiya on 2/17/2018.
 */
public interface SecurityService {

    String findLoggedInUsername();

    boolean autoLogin(String username, String password);
}
