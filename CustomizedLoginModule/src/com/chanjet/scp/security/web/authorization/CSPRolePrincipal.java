package com.chanjet.scp.security.web.authorization;

import org.apache.geronimo.security.realm.providers.GeronimoGroupPrincipal;

import java.io.Serializable;
import java.security.Principal;

/**
 * Created by feishan on 11/5/14.
 */
public class CSPRolePrincipal extends GeronimoGroupPrincipal implements Principal, Serializable {


    public CSPRolePrincipal(String name) {

        super(name);

    }

}
