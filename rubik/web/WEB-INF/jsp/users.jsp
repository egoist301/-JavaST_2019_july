<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11.10.2019
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.get('locale').value}"/>
<fmt:setBundle basename="property/localization"/>
<%@taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title><fmt:message key="header.users"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr class="my-5">
<div class="container">
    <div>
        <h2><fmt:message key="header.users"/></h2>
    </div>
    <div class="card mb-3 div-bg table-responsive" style="max-width: 1150px;">
        <table class="table table-hover table-bordered">
            <%--<div class="row after-header">
                <div class="col-12">
                    <h2></h2>
                    <table class="table table-hover">--%>
            <thead class="send-button-color">
            <tr>
                <td><fmt:message key="label.id"/></td>
                <td><fmt:message key="label.username"/></td>
                <td><fmt:message key="label.role"/></td>
                <td><fmt:message key="label.phone"/></td>
                <td><fmt:message key="label.email"/></td>
                <td><fmt:message key="label.blocked"/></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <jsp:useBean id="user"
                             type="by.training.catalog.bean.User"/>
                <tr>
                    <td>
                        <label>${user.id}</label>
                    </td>
                    <td>
                        <label>${user.username}</label>
                    </td>
                    <td>
                        <label>${user.role}</label>
                    </td>
                    <td>
                        <label><a href="tel:${user.phone}">${user.phone}</a>
                        </label>
                    </td>
                    <td>
                        <label><a href="mailto:${user.email}">${user.email}</a>
                        </label>
                    </td>
                    <td>
                        <label>${user.blocked}</label>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<ctgg:pagination page="${page}" lastPage="${lastPage}" pageURL="users"/>
<hr class="my-5">
<ctgg:footer/>
</body>
</html>
