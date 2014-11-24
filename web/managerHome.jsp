<%-- 
    Document   : managerHome
    Created on : Nov 23, 2014, 2:26:05 AM
    Author     : Acer
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="js/employeeTableButton.js"></script>
        <script type="text/javascript" src="js/customerTableButton.js"></script>
        <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
        <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id = "currentUser" class="User.Person" scope="request"/>
        <title>Manager Home</title>
    </head>
    
    <body>
        
        <div>
        <h1 id="abc">Hi! ${currentUser.firstName} ${currentUser.lastName}</h1>
            <input type="submit" id="customerTableButton" value="Show All Customers" onclick="populateCustomersTable()"/>
            <input type="submit" id="employeeTableButton" value="Show All Employees" onclick="populateEmployeesTable()"/>
        </div>
            <div> 
                
                 <div style="float:left; width:50%"> 

                <table id="customersTable" style="display:none">
                    <thead>
                    <th>Social Security Number</th>
                    <th>PPP</th>
                    <th>Your Rating</th>
                    <th>Your Last Active Date</th>
                    <th>Delete</th>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div style="float:left; width:50%">
                <table id="employeesTable" style="display:none">
                    <thead>
                    <th>Social Security Number</th>
                    <th>Role</th>
                    <th>Start Date</th>
                    <th>Rate</th>
                    <th>Delete</th>
                    
                    </thead>
                    <tbody>

                    </tbody>

                </table>
            </div>
                 </div> 
    </body>
</html>
