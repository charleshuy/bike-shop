<%-- 
    Document   : login
    Created on : Apr 19, 2024, 2:24:57 PM
    Author     : patho
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
            // Retrieve last name cookie
            String userName = "";
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("USER_NAME")) {
                        userName = cookie.getValue();
                        break;
                    }
                }
}%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form action="MainController" method="POST">
            Username <input type="text" name="txtUsername" value="<%= userName %>"/><br/>
            Password <input type="password" name="txtPassword"/><br/>            
            <input type="submit" name="btAction" value="Login"/>
            <input type="reset" name="btAction" value="Reset"/>
        </form>
        <a href="signup.jsp">Sign Up</a>
        <% 
        if (request.getAttribute("INVALID_LOGIN") != null) {
        %>
                <span style="color: red;">Login failed. Please check your username and password.</span>
        <%
            }
        %>
    </body>
</html>
