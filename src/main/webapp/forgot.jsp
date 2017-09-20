<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: palah
  Date: 19.09.2017
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot password</title>
</head>
<body>


<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="res"/>
<form method="POST" action="/sendEmail" name="myform">

    <div class="imgcontainer">
        <img src="image/password.png" alt="Avatar" class="avatar">
    </div>

    <div class="container">
        <label><b><fmt:message key="admin_Email"/></b></label>
        <input type="text" placeholder="Enter Email" name="email" required>
        <button type="submit"><fmt:message key="side_sign_in"/></button>
           </div>


</form>
</body>
</html>
