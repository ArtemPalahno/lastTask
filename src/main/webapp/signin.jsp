
<%@ page import="ua.nure.palagno.facebook.CreateFBConnection" %>
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
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id"
          content="250566326923-vvh3009tvpb434tunjcermkd6djk0778.apps.googleusercontent.com">
    <link rel="stylesheet" type="text/css" href="style/style1.css">
</head>
<body>
<script>
    function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        console.log('ID: ' + profile.getId());
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail());
        console.log('id_token: ' + googleUser.getAuthResponse().id_token);

        //do not post above info to the server because that is not safe.
        //just send the id_token

        var redirectUrl = 'fb';
        //using jquery to post data dynamically
        var form = $('<form action="' + redirectUrl + '" method="post">' +
            '<input type="text" name="id_token" value="' +
            googleUser.getAuthResponse().id_token + '" />' +
            '</form>');
        $('body').append(form);
        form.submit();
    }

</script>

<fmt:setLocale value="${language}" />
<fmt:setBundle basename="res" />
<form  method = "POST" action="/Login" name="myform">

    <div class="imgcontainer">
        <img src="image/img_avatar2.png" alt="Avatar" class="avatar">
    </div>

    <div class="container">

        <label><b><fmt:message key="admin_Email"/></b></label>
        <input type="text" placeholder="Enter Username" name="email" required>

        <label><b><fmt:message key="password"/></b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>
        <div class="g-recaptcha"
             data-sitekey="6Ldq9jAUAAAAALqdx9lDNhDsqrqpKKidnlurSqYu"></div>

        <button type="submit"><fmt:message key="side_sign_in"/></button>
        <input type="checkbox" checked="checked" value="OK" name="rememberMe"><fmt:message key="remember"/>
    </div>

    <div class="container" style="background-color:#f1f1f1">
        <button type="button" class="cancelbtn"  onclick="location.href='signUp.jsp'"><fmt:message key="sign_up"/></button>
        <%
            CreateFBConnection fbConnection = new CreateFBConnection();
        %>
        <h3>Test Facebook Authentication</h3>
        <a href="<%=fbConnection.getFBAuthUrl()%>">Facebook Login</a>
        <h2>Servlet OAuth example</h2>
        <br>
        <div class="g-signin2" data-onsuccess="onSignIn"></div>
        <span class="psw"><a href="/forgot.jsp"><fmt:message key="forgot_password"/>?</a></span>
    </div>

</form>

</body>
</html>
