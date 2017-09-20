<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: palah
  Date: 16.09.2017
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="style/style.css">
<html>
<head>
    <title></title>
</head>
<body>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="res"/>
<div class='fullscreenDiv'>
    <div class="center">
        <c:if test="${not empty OK}">
            <fmt:message key="success_pay"/><br>
        </c:if>
        <button type="button"   onclick="location.href='/userPage'"><fmt:message key="back_to_user_page"/></button>
    </div>
</div>​
</body>
</html>