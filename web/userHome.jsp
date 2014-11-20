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
        <title>${currentUser.email}</title>
    </head>
    <body>
        <h1>Hello ${currentUser.firstName} ${currentUser.lastName}</h1>
        <!--Combo Box for profiles of this user-->
        <select id="profileBox" onchange="readProfile()">
            <option>No Profile Selected</option>
            <c:forEach items="${profiles}" var="profile">
                <option><c:out value="${profile.profileId}" /></option>
            </c:forEach>
        </select>
        <!--Table for the currently selected profile's data-->
        <table id="infoTable" border="1">
            <tr> 
                <td align="right">Profile Id:</td>
                <td id="profileId"> Hello</td>
            </tr>
            <tr> 
                <td align="right">Age:</td>
                <td id="age"> Hello</td>
            </tr>
            <tr> 
                <td align="right">Age Range Start:</td>
                <td id="ageRangeStart"> Hello</td>
            </tr>
            <tr> 
                <td align="right">Age Range End:</td>
                <td id="ageRangeEnd"> Hello</td>
            </tr>
            <tr> 
                <td align="right">Geo-Range:</td>
                <td id="geoRange"> Hello</td>
            </tr>
            <tr> 
                <td align="right">Gender:</td>
                <td id="gender"> Hello</td>
            </tr>
            <tr> 
                <td align="right">Hobbies:</td>
                <td id="hobbies"> Hello</td>
            </tr>
            <tr> 
                <td align="right">Height:</td>
                <td id="height"> Hello</td>
            </tr>
            <tr> 
                <td align="right">Weight:</td>
                <td id="weight"> Hello</td>
            </tr>
            <tr> 
                <td align="right">Hair Color:</td>
                <td id="hairColor"> Hello</td>
            </tr>
            <tr> 
                <td align="right">Profile Creation Date:</td>
                <td id="profileCreationDate"> Hello</td>
            </tr>
        </table>
    </body>
</html>
