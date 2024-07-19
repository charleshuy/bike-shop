<%-- 
    Document   : insert
    Created on : Apr 19, 2024, 5:11:34 PM
    Author     : patho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert</title>
    </head>
    <body>
        <h1>Insert Page</h1>
        
        <form action="MainController" method="POST">
            Model Name: <input type="text" name ="txtProductName"/><br>
            Price: <input type="text" id="txtPrice" name="txtPrice" oninput="this.value = this.value.replace(/[^0-9]/g, '');"><br>
            Quantity: <input type="text" id="txtQuantity" name="txtQuantity" oninput="this.value = this.value.replace(/[^0-9]/g, '');"><br>
            Description: <input type="text" name ="txtDescription"/><br>
            Image Link: <input type="text" name ="txtImageLink"/><br>
            <input type="submit" name="btAction" value="Add"/>
            <input type="reset" value="Reset"/><br>
            <a href="search.jsp">Cancel</a>
        </form>
    </body>
</html>
