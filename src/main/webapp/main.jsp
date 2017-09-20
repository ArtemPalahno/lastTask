<%@ page import="ua.nure.palagno.db.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: palah
  Date: 14.09.2017
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>CSS Template</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../../../style/style.css">

</head>
<body>
<jsp:include page="WEB-INF/jsp/other/side.jsp"></jsp:include>
<input type="hidden" name="viewid" value="/main.jsp">
<div class="content">
    <h2>Welcome to </h2>
    <p>A full-height, fixed sidenav and content.</p>
</div>

</body>
</html>
