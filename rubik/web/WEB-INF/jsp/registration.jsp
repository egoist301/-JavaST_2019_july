<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 04.10.2019
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${cookie.get('locale').value}"/>
<fmt:setBundle basename="property/localization"/>
<html lang="en">
<head>
    <title><fmt:message key="registration"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr class="my-5">
<div class="grid-posts">
    <div class="container div-bg">
        <h2 class="text-center h2-pad-top"><fmt:message key="registration"/>
        </h2>
        <c:if test="${not empty error}">
            <div class="text-center text-warning">
                <label class="text">
                    <p><fmt:message key="attention"/></p>
                    <fmt:message key="${error}"/>
                </label>
            </div>
        </c:if>
        <form action="registr.html" method="post">
            <div class="form-group">
                <label for="exampleName"><fmt:message
                        key="registration.username"/></label>
                <input type="text" class="form-control" id="exampleName"
                       placeholder="<fmt:message
                               key="registration.placeholder.username"/>"
                       required name="username" minlength="4"
                       maxlength="16">
            </div>
            <div class="form-group">
                <label for="exampleMail"><fmt:message
                        key="registration.email"/></label>
                <input type="email" class="form-control" id="exampleMail"
                       placeholder="<fmt:message
                               key="registration.placeholder.email"/>" required
                       name="email" minlength="8" maxlength="256">
            </div>
            <div class="form-group">
                <label for="examplePhone"><fmt:message
                        key="registration.phone"/></label>
                <input type="tel" pattern="[0-9]{3}-[0-9]{2}-[0-9]{2}"
                       class="form-control" id="examplePhone"
                       placeholder="<fmt:message
                               key="registration.placeholder.phone"/>" required
                       name="phone" minlength="9" maxlength="9">
            </div>
            <div class="form-group">
                <label
                        for="exampleInputPassword1"><fmt:message
                        key="registration.password"/> </label>
                <input type="password" class="form-control form-control-sm"
                       id="exampleInputPassword1" placeholder=
                               "<fmt:message
                               key="registration.placeholder.password"/>"
                       required name="password" minlength="4" maxlength="16">
            </div>
            <button type="submit" class="btn btn-primary" id="submit">
                <fmt:message key="registration"/>
            </button>
            ${pageContext.session.removeAttribute("error")}
        </form>
    </div>
</div>
<hr class="my-5">

<!-- Footer -->
<ctgg:footer/>
</body>
</html>