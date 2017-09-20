<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<%@ include file="../other/page.jspf" %>
<%--
  Created by IntelliJ IDEA.
  User: palah
  Date: 15.09.2017
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>

    <title>CSS Template</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../../../style/style.css">
    <script src="../../../js/script.js"></script>


</head>
<body>


<jsp:include page="../other/side.jsp"></jsp:include>


<fmt:setLocale value="${language}" />
<fmt:setBundle basename="res" />
<div class="content">
    <h2>Welcome to </h2>

    <h3>Publication List</h3>
    <h3></h3>

    <div class="container">

    <table class="sortable"  >
        <thead>
        <tr>
            <th><fmt:message key="admin_group"/></th>
            <th><fmt:message key="admin_name"/></th>
            <th><fmt:message key="admin_price"/></th>
            <th><fmt:message key="admin_edit"/></th>
            <th><fmt:message key="admin_delete"/></th>
        </tr></thead>
        <tbody>

        <c:forEach items="${publicationList}" var="product" >
            <tr>
                <td>${product.groupName}</td>
                <td>${product.bookName}</td>
                <td>${product.price}</td>
                <td>
                    <a href="editPublication?id=${product.id}">Edit</a>
                </td>
                <td>
                    <a href="deletePublication?id=${product.id}">Delete</a>
                </td>
            </tr>
        </c:forEach></tbody>


    </table>
        <a href="createPublication">Create Publication</a>

        <table class="sortable">
            <thead>
            <tr>
                <th><fmt:message key="admin_Email"/></th>
                <th><fmt:message key="admin_isBlocked"/></th>
                <th><fmt:message key="admin_Blocked_unBlocked"/></th>


            </tr></thead>
            <tbody>
              <c:forEach items="${usersList}" var="users" >
               <c:if test="${user.id ne users.id}">
                <tr>
                    <td>${users.email}</td>
                    <td>${users.blocked}</td>
                    <td>
                        <a href="changeStatus?id=${users.id}">Change</a>
                    </td>

                </tr>
            </c:if>
            </c:forEach></tbody>

        </table>

    </div>
</div>

</body>
</html>
