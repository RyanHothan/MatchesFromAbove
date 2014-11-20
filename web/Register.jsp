<%-- 
    Document   : Register
    Created on : Nov 19, 2014, 9:04:49 PM
    Author     : Ryan Hothan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Register</h1><br>
        <form  id = "registering" action="/MatchesFromAbove/RegisterHelp" method = "POST">
          <input type="text" name="email" placeholder="Email">
          <input type="text" name="ssn" placeholder="Social Security Number">
          <input type="text" name="firstname" placeholder="First Name">
          <input type="text" name="lastname" placeholder="Last Name">
          <input type="text" name="street" placeholder="Street Address">
          <input type="text" name="city" placeholder="City">
          <input type="text" name="state" placeholder="State">
          <input type="text" name="zip" placeholder="Zipcode">
          <input type="text" name="telephone" placeholder="Telephone <(111) 111-1111>">
          <input type="password" name="password" placeholder="Password">
          <input type="submit" name="register" class="register register-submit" value="register" form ="registering">
        </form>
    </body>
</html>
