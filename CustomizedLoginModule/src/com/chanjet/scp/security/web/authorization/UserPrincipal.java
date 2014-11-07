package com.chanjet.scp.security.web.authorization;

import org.apache.geronimo.security.realm.providers.GeronimoUserPrincipal;

import java.io.Serializable;
import java.security.Principal;

/**
 * Created by feishan on 10/16/14.
 */
public class UserPrincipal extends GeronimoUserPrincipal implements Principal, Serializable {


    public UserPrincipal(String name) {
        super(name);
    }



    public String getPermission() {return "Calculated Permission..."; }


}
