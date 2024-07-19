<%-- 
    Document   : user
    Created on : Apr 21, 2024, 7:19:12 PM
    Author     : patho
--%>

<%@page import="registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page import="product.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <%
            // Retrieve last name cookie
            String lastName = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("LAST_NAME")) {
                        lastName = cookie.getValue();
                        break;
                    }
                }%>
                <p>Welcome, <%= lastName != null ? lastName : "" %></p>
            <%}%>
           

        <form action="MainController" method="POST">
            Name<input type="text" name="txtSearchValue"/>
            <input type="submit" name="btAction" value="Search"/>
        </form>
        <%
            List<ProductDTO> accs = (List<ProductDTO>) request.getAttribute(
                    "ACCS");
            if (accs != null && accs.size() > 0) { %>
        <table border = '1'>
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Model Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Description</th>
                    <th>Image</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (int cnt = 0; cnt < accs.size(); cnt++) {
                        ProductDTO i = accs.get(cnt);
                %>
                <tr>
            <form action="MainController" method="POST">
                <td><%=cnt + 1%></td>
                <td><%= i.getProductName()%><input type="hidden" name="txtProductName" 
                                                value="<%= i.getProductName()%>"/></td>
                <td><%= i.getPrice()%><input type="hidden" name="txtPrice" value="<%= i.getPrice()%>"/></td>
                <td><%= i.getQuantity()%><input type="hidden" name="txtQuantity" value="<%= i.getQuantity()%>"/></td>
                <td><%= i.getDescription()%><input type="hidden" name="txtDescription" value="<%= i.getDescription()%>"/></td>
                <td><img src="<%= i.getImageLink() %>" alt="Image"><input type="hidden" name="txtImageLink" value="<%= i.getImageLink()%>"/></td>
                <td><input type="submit" name="btAction" value="Add To Cart" />
                <input type="hidden" name="lastSearchValue" value="<%=request.getParameter(
                               "txtSearchValue")%>"/></td>
            </form>            
        </tr>
        <%}%>
        <%}%>
    </tbody>
</table>
    <% 
        if (request.getAttribute("OUT_OF_STOCK") != null) {
        %>
                <span style="color: red;">Out of stock</span>
        <%
            }
        %>
            <form action="MainController" method="POST">
                <input type="submit" name="btAction" value="Logout" />
            </form>
            <a href="cart.jsp">View Cart</a>
</body>
</html>
