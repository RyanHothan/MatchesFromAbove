<%-- 
    Document   : employeeHome
    Created on : Nov 19, 2014, 12:34:14 PM
    Author     : Javier
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:useBean id = "currentUser" class="User.Person" scope="request"/>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table>
            <c:forEach items="${customers}"  var = "customer">
                <tr>
                    <td><c:out value="${customer.ssn}" /></td>
                    <td><c:out value="${customer.ppp}" /></td>
                    <td><c:out value="${customer.rating}" /></td>
                    <td><c:out value="${customer.lastActiveDate}" /></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>