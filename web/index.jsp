<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Log-in</title>
  <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
   <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
</head>

<body>
  <div class="login-card">
    <h1>Log-in</h1><br>
  <form  id = "data" action="http://localhost:8080/MatchesFromAbove/loginhelp" method = "POST">
    <input type="text" name="email" placeholder="Email">
    <input type="password" name="password" placeholder="Password">
    <input type="submit" name="login" class="login login-submit" value="login" form ="data">
  </form>

  <div class="login-help">
    <a href="http://localhost:8080/MatchesFromAbove/Register.jsp">Register</a> • <a href="#">Forgot Password</a>
  </div>
</div>

<!-- <div id="error"><img src="https://dl.dropboxusercontent.com/u/23299152/Delete-icon.png" /> Your caps-lock is on.</div> -->

</body>

</html>