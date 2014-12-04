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
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
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
            <input type="submit" class="managerButtons" id="customerTableButton" value="Show Customers" onclick="populateCustomersTable()"/>
            <input type="submit" class="managerButtons" id="employeeTableButton" value="Show Employees" onclick="populateEmployeesTable()"/>

            <div style="float:left; width:100%; min-height: 100px"> 

                <table id="customersTable" style="display:none">
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
                <table id="employeesTable" style="display:none">
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
            <div style="padding: 50px">
                INFO TO ADD EMPLOYEE:<br>
                <input type="text" style="display:inline-block" id="newEmpSSN" value ="SSN" />
                <input type="text" style="display:inline-block" id="newEmpPassword" value ="Password" />
                <input type="text" style="display:inline-block" id="newEmpFirstName" value ="FirstName" />
                <input type="text" style="display:inline-block" id="newEmpLastName" value ="LastName" />
                <input type="text" style="display:inline-block" id="newEmpStreet" value ="Street" />
                <input type="text" style="display:inline-block" id="newEmpCity" value ="City" />
                <input type="text" style="display:inline-block" id="newEmpState" value ="State" />
                <input type="text" style="display:inline-block" id="newEmpZipCode" value ="ZipCode" />
                <input type="text" style="display:inline-block" id="newEmpEmail" value ="Email" />
                <input type="text" style="display:inline-block" id="newEmpTelephone" value ="Telephone" />
                <input type="text" style="display:inline-block" id="newEmpRole" value ="Role" />
                <input type="text" style="display:inline-block" id="newEmpRate" value ="Rate" /><br><br>
                <input type="submit" class="managerButtons" id="addEmployee" value="Add Employee" onclick="addEmployee()"/>

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
                <table id="revPlace">
                    <thead>
                        <tr colspan="2"><h3>Sales For Selected Month</h3></tr>
                    <th>Fee for Date</th>
                    <th>Date's Time</th>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>

            <p>Show revenue by calender date:</p>  
            <script>
                $(function() {
                    $("#datepicker").datepicker();
                });
            </script>
            <p>Date: <input type="text" id="datepicker"></p> <button onclick="getRevByDate()">$ By Date</button>
        </div >
        <div style="width: 100%">
            <table id="dateGenRev">
                <thead>
                    <tr colspan="3"><h3>Sales For Selected Day</h3></tr>
                <th>Fee for Date</th>
                <th>Date's Time</th>
                <th>Total</th>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>

        <p>Show revenue by customer SSN</p>
        <p>SSN: <input type="text" id="custName"></p> <button onclick="getRevBySSN()">$ By Customer</button>             
    </div >
    <div style="width: 100%">
        <table id="nameGenRev">
            <thead>
                <tr colspan="3"><h3>Sales For Selected Customer</h3></tr>
            <th>Fee for Date</th>
            <th>Date's Time</th>
            <th>Total</th>
            </thead>
            <tbody>
            </tbody>
        </table>
        
                
    </div >
    <div style="width: 100%">
        <p>Show most active customers</p>
        <button onclick="actCust()">Most Active Customers</button>     
        <table id="mostActCustTable">
            <thead>
                <tr colspan="3"><h3>Activity level For Top Customers</h3></tr>
            <th>Name</th>
            <th>Level</th>
            </thead>
            <tbody>
            </tbody>
        </table>
        
    </div>
    
    <h1 style="color:yellow" onclick="getBestRep()" >Customer Rep. With Most Revenue: ~~Star Employee~~ </h1>

    <p style="color:yellow" id = "bestRep">Click Above</p>

    <h1 style="color:yellow" onclick="getBestCust()" >Customer With Most Revenue: ~~Star Customer~~ </h1>

    <p style="color:yellow" id = "bestCust">Click Above</p>

    <h1 style="color:yellow" onclick="bestRatedCust()" >Top Rated Customers (rating of 4 or 5): </h1>

    <div style="color:yellow" id = "bestRatedCust">Click Above</div>

    <h1 style="color:yellow" onclick="bestDateDays()" >Best Date Days: </h1>

    <div style="color:yellow" id = "bestDateDays">Click Above</div>


</div> 



</body>
</html>
