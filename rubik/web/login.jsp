<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<%@taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>
<html lang="en">
<head>
    <title>Login</title>
</head>
<body>
<hr class="my-5">
<!-- Form to log in-->
<div class="grid-posts">
    <div class="container div-bg">
        <h2 class="text-center h2-pad-top"><fmt:message
                key="login"/></h2>
        <form action="signin.html" method="post">
            <div class="form-group">
                <label for="exampleName"><fmt:message
                        key="login.username"/></label>
                <input type="text" class="form-control form-control-sm"
                       id="exampleName" aria-describedby="nameHelp"
                       placeholder=
                       <fmt:message key="login.placeholder.username"/> required
                       maxlength="16" name="login">
            </div>
            <div class="form-group">
                <label
                        for="exampleInputPassword1"><fmt:message
                        key="login.password"/></label>
                <input type="password" class="form-control form-control-sm"
                       id="exampleInputPassword1"
                       placeholder=
                       <fmt:message
                               key="login.placeholder.password"/>
                               required maxlength="16" name="password">
            </div>
            <!--<div class="g-recaptcha"
                 data-sitekey="6KepjAsTFFFFFFMqccY0ZiGqc3TEd3YVxo8cHsGX
                 "></div>-->
            <button type="submit"
                    class="btn btn-primary"><fmt:message
                    key="login"/></button>
            <a href="registration.html" class="btn-link
            waves-effect"><fmt:message
                    key="login.registration"/></a>

        </form>
    </div>
</div>
<hr class="my-5">

<!-- Footer -->
<ctgg:footer/>
</body>
</html>