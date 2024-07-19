<%-- 
    Document   : search
    Created on : Apr 19, 2024, 2:37:40 PM
    Author     : patho
--%>

<%@page import="product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="registration.RegistrationDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <script>
            function isNumberKey(evt) {
            var charCode = (evt.which) ? evt.which : event.keyCode;
            if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                return false;
            }
            var inputValue = evt.target.value + String.fromCharCode(charCode);
            return !(parseFloat(inputValue) <= 0);
        }
        </script>


        <a href="account-management.jsp">Account Management</a>

            <h3>Product management</h3>
                
        <form action="MainController" method="POST">
            Name Model<input type="text" name="txtSearchValue"/>
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
                    <th>Delete</th>
                    <th>Update</th>
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
                <td><input type="text" name="txtPrice" id="txtPrice" value="<%= i.getPrice()%>" onkeypress="return isNumberKey(event)" onpaste="return false" min="0"/></td>
                <td><input type="number" name="txtQuantity" value="<%= i.getQuantity()%>" onkeypress="return isNumberKey(event)" onpaste="return false" min="0"/></td>
                <td><input type="text" name="txtDescription" value="<%= i.getDescription()%>"/></td>
                <td><img src="<%= i.getImageLink() %>" alt="Image"><input type="hidden" name="txtImageLink" 
                                                value="<%= i.getImageLink()%>"/></td>
                <td><a href="<%= "MainController?btAction=Delete&pk="
                        + i.getProductName()
                        + "&lastSearchValue=" + request.getParameter(
                                "txtSearchValue")%>">Delete</a></td>
                       <input type="hidden" name="lastSearchValue" value="<%=request.getParameter(
                               "txtSearchValue")%>"/>
                <td><input type="submit" name="btAction" value="Update" /></td>
            </form>            
        </tr>
        <%}%>
        <%}%>
    </tbody>
</table>
     <a href="insert.jsp">Insert</a>
    <form action="MainController" method="POST">
                <td><input type="submit" name="btAction" value="Logout" /></td>
            </form>
            
        
</body>
</html>
