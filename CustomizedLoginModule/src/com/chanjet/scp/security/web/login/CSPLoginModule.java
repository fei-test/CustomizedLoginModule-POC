package com.chanjet.scp.security.web.login;

import com.chanjet.scp.security.web.authorization.CSPRolePrincipal;
import com.chanjet.scp.security.web.authorization.CSPUserPrincipal;
import org.apache.geronimo.security.realm.providers.RequestCallback;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by feishan on 10/16/14.
 */
public class CSPLoginModule implements LoginModule {

    private Subject subject;
    private CallbackHandler handler;
    private String user;
    private CSPUserPrincipal userPrincipal;
    private CSPRolePrincipal rolePrincipal;

    private String request_username;
    private String request_password;
    private String request_cb_username;
    private String request_cb_password;

    private boolean loginSuccess = false;

    private Long uid;

    private HttpServletRequest getHttpRequestInfo() {
        /** The JACC PolicyContext key for the current Subject */
        HttpServletRequest request = null;
        try {
            request = (HttpServletRequest) PolicyContext.getContext("javax.servlet.http.HttpServletRequest");
        } catch (PolicyContextException e) {
            //logger.error("Exception in getHttpRequestInfo(): " + e);
            e.printStackTrace();
        }
        return request;
    }


    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler,
                           Map<String, ?> sharedState, Map<String, ?> options) {

        System.out.println(" Init...");

        HttpServletRequest httpServletRequest = getHttpRequestInfo();
        request_username = httpServletRequest.getParameter("username");
        request_password = httpServletRequest.getParameter("password");

        System.out.println(" init: " + request_username + "  " + request_password);


        this.subject = subject;
        this.handler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {

        loginSuccess = false;

        System.out.println("Login...");

        NameCallback nc = new NameCallback("Enter User Token: ");
        PasswordCallback pwc = new PasswordCallback("Enter Password: ", false);

        RequestCallback rcb = new RequestCallback();

        try {

            System.out.println("Start process callback handlers...");

            handler.handle(new Callback[]{nc, pwc});

        } catch (IOException | UnsupportedCallbackException e) {

            clearPrivateState();

            e.printStackTrace();
            throw (LoginException) new LoginException().initCause(e);
        }

        System.out.println("handler is invoked");

        //rcb callback handler only works on different auth mode
        /**HttpServletRequest request = rcb.getRequest();
        request_cb_username = request.getParameter("username");
        request_cb_password = request.getParameter("password"); **/

        user = nc.getName();
        String password = new String(pwc.getPassword());

        //TODO: need call mock api service with username and password


        //need call restful api to get real username
        //http://cia.chanapp.chanjet.com/internal_api/authorizeByJsonp?callback=callback&client_id=newapp&state=xxsss

        //if http rest call return fail result --> throw new FailedLoginException();

        uid = 1001L;

        System.out.println(" user name is : " + user + " | password is : " + password);
        System.out.println(" from request user name is : " + request_username + " | password is : " + request_password);
        System.out.println(" from request callback user name is : " + request_cb_username + " | password is : " + request_cb_password);

        loginSuccess = true;

        //if valid
        return true;
    }

    @Override
    public boolean commit() throws LoginException {

        System.out.println(" commit..." + user);
        System.out.println(" subject principals name: " + subject.getPrincipals().size());

        userPrincipal = new CSPUserPrincipal(uid, user);
        rolePrincipal = new CSPRolePrincipal("CSPAppUsers");

        subject.getPrincipals().add(userPrincipal);
        subject.getPrincipals().add(rolePrincipal);

        System.out.println(" subject principals name: " + subject.getPrincipals().size());

        System.out.println(" login : " + (loginSuccess ? " success " : " fail "));

        clearPrivateState();

        return loginSuccess;
    }

    @Override
    public boolean abort() throws LoginException {

        System.out.println(" abort..... ");

        if (loginSuccess) {

            clearPrivateState();

            userPrincipal = null;
            rolePrincipal = null;
        }


        System.out.println(" login : " + (loginSuccess ? " success " : " fail "));

        return loginSuccess;
    }

    @Override
    public boolean logout() throws LoginException {

        System.out.println(" logout...... ");

        clearPrivateState();

        loginSuccess = false;

        if (!subject.isReadOnly()) {

            subject.getPrincipals().remove(userPrincipal);
            subject.getPrincipals().remove(rolePrincipal);
        }

        userPrincipal = null;
        rolePrincipal = null;

        return true;
    }

    private void clearPrivateState() {
        request_cb_password = null;
        request_cb_username = null;
        user = null;
        request_username = null;
        request_password = null;
    }

}
