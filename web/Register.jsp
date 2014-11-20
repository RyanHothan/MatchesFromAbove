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
        <form  id = "registering" action="http://localhost:8080/MatchesFromAbove/RegisterHelp" method = "POST">
          <input type="text" name="email" placeholder="Email">
          <input type="password" name="password" placeholder="Password">
          <input type="submit" name="register" class="register register-submit" value="register" form ="registering">
        </form>
    </body>
</html>
