<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: palah
  Date: 15.09.2017
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/other/page.jspf" %>



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


<fmt:setLocale value="${language}" />
<fmt:setBundle basename="res" />


<div class="content">
    <h2>Welcome to </h2>

    <h3>Publication List</h3>
    <h3> <%

        String error = (String) request.getAttribute("error");
        if(error!=null){
            out.print(error);}
    %></h3>



    <form method="POST" action="${pageContext.request.contextPath}/createPublication">
        <table border="0">

            <tr>
                <td><fmt:message key="admin_name"/></td>
                <td><input type="text" name="name" value="${product.name}" /></td>
            </tr>
            <tr>
                <td><fmt:message key="admin_price"/></td>
                <td><input type="number" min="0" step="0.01"  name="price" value="${product.price}" /></td>
            </tr>
            <tr>
                <td><fmt:message key="admin_group"/></td>
                <td> <select name="group">
             <c:forEach items="${groups}" var="group" >
                    <option value="${group.id}">${group}</option>

                  </c:forEach>
                     </select></td>
                 </tr>
                 <tr>
                     <td colspan="2">
                         <input type="submit" value="Submit" />
                         <a href="productList"><fmt:message key="cancel"/></a>
                     </td>
                 </tr>
             </table>
         </form>

     </div>

     </body>
     </html>
