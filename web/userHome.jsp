<%-- 
    Document   : userHome
    Created on : Nov 18, 2014, 12:00:50 AM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/profileSelect.js" type="text/javascript"></script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script type='text/javascript' src='js/basic.js'></script>
<script type='text/javascript' src='js/jquery.simplemodal.js'></script>
<script type='text/javascript' src='js/deleteProfile.js'></script>
<script type='text/javascript' src='js/deleteAccount.js'></script>
<script type='text/javascript' src='js/updateProfile.js'></script>
<script type='text/javascript' src='js/accountSelect.js'></script>

<!DOCTYPE html>
<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id = "currentUser" class="User.Person" scope="request"/>
        <link type='text/css' href='css/basic.css' rel='stylesheet' media='screen' />
        <link type='text/css' href='css/style.css' rel='stylesheet' media='screen' />
        <title>${currentUser.email}</title>
    </head>
    <body>
        <!--Div for Profiles-->
        <div style="max-width: 25%; float:left">
            <h1>Hello ${currentUser.firstName} ${currentUser.lastName}</h1>
            <!--Combo Box for profiles of this user-->
            <select id="profileBox" onchange="readProfile()">
                <option id="defaultOption">No Profile Selected</option>   
                <c:forEach items="${profiles}" var="profile">
                    <option><c:out value="${profile.profileId}" /></option>
                </c:forEach>
            </select>
            <!--Table for the currently selected profile's data-->
            <table id="profileInfoTable" style="display:none;" >
                <tbody>
                    <tr value="profileId"> 
                        <td align="right" value="unchangeable">Profile Id:</td>
                        <td id="profileId"> </td>
                    </tr>
                    <tr value="age">
                        <td align="right" value="unchangeable">Age:</td>
                        <td id="age"> </td>
                    </tr>
                    <tr value="ageRangeStart"> 
                        <td align="right" value="unchangeable">Age Range Start:</td>
                        <td id="ageRangeStart"> </td>
                    </tr>
                    <tr value="ageRangeEnd"> 
                        <td align="right" value="unchangeable">Age Range End:</td>
                        <td id="ageRangeEnd"> </td>
                    </tr>
                    <tr value="geoRange"> 
                        <td align="right" value="unchangeable">Geo-Range:</td>
                        <td id="geoRange"> </td>
                    </tr>
                    <tr value="gender"> 
                        <td align="right" value="unchangeable">Gender:</td>
                        <td id="gender"> </td>
                    </tr>
                    <tr value="hobbies"> 
                        <td align="right" value="unchangeable">Hobbies:</td>
                        <td id="hobbies"> </td>
                    </tr>
                    <tr value="height"> 
                        <td align="right" value="unchangeable">Height:</td>
                        <td id="height"> </td>
                    </tr>
                    <tr value="weight"> 
                        <td align="right" value="unchangeable">Weight:</td>
                        <td id="weight"> </td>
                    </tr>
                    <tr value="hairColor"> 
                        <td align="right" value="unchangeable">Hair Color:</td>
                        <td id="hairColor"> </td>
                    </tr>
                    <tr>
                        <td align="right" value="unchangeable">Profile Creation Date:</td>
                        <td id="profileCreationDate"> </td>
                    </tr>
                </tbody>
            </table>
            <button id ="createProfileButton" class='basic'>Create New Profile</button>
            <button id ="deleteProfileButton" style="display:none" onclick="deleteProfile()">Delete Profile</button>
        </div>
            
        <!--Div for profile viewers-->
        <div style="max-width:50%">
            <table>
                <tr id="header">
                    <th>
                        
                    </th>
                </tr>
            </table>
        </div>
        
        <!--Div for Accounts-->
        <div style="max-width: 25%; float:right">
            <!--Combo Box for accounts of this user-->
            <select style="float:right" id="accountBox" onchange="readAccount()">
                <option id="defaultOption">No Account Selected</option>   
                <c:forEach items="${accounts}" var="account">
                    <option><c:out value="${account.accountNumber}" /></option>
                </c:forEach>
            </select>

            <!--Account Table-->
            <table id="accountInfoTable" style="display:none;" >
                <tbody>
                    <tr> 
                        <td align="right" value="unchangeable">Account Number:</td>
                        <td id="accountNumber"> </td>
                    </tr>
                    <tr value="creditCardNumber">
                        <td align="right" value="unchangeable">Credit Card Number:</td>
                        <td id="creditCardNumber"> </td>
                    </tr>
                    <tr> 
                        <td align="right" value="unchangeable">Account Creation Date:</td>
                        <td id="accountCreationDate"> </td>
                    </tr>
                </tbody>
            </table>
            <button id ="createAccountButton" class='createAccount' style="float:right">Create New Account</button>
            <button id ="deleteAccountButton" style="display:none; float:right" onclick="deleteAccount()">Delete Account</button>
        </div>



        <!-- this is the modal for profile-->
        <div id="basic-modal-content">
            <h1>Please enter your profile information below</h1>
            <form  id = "createProfileForm" action="/MatchesFromAbove/CreateProfileHelp" method = "POST">
                <input type="text" name="profileId" placeholder="Profile Name">
                <input type="text" name="age" placeholder="Age">
                <input type="text" name="ageRangeStart" placeholder="Age Range Minimum">
                <input type="text" name="ageRangeEnd" placeholder="Age Range Maximum">
                <input type="text" name="geoRange" placeholder="Maximum Distance From You">
                <input type="text" name="gender" placeholder="Gender">
                <input type="text" name="hobbies" placeholder="Hobbies (Seperate with commas)">
                <input type="text" name="height" placeholder="Height">
                <input type="text" name="weight" placeholder="Weight">
                <input type="text" name="hairColor" placeholder="Haircolor">
                <input type="text" name="ssn" style="display:none" value=${currentUser.ssn}>
                <input type="text" name="email" style="display:none" value=${currentUser.email}>
                <input type="text" name="password" style="display:none" value=${currentUser.password}>
                <input type="submit" name="createProfile" class="createProfile-submit" value="Create Profile" form ="createProfileForm">
            </form>
        </div>

        <div id="basic-modal-content-account">
            <h1>Please enter your profile information below</h1>
            <form  id = "createProfileForm" action="/MatchesFromAbove/CreateProfileHelp" method = "POST">
                <input type="text" name="profileId" placeholder="Profile Name">
                <input type="text" name="age" placeholder="Age">
                <input type="text" name="ageRangeStart" placeholder="Age Range Minimum">
                <input type="text" name="ageRangeEnd" placeholder="Age Range Maximum">
                <input type="text" name="geoRange" placeholder="Maximum Distance From You">
                <input type="text" name="gender" placeholder="Gender">
                <input type="text" name="hobbies" placeholder="Hobbies (Seperate with commas)">
                <input type="text" name="height" placeholder="Height">
                <input type="text" name="weight" placeholder="Weight">
                <input type="text" name="hairColor" placeholder="Haircolor">
                <input type="text" name="ssn" style="display:none" value=${currentUser.ssn}>
                <input type="text" name="email" style="display:none" value=${currentUser.email}>
                <input type="text" name="password" style="display:none" value=${currentUser.password}>
                <input type="submit" name="createProfile" class="createProfile-submit" value="Create Profile" form ="createProfileForm">
            </form>
        </div>
    </body>
</html>
