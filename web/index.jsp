<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Log-in</title>
  <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
   <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
</head>

<body id="index">
  <button onclick="window.location = 'help.html'">Help</button>
  <button onclick="window.location = 'ER.html'">ER Diagram</button>
  <div class="login-card">
  <h1>Log-in</h1><br>
  <form  id = "loginForm" action="/MatchesFromAbove/loginhelp" method = "POST">
    <input type="text" name="email" placeholder="Email">
    <input type="password" name="password" placeholder="Password">
    <input type="submit" name="login" class="login login-submit" value="login" form ="loginForm">
  </form>

  <div class="login-help">
    <a href="/MatchesFromAbove/Register.jsp">Register</a>
  </div>
</div>

<!-- <div id="error"><img src="https://dl.dropboxusercontent.com/u/23299152/Delete-icon.png" /> Your caps-lock is on.</div> -->

</body>

</html>
