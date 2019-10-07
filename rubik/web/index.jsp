<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 26.09.2019
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>
<%@include file="header.jsp"%>
<%--<c:choose>
    <c:when test="${empty locale}">
        <fmt:setLocale value="en_US"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${locale}"/>
    </c:otherwise>
</c:choose>
<fmt:bundle basename="property/localization">--%>
<html>
<head>
    <title>Rubik</title>
</head>
<body>
<hr class="my-5">
<footer>
    <ctg:info-time/>
    <div class="container">
        <div class="d-flex justify-content-between">
            <div class="footer-left">
                <a href="#">Terms and conditions</a>
                <a href="#">Privacy</a>
                <p class="pull-left">Copyright © 2019</p>
            </div>
            <div class="footer-right">
                <span>Follow us:</span>
                <a href="http://facebook.com"><em
                        class="fab fa-facebook-f"></em></a>
                <a href="http://twitter.com"><em
                        class="fab fa-twitter"></em></a>
                <a href="http://instagram.com"><em
                        class="fab fa-instagram"></em></a>
            </div>
        </div>
    </div>
</footer>
</body>
</html>