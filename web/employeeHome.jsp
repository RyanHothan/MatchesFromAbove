<%-- 
    Document   : employeeHome
    Created on : Nov 19, 2014, 12:34:14 PM
    Author     : Javier
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/customerTableButton.js"></script>
        <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
        <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id = "currentUser" class="User.Person" scope="request"/>
        <title>Employee Home</title>
    </head>
    <body>
        <h1 id="abc">Hello World!</h1>
        <input type="submit" id="customerTableButton" value="Show All Customers" onclick="populateCustomersTable()"/>
       
    <table id="customersTable" style="display:none">
         <thead>
            <th>Social Security Number</th>
            <th>PPP</th>
            <th>Your Rating</th>
            <th>Your Last Active Date</th>
            <thead>
        <tbody>

        </tbody>
    </table>
</body>
</html>
