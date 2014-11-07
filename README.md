CustomizedLoginModule-POC
=========================

Created an Customized Login Module with Customized Principal

1. add an user with user = user1 and password = 1111 on geronimo
2. make and deploy the customizedLoginModule to server repository
3. make the deploy the war(/CustomizedLogin)
4. visit "http://..../CustomizedLogin/testServlet"
5. the login form page will show up
6. login with user1/1111
7. then protected /web-inf/protected.jsp with showup with uid = 1001
8. the uid is injected by the customizedLoginModule
9. and the principle is customized CSPUserPrinciple with a get access method of uid
   it could never be set by outside but from the CustomizedLoginModule

10. things needs to do:
    1. will switch to GENERIC http auth method , which use the http header var to auth instead the form
    2. will add real rest call to current authnicate server in the CustomizedLoginModule
    

