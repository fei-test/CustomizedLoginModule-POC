package com.chanjet.scp.security.web.authorization;

import org.apache.geronimo.security.realm.providers.GeronimoUserPrincipal;

import java.io.Serializable;
import java.security.Principal;

/**
 * Created by feishan on 11/5/14.
 */
public class CSPUserPrincipal extends GeronimoUserPrincipal implements Principal, Serializable {


    private Long realUID;

    public CSPUserPrincipal(String name) {
        super(name);
    }

    public CSPUserPrincipal(Long realUID, String name) {
        super(name);
        this.realUID = realUID;
    }

    public Long getAppUID() {
        return this.realUID;
    }




}
