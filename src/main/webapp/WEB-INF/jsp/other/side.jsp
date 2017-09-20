<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="ua.nure.palagno.db.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: palah
  Date: 16.09.2017
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="sidenav">
    <c:out value="${language}"/>
    <%--    <c:if test="${not empty language}">
          <c:set var="language" value="${param.language}" scope="session" />
      </c:if>
   <c:if test="${empty language}">
          <c:set var="language" value="ru" scope="session"/>
      </c:if>--%>
    <fmt:setLocale value="${language}" />
    <fmt:setBundle basename="res" />


    <c:if test="${empty user}">
        <a href="signin.jsp"><fmt:message key="side_sign_in"/></a>
    </c:if>
    <c:if test="${not empty user}">
        <a href="/userPage">${user.name}</a>
    </c:if>


    <a href="/PublicationList"><fmt:message key="side_publication"/></a>
    <a href="#"><fmt:message key="side_about_us"/></a>
    <c:if test="${not empty user}">
        <a href="/Logout"><fmt:message key="side_logout"/></a>
    </c:if>
    <form action="/locale" method="post">
        <select name="loc">
            <option value="ru">Русский</option>
            <option value="en" selected>English</option>
        </select>
        <p></p>
        <input type="submit" value="Submit">
    </form>
</div>
