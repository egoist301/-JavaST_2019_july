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

    <title><fmt:message key="profile"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr class="my-5">
<!-- Form to log in-->
<div class="grid-posts">
    <div class="container div-bg">
        <h2 class="text-center h2-pad-top"><fmt:message key="profile"/></h2>
        <form action="edit.html" method="post">
            <div class="form-group">
                <label for="exampleName"><fmt:message
                        key="profile.username"/></label>
                <input type="text" class="form-control" id="exampleName"
                       placeholder="${user.username}" name="username"
                       required
                       disabled>
            </div>
            <div class="form-group">
                <label
                        for="exampleRole"><fmt:message
                        key="profile.role"/></label>
                <input type="password" class="form-control form-control-sm"
                       id="exampleRole" name="role"
                       placeholder="${user.role}" required disabled>
            </div>
            <div class="form-group">
                <label
                        for="exampleMail"><fmt:message
                        key="profile.email"/></label>
                <input type="email" class="form-control" id="exampleMail"
                       placeholder="${user.email}" name="email" required
                       disabled>
            </div>
            <div class="form-group">
                <label
                        for="examplePhone"><fmt:message
                        key="profile.phone"/></label>
                <input type="tel" pattern="[0-9]{3}-[0-9]{2}-[0-9]{2}"
                       class="form-control" id="examplePhone"
                       placeholder="${user.phone}" name="phone" required
                       disabled>
            </div>
            <div class="form-group">
                <label
                        for="exampleInputPassword1"><fmt:message
                        key="profile.oldpassword"/> </label>
                <input type="password" class="form-control form-control-sm"
                       id="exampleInputPassword1" name="passwordOld"
                       placeholder=
                               "<fmt:message
                               key="profile.placeholder.oldpassword"/>"
                       required minlength="4" maxlength="16">
            </div>
            <div class="form-group">
                <label
                        for="exampleInputPassword2"><fmt:message
                        key="profile.password"/> </label>
                <input type="password" id="password"
                       class="form-control form-control-sm"
                       id="exampleInputPassword2" name="password"
                       placeholder=
                               "<fmt:message key="profile.placeholder.newpassword"/>"
                       required onkeyup='check();' minlength="4" maxlength="16">
            </div>
            <div class="form-group">
                <label
                        for="exampleInputPassword3"><fmt:message
                        key="profile.password.repeat"/></label>
                <input type="password" id="confirm_password"
                       class="form-control form-control-sm"
                       id="exampleInputPassword3" placeholder=
                               "<fmt:message
                                   key="profile.placeholder.newpassword2"/>"
                       required onkeyup='check();' minlength="4" maxlength="16">
                <span id="message"></span>
            </div>
            <button type="submit" class="btn btn-primary" id="submit"
                    disabled=""><fmt:message key="profile.change"/>
            </button>
            <button type="reset" class="btn btn-primary"><fmt:message
                    key="profile.clear"/>
            </button>
        </form>
    </div>
</div>
<hr class="my-5">
<ctgg:footer/>
<script src="<c:url value="/js/main.js"/>"></script>
</body>
</html>