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
    <link rel="stylesheet" type="text/css" href="style/style1.css">
    <script>
        function validateForm() {
            var x = document.forms["myForm"]["uEmail"].value;
            var atpos = x.indexOf("@");
            var dotpos = x.lastIndexOf(".");
            if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
                alert("Not a valid e-mail address");
                return false;
            }
        }
    </script>
</head>
<body>


<fmt:setLocale value="${language}" />
<fmt:setBundle basename="res" />

<body>
<form action="Register" method = "POST" name="myForm" onsubmit="return validateForm();">
    <div class="imgcontainer">
        <img src="image/photo.jpg" alt="Avatar" class="avatar">
    </div>

    <div class="container">
        <label><b><fmt:message key="name"/></b></label>
        <input type="text" placeholder="Enter Name" name="uName" required>
        <label><b><fmt:message key="surname"/></b></label>
        <input type="text" placeholder="Enter Surname" name="uSurname" required>
        <label><b><fmt:message key="admin_Email"/></b></label>
        <input type="email" placeholder="Enter Email" name="uEmail" required>
        <label><b><fmt:message key="password"/></b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <button type="submit"><fmt:message key="sign_up"/></button>
    </div>

    <div class="container" style="background-color:#f1f1f1">
        <button type="button" class="cancelbtn"><fmt:message key="cancel"/></button>
    </div>
</form>
</body>
</html>