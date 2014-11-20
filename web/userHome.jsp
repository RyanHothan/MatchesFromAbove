<%-- 
    Document   : userHome
    Created on : Nov 18, 2014, 12:00:50 AM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id = "currentUser" class="User.Person" scope="request"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script src="js/ProfileSelect.js" type="text/javascript"></script>
        
         <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
        <title>${currentUser.email}</title>
    </head>
    <body>
        <h1>Hello ${currentUser.firstName} ${currentUser.lastName}</h1>
        <!--Combo Box for profiles of this user-->
        <select id="profileBox" onchange="readProfile()">
            <option id="defaultOption">No Profile Selected</option>
            <c:forEach items="${profiles}" var="profile">
                <option><c:out value="${profile.profileId}" /></option>
            </c:forEach>
        </select>
        <!--Table for the currently selected profile's data-->
        <table id="infoTable"  style="display:none" >
            <tbody>
            <tr> 
                <td align="right">Profile Id:</td>
                <td id="profileId"> </td>
            </tr>
            <tr> 
                <td align="right">Age:</td>
                <td id="age"> </td>
            </tr>
            <tr> 
                <td align="right">Age Range Start:</td>
                <td id="ageRangeStart"> </td>
            </tr>
            <tr> 
                <td align="right">Age Range End:</td>
                <td id="ageRangeEnd"> </td>
            </tr>
            <tr> 
                <td align="right">Geo-Range:</td>
                <td id="geoRange"> </td>
            </tr>
            <tr> 
                <td align="right">Gender:</td>
                <td id="gender"> </td>
            </tr>
            <tr> 
                <td align="right">Hobbies:</td>
                <td id="hobbies"> </td>
            </tr>
            <tr> 
                <td align="right">Height:</td>
                <td id="height"> </td>
            </tr>
            <tr> 
                <td align="right">Weight:</td>
                <td id="weight"> </td>
            </tr>
            <tr> 
                <td align="right">Hair Color:</td>
                <td id="hairColor"> </td>
            </tr>
            <tr> 
                <td align="right">Profile Creation Date:</td>
                <td id="profileCreationDate"> </td>
            </tr>
            </tbody>
        </table>
    </body>
</html>
