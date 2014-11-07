package com.chanjet.scp.security.web.authorization;

import org.apache.geronimo.security.realm.providers.GeronimoGroupPrincipal;

import java.io.Serializable;
import java.security.Principal;

/**
 * Created by feishan on 10/21/14.
 */
public class RolePrincipal extends GeronimoGroupPrincipal implements Principal, Serializable {


    public RolePrincipal(String name) {

        super(name);

    }

}
