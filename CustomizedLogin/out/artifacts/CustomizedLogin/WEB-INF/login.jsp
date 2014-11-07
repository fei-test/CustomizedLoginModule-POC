<%--
Created by IntelliJ IDEA.
User: feishan
Date: 10/16/14
Time: 3:26 PM
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<!--<form name="loginForm" action="/CustomizedLogin/servlet">
    <input type="text" name="username">
    <input type="password" name="password">
    <input type="submit" name="login">
</form>-->

<form method=post action="j_security_check" >

    <input type="hidden" name="username" value="test1">
    <input type="hidden" name="password" value="1111">

    <p>
        <span>Username:</span>
        <br />
        <input type="text"  name= "j_username" >
    </p>
    <p>
        <span>Password:</span>
        <br />
        <input type="password"  name= "j_password" >
    </p>
    <p>
        <input type="submit" value="Login">
    </p>
</form>

</body>
</html>