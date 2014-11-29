<%-- 
    Document   : userHome
    Created on : Nov 18, 2014, 12:00:50 AM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script type='text/javascript' src='js/jquery.simplemodal.js'></script>
<script type='text/javascript' src='js/deleteProfile.js'></script>
<script type='text/javascript' src='js/deleteAccount.js'></script>
<script type='text/javascript' src='js/updateProfile.js'></script>
<script type='text/javascript' src='js/accountSelect.js'></script>
<script type='text/javascript' src='js/createProfile.js'></script>
<script type='text/javascript' src='js/createAccount.js'></script>
<script type='text/javascript' src='js/basic.js'></script>
<script type='text/javascript' src='js/like.js'></script>
<script type='text/javascript' src='js/date.js'></script>
<script src="js/profileSelect.js" type="text/javascript"></script>
<!DOCTYPE html>
<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id = "currentUser" class="User.Person" scope="request"/>
        <link type='text/css' href='css/basic.css' rel='stylesheet' media='screen' />
        <link type='text/css' href='css/style.css' rel='stylesheet' media='screen' />
        <title>User Home</title>
    </head>
    <body>
        <div>
            <!--Div for Profiles-->
            <div style="width: 25%; float:left; display:inline-block">
                <p style="display:inline-block; color:white;">Profile Name:</p>
                <!--Combo Box for profiles of this user-->
                <select style="display:inline-block" id="profileBox" onchange="readProfile()">
                    <option id="defaultOption">No Profile Selected</option>   
                    <c:forEach items="${profiles}" var="profile">
                        <option><c:out value="${profile.profileId}" /></option>
                    </c:forEach>
                </select>
                <br></br>
                <!--Table for the currently selected profile's data-->
                <table id="profileInfoTable" style="display:none;" >
                    <tbody>
                        <tr>
                            <th align="right" value="unchangeable">Picture: </th>
                            <td><img src="" id="profilePicture" style="max-width:200px; max-height: 200px;"/></td>
                        </tr>
                        <tr value="profileId"> 
                            <th align="right" value="unchangeable">Profile Id:</td>
                            <td id="profileId"> </td>
                        </tr>
                        <tr value="age">
                            <th align="right" value="unchangeable">Age:</td>
                            <td id="age"> </td>
                        </tr>
                        <tr value="ageRangeStart"> 
                            <th align="right" value="unchangeable">Age Range Start:</td>
                            <td id="ageRangeStart"> </td>
                        </tr>
                        <tr value="ageRangeEnd"> 
                            <th align="right" value="unchangeable">Age Range End:</td>
                            <td id="ageRangeEnd"> </td>
                        </tr>
                        <tr value="geoRange"> 
                            <th align="right" value="unchangeable">Geo-Range:</td>
                            <td id="geoRange"> </td>
                        </tr>
                        <tr value="gender"> 
                            <th align="right" value="unchangeable">Gender:</td>
                            <td id="gender"> </td>
                        </tr>
                        <tr value="hobbies"> 
                            <th align="right" value="unchangeable">Hobbies:</td>
                            <td id="hobbies"> </td>
                        </tr>
                        <tr value="height"> 
                            <th align="right" value="unchangeable">Height:</td>
                            <td id="height"> </td>
                        </tr>
                        <tr value="weight"> 
                            <th align="right" value="unchangeable">Weight:</td>
                            <td id="weight"> </td>
                        </tr>
                        <tr value="hairColor"> 
                            <th align="right" value="unchangeable">Hair Color:</td>
                            <td id="hairColor"> </td>
                        </tr>
                        <tr>
                            <th align="right" value="unchangeable">Profile Creation Date:</td>
                            <td id="profileCreationDate"> </td>
                        </tr>
                    </tbody>
                </table>
                <button id ="createProfileButton" class='basic'>Create New Profile</button>
                <button id ="deleteProfileButton" style="display:none" onclick="deleteProfile()">Delete Profile</button>
            </div>

            <!--Div for profile viewers-->
            <div style="width:50%; display:inline-block">
                <h1 align="center" style="color:white;">Hello ${currentUser.firstName} ${currentUser.lastName}!</h1>
                <table style="width: 800px;">
                    <thead>
                    <th>Profile Name</th>
                    <th>Age</th>
                    <th>Gender</th>
                    <th>Hobbies</th>
                    <th>Height</th>
                    <th>Weight</th>
                    <th>Hair Color</th>
                    </thead>
                    <tbody id="recProfileTable">

                    </tbody>
                </table>
            </div>

            <!--Div for Accounts-->
            <div style="width: 25%; float:right; display:inline-block">
                <div>
                    <button style="display:inline-block" id="logoutButton" onclick="window.location = 'index.jsp'">Log Out</button>
                    <p style="width:26%; display:inline-block"></p>
                    <div style="display:inline-block;">
                        <p style="display:inline-block; color:white;">Account Number:</p>
                        <!--Combo Box for accounts of this user-->
                        <select style="display:inline-block" id="accountBox" onchange="readAccount()">
                            <option id="defaultOption">No Account Selected</option>   
                            <c:forEach items="${accounts}" var="account">
                                <option><c:out value="${account.accountNumber}" /></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <!--Account Table-->
                <table id="accountInfoTable" style="display:none;" >
                    <tbody>
                        <tr> 
                            <th align="right" value="unchangeable">Account Number:</td>
                            <td id="accountNumber"> </td>
                        </tr>
                        <tr value="creditCardNumber">
                            <th align="right" value="unchangeable">Credit Card Number:</td>
                            <td id="creditCardNumber"> </td>
                        </tr>
                        <tr> 
                            <th align="right" value="unchangeable">Account Creation Date:</td>
                            <td id="accountCreationDate"> </td>
                        </tr>
                    </tbody>
                </table>
                <button id ="createAccountButton" class='createAccount' style="float:right">Create New Account</button>
                <button id ="deleteAccountButton" style="display:none; float:right" onclick="deleteAccount()">Delete Account</button>
            </div>
        </div>


        <!-- this is the modal for profile-->
        <div id="basic-modal-content">
            <h1>Please enter your profile information below</h1>
            <input type="text" name="profilePicture" placeholder="Insert Profile Picture URL" />
            <input type="text" name="profileId" placeholder="Profile Name" />
            <input type="text" name="age" placeholder="Age" />
            <input type="text" name="ageRangeStart" placeholder="Age Range Minimum" />
            <input type="text" name="ageRangeEnd" placeholder="Age Range Maximum" />
            <input type="text" name="geoRange" placeholder="Maximum Distance From You" />
            <input type="text" name="gender" placeholder="Gender" />
            <input type="text" name="hobbies" placeholder="Hobbies (Seperate with commas)" />
            <input type="text" name="height" placeholder="Height" />
            <input type="text" name="weight" placeholder="Weight" />
            <input type="text" name="hairColor" placeholder="Haircolor" />
            <input type="text" name="ssn" style="display:none" value=${currentUser.ssn} />
            <input type="text" name="email" style="display:none" value=${currentUser.email} />
            <input type="text" name="password" style="display:none" value=${currentUser.password} />
            <input type="submit" name="createProfile" class="createProfile-submit" value="Create Profile" onclick="checkProfile()" />
        </div>

        <div id="basic-modal-content-account">
            <h1>Please enter your account information below</h1>
            <input type="text" name="ccn" placeholder="Credit Card Number" />
            <input type="text" name="ssn" style="display:none" value=${currentUser.ssn} />
            <input type="submit" name="createProfile" class="createProfile-submit" value="Create Account" onclick="createAccount()" />
        </div>

        <div id="basic-modal-content-viewProfile">
            <table style="width: 250px;">
                <thead>
                <th>Profile Name</th>
                <th>Age</th>
                <th>Gender</th>
                <th>Hobbies</th>
                <th>Height</th>
                <th>Weight</th>
                <th>Hair Color</th>
                </thead>
                <tbody id="viewProfileTable">

                </tbody>
            </table>
            <form>
                
                <fieldset class="date"> 
                    <legend> Date </legend> 
                    <label for="month">Month</label> 
                    <select id="month" /> 
                    <option value="1">January</option>       
                    <option value="2">February</option>       
                    <option value="3">March</option>       
                    <option value="4">April</option>       
                    <option value="5">May</option>       
                    <option value="6">June</option>       
                    <option value="7">July</option>       
                    <option value="8">August</option>       
                    <option value="9">September</option>       
                    <option value="10">October</option>       
                    <option value="11">November</option>       
                    <option value="12">December</option>       
                    </select> - 
                    <label for="day_start">Day</label> 
                    <select id="day" /> 
                    <option value="1">1</option>       
                    <option value="2">2</option>       
                    <option value="3">3</option>       
                    <option value="4">4</option>       
                    <option value="5">5</option>       
                    <option value="6">6</option>       
                    <option value="7">7</option>       
                    <option value="8">8</option>       
                    <option value="9">9</option>       
                    <option value="10">10</option>       
                    <option value="11">11</option>       
                    <option value="12">12</option>       
                    <option value="13">13</option>       
                    <option value="14">14</option>       
                    <option value="15">15</option>       
                    <option value="16">16</option>       
                    <option value="17">17</option>       
                    <option value="18">18</option>       
                    <option value="19">19</option>       
                    <option value="20">20</option>       
                    <option value="21">21</option>       
                    <option value="22">22</option>       
                    <option value="23">23</option>       
                    <option value="24">24</option>       
                    <option value="24">25</option>       
                    <option value="25">26</option>       
                    <option value="26">27</option>       
                    <option value="27">28</option>       
                    <option value="28">29</option>       
                    <option value="29">30</option>       
                    <option value="30">31</option>       
                    </select> - 
                    <label for="year">Year</label> 
                    <select id="year" />     
                    <option value="2014">2014</option>       
                    <option value="2015">2015</option>         
                    </select> 
                    <label for="hour">Hour</label>
                    <select id="hour" />     
                    <option value="1">1</option>   
                    <option value="2">2</option>   
                    <option value="3">3</option>   
                    <option value="4">4</option> 
                    <option value="5">5</option> 
                    <option value="6">6</option> 
                    <option value="7">7</option> 
                    <option value="8">8</option> 
                    <option value="9">9</option> 
                    <option value="10">10</option> 
                    <option value="11">11</option> 
                    <option value="12">12</option> 
                    <option value="13">13</option> 
                    <option value="14">14</option> 
                    <option value="15">15</option> 
                    <option value="16">16</option> 
                    <option value="17">17</option> 
                    <option value="18">18</option> 
                    <option value="19">19</option> 
                    <option value="20">20</option> 
                    <option value="21">21</option> 
                    <option value="22">22</option> 
                    <option value="23">23</option> 
                    </select> 
                    <label for="minute">- Minute</label>
                    <select id="minute" />     
                    <option value="00">00</option>   
                    <option value="05">05</option>   
                    <option value="10">10</option>   
                    <option value="15">15</option> 
                    <option value="20">20</option> 
                    <option value="25">25</option> 
                    <option value="30">30</option> 
                    <option value="35">35</option> 
                    <option value="40">40</option> 
                    <option value="45">45</option> 
                    <option value="50">50</option> 
                    <option value="55">55</option> 
                    </select> 
                    
                </fieldset> 
                <input id="location" placeholder="Location" type="text">
                <input id="comments" placeholder="Comments" type="text" style="float:right">
            </form>
            <button id="makeDateButton" onclick="makeDate()">Make Date</button>
            <button id="likeButton" onclick="makeLike()">Like</button>
            <button id="referralButton" onclick="makeReferral()">Refer</button>
        </div>
    </body>
</html>
