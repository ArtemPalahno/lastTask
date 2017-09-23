<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: palah
  Date: 17.09.2017
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>


    <!-- reCAPTCHA with Auto language -->
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>

    <link rel="stylesheet" type="text/css" href="style/style1.css">
</head>
<body>


<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="res"/>
<form method="POST" action="/Login" name="myform">

    <div class="imgcontainer">
        <img src="image/img_avatar2.png" alt="Avatar" class="avatar">
    </div>

    <div class="container">

        <label><b><fmt:message key="admin_Email"/></b></label>
        <input type="text" placeholder="Enter Email" name="email" required>

        <label><b><fmt:message key="password"/></b></label>
        <input type="password" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
               placeholder="Enter Password (UpperCase, LowerCase, Number/SpecialChar and min 8 Chars)" name="psw" required>
        <div class="g-recaptcha"
             data-sitekey="6Ldq9jAUAAAAALqdx9lDNhDsqrqpKKidnlurSqYu"></div>

        <button type="submit"><fmt:message key="side_sign_in"/></button>
        <input type="checkbox" checked="checked" value="OK" name="rememberMe"><fmt:message key="remember"/>
    </div>

    <div class="container" style="background-color:#f1f1f1">
        <button type="button" class="cancelbtn" onclick="location.href='signUp.jsp'"><fmt:message
                key="sign_up"/></button>

        <span class="psw"><a href="/forgot.jsp"><fmt:message key="forgot_password"/>?</a></span>
    </div>

</form>

</body>
</html>
