<%-- 
    Document   : signup
    Created on : Apr 19, 2024, 4:54:31 PM
    Author     : patho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <h1>Create Account Page</h1>
        
        <form action="MainController" method="POST">
            User Name: <input type="text" name ="txtUserName"/>
            ${requestScope.ERROR.userNameLenError}<br>
            Password: <input type="password" name ="txtPassword"/>
            ${requestScope.ERROR.passwordLenError}<br>
            Confirm: <input type="password" name ="txtConfirm"/>
            ${requestScope.ERROR.confirmNotMatch}<br>
            Last Name: <input type="text" name ="txtLastName"/>
            ${requestScope.ERROR.lastNameLenError}<br>
            <input type="submit" name="btAction" value="Create"/>
            <input type="reset" value="Reset"/><br>
            <a href="login.jsp">Cancel</a>
        </form>
    </body>
</html>
