<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ua.nure.palagno.db.entity.Publication" %>
<%@ page import="ua.nure.palagno.db.entity.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ua.nure.palagno.db.dao.classes.MySqlGroupDao" %>
<%@ page import="ua.nure.palagno.db.connection.MySQLConnectionUtils" %>
<%@ page isELIgnored="false"%><%--
  Created by IntelliJ IDEA.
  User: palah
  Date: 12.09.2017
  Time: 16:59
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
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="res" />
<jsp:include page="side.jsp"></jsp:include>
<div class="content">
    <h2>Welcome to </h2>

    <h3>Publication List</h3>
    <h3> <%

        String error = (String) request.getAttribute("error");
        if(error!=null){
            out.print(error);}
         %></h3>




    <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names..">
    <table class="sortable" id="myTable"  >
        <thead>
        <tr>
            <th><fmt:message key="admin_group"/></th>
            <th><fmt:message key="admin_name"/></th>
            <th><fmt:message key="admin_price"/></th>
            <c:if test="${user ne null}">
            <th><fmt:message key="publication_view_subscribe"/></th>
            </c:if>

        </tr></thead>
        <tbody>

        <%
            User user = (User) session.getAttribute("user");
            ArrayList<Publication> list = (ArrayList<Publication>) request.getAttribute("publicationList");
           for(Publication pub:list){
               out.print("<tr>");
               out.print("<td>"+ new MySqlGroupDao(MySQLConnectionUtils.getMySQLConnection()).get(pub.getGroupID())+"</td>");
               out.print("<td>"+pub.getBookName()+"</td>");
               out.print("<td>"+pub.getPrice()+"</td>");
               if(user!=null){
               out.print("<td>"+"<a href='"+"subscribePublication?code="+pub.getId()+"'"+">"+"subscribe"+"</a>"+"</td>");}
               out.print("</tr>");
           }

        %></tbody>


    </table>

</div>
<script>
    function myFunction() {
        // Declare variables
        var input, filter, table, tr, td, i;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTable");
        tr = table.getElementsByTagName("tr");

        // Loop through all table rows, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[1];
            if (td) {
                if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>
</body>
</html>
