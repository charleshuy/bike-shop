<%-- 
    Document   : cart
    Created on : Apr 21, 2024, 8:54:04 PM
    Author     : patho
--%>

<%@page import="cart.CartDTO"%>
<%@page import="java.util.List"%>
<%@page import="product.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
    </head>
    <body>
        <p> <%= lastName != null ? lastName : "" %>'s Cart</p>
        <%
            HttpSession ses = request.getSession();
            CartDTO cart = (CartDTO) ses.getAttribute("CART");
            if(cart != null){
                List<ProductDTO> product = cart.getProducts();
                if (product != null && product.size() > 0) { %>
        <table border = '1'>
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Model Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Description</th>
                    <th>Image<th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (int cnt = 0; cnt < product.size(); cnt++) {
                        ProductDTO i = product.get(cnt);
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
                <td><input type="submit" name="btAction" value="Remove" /></td>
            </form>            
        </tr>
        <%}%>
        <%}%>
        <%}%>
            
    </tbody>
</table>
        <form action="MainController" method="POST">
            <input type="submit" name="btAction" value="Check Out" />
        </form><br>
        <%
            String moneyText = (String) request.getAttribute("MONEY");
            if(moneyText == null){
                moneyText = "0";
            }
        %>
        <h4>Total: <%= moneyText %>$</h4><br>
        
        <a href="user.jsp">Return</a>
    </body>
</html>
