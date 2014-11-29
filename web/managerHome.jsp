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
        <script type="text/javascript" src="js/monthlyRev.js"></script>
        <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
        <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id = "currentUser" class="User.Person" scope="request"/>
        <title>Manager Home</title>
    </head>
    
    <body id = "managerPageBody">        
        <div>
        <h1 id="abc">Hi! ${currentUser.firstName} ${currentUser.lastName}</h1>
            
        </div>
        <div id="container"> 
                <h3>Show: </h3>
                <input type="submit" class="managerButtons" id="customerTableButton" value="Customers" onclick="populateCustomersTable()"/>
                <input type="submit" class="managerButtons" id="employeeTableButton" value="Employees" onclick="populateEmployeesTable()"/>
            
            <div style="float:left; width:100%; min-height: 100px"> 

                <table id="customersTable">
                    <thead>
                    <tr colspan="5"><h3>Customers</h3></tr>
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
            <div style="float:left; width:100%; min-height: 100px">
                <table id="employeesTable">
                    <thead>
                    <tr colspan="5"><h3>Employee</h3></tr>
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
            <div style="width: 100%; min-height: 100px">
            
            Select a month to generate sales report: 
             <select style="display:inline-block" id="salesReport">
                        <option value = "1">JAN</option>
                        <option value = "2">FEB</option>
                        <option value = "3">MAR</option>
                        <option value = "4">APR</option>
                        <option value = "5">MAY</option>
                        <option value = "6">JUN</option>
                        <option value = "7">JUL</option>
                        <option value = "8">AUG</option>
                        <option value = "9">SEP</option>
                        <option value = "10">OCT</option>
                        <option value = "11">NOV</option>
                        <option value = "12">DEC</option>                    
                </select> 
            <input type="text" style="display:inline-block" id="salesReportYear" value ="Year" />
            <button onclick="getRev()">Get Report</button>
                
            </div >
            <div style="width: 100%">
                    <table id="revPlace" style="display:none">
                        <thead>
                            <tr colspan="2"><h3>Sales For Selected Month</h3></tr>
                            <th>Fee for Date</th>
                            <th>Date's Time</th>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            
            Show dates by name or by day:  
        </div> 



    </body>
</html>
