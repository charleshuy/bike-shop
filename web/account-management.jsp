<%-- 
    Document   : account-management
    Created on : Apr 22, 2024, 1:05:52 AM
    Author     : patho
--%>

<%@page import="registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Management</title>
    </head>
    <body>
       <a href="search.jsp">Product Management</a>
            
            <h3>Account management</h3>
        <form action="MainController" method="POST">
            Name <input type="text" name="txtSearchValueName"/>
            <input type="submit" name="btAction" value="Search Acc"/>
        </form>
        <%
            List<RegistrationDTO> accounts = (List<RegistrationDTO>) request.getAttribute(
                    "ACCOUNTS");
            if (accounts != null && accounts.size() > 0) { %>
        <table border = '1'>
            <thead>
                <tr>
                    <th>No.</th>
                    <th>User Name</th>
                    <th>Password</th>
                    <th>Full Name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (int cnt = 0; cnt < accounts.size(); cnt++) {
                        RegistrationDTO a = accounts.get(cnt);
                %>
                <tr>
            <form action="MainController" method="POST">
                <td><%=cnt + 1%></td>
                <td><%= a.getUsername()%><input type="hidden" name="txtUserName" 
                                                value="<%= a.getUsername()%>"/></td>
                <td><input type="text" name="txtPassword" value="<%= a.getPassword()%>"/></td>
                <td><input type="text" name="txtLastName" value="<%= a.getLastName()%>"/></td>
                <td><input type="checkbox" name="ckIsAdmin" <%=a.isIsAdmin() ? "checked" : null%>/></td>
                <td><a href="<%= "MainController?btAction=DeleteAcc&pkn="
                        + a.getUsername()
                        + "&lastSearchValueName=" + request.getParameter(
                                "txtSearchValueName")%>">Delete</a></td>
                       <input type="hidden" name="lastSearchValueName" value="<%=request.getParameter(
                               "txtSearchValueName")%>"/>
                <td><input type="submit" name="btAction" value="Update Account" /></td>
            </form>            
        </tr>
        <%}%>
        <%}%>
    </tbody>
</table>
            <form action="MainController" method="POST">
                <td><input type="submit" name="btAction" value="Logout" /></td>
            </form>
    </body>
</html>
