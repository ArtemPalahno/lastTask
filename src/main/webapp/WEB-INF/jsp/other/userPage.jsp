<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.nure.palagno.db.entity.User" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="ua.nure.palagno.db.entity.Publication" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ua.nure.palagno.db.dao.classes.MySqlGroupDao" %>
<%@ page import="ua.nure.palagno.db.connection.MySQLConnectionUtils" %>
<%@ page import="ua.nure.palagno.db.dao.classes.MySqlSubscriptionsDao" %>
<%--
  Created by IntelliJ IDEA.
  User: palah
  Date: 11.09.2017
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CSS Template</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../../../style/style.css">
    <script src="../../../js/script.js"></script>

</head>
<body>
<jsp:include page="side.jsp"></jsp:include>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="res"/>
<div class="content">
    <h2>Welcome to </h2>
    <p>Your personal page</p>
    <form method="POST" action="/pay" name="myform">
        <p><fmt:message key="user_page_balance"/>
            <fmt:formatNumber type = "number"
                              maxFractionDigits = "2" value = "${user.money}" />

        </p>
        <fmt:message key="paying"/> : <input type="number" step="0.01" min="0" max="100" name="payment"
                                             value="0.00"><br>
        <button type="submit"><fmt:message key="account_top_up"/></button>
    </form>

    <p><fmt:message key="your_subscription"/></p>

    <p style="color: #111111;"></p>

    <table class="sortable">
        <thead>
        <tr>
            <th><fmt:message key="admin_group"/></th>
            <th><fmt:message key="admin_name"/></th>
            <th><fmt:message key="admin_price"/></th>
        </tr>
        </thead>
        <tbody>
        <% User user = (User) session.getAttribute("user");
            //   ArrayList<Publication> list = (ArrayList<Publication>) new MySqlSubscriptionsDao(MySQLConnectionUtils.getMySQLConnection()).
            //         getAllPublicationsByUserID(user.getId());
            ArrayList<Publication> list = (ArrayList<Publication>) new MySqlSubscriptionsDao(MySQLConnectionUtils.getMySQLConnection()).
                    getAllPublicationsByUserID(user.getId());
            if (list.isEmpty()) {
                out.print("you don't have subscriptions  ,yet");
            } else {
                for (Publication pub : list) {
                    out.print("<tr>");
                    out.print("<td>" + new MySqlGroupDao(MySQLConnectionUtils.getMySQLConnection()).get(pub.getGroupID()) + "</td>");
                    out.print("<td>" + pub.getBookName() + "</td>");
                    out.print("<td>" + pub.getPrice() + "</td>");
                    out.print("</tr>");
                }
            }
        %>
        </tbody>
    </table>

</div>

</body>
</html>