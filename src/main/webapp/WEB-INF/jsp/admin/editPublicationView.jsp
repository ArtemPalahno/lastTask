<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/other/page.jspf" %>
<%--
  Created by IntelliJ IDEA.
  User: palah
  Date: 15.09.2017
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>

    <title>CSS Template</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../../../style/style.css">
    <script src="../../../js/script.js"></script>


</head>
<body>


<jsp:include page="../other/side.jsp"></jsp:include>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="res"/>
<div class="content">
    <h2>Welcome to </h2>

    <h3>Publication List</h3>
    <h3><%

        String error = (String) request.getAttribute("error");
        if (error != null) {
            out.print(error);
        }
    %></h3>


    <c:if test="${not empty publication}">
        <form method="POST" action="${pageContext.request.contextPath}/editPublication">
            <input type="hidden" name="id" value="${publication.id}"/>
            <table border="0">
                <tr>
                    <td><fmt:message key="edit_publication_ID"/></td>
                    <td style="color:red;">${publication.id}</td>
                </tr>
                <tr>
                    <td><fmt:message key="admin_name"/></td>
                    <td><input type="text" name="name" value="${publication.bookName}"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="admin_price"/></td>
                    <td><input type="text" name="price" value="${publication.price}"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="admin_group"/></td>
                    <td><select name="group">
                        <c:forEach items="${groups}" var="group">
                            <option value="${group.id}">${group}</option>
                        </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Submit"/>
                        <a href="${pageContext.request.contextPath}/PublicationList"><fmt:message key="cancel"/></a>
                    </td>
                </tr>
            </table>
        </form>
    </c:if>

</div>

</body>
</html>

