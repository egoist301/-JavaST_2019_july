<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
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
        <form>
            <div class="form-group">
                <label for="exampleName"><fmt:message
                        key="login.username"/></label>
                <input type="text" class="form-control form-control-sm"
                       id="exampleName" aria-describedby="nameHelp"
                       placeholder=
                       <fmt:message key="login.placeholder.username"/> required
                       maxlength="16">
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
                               required maxlength="16">
            </div>
            <!--<div class="g-recaptcha"
                 data-sitekey="6KepjAsTFFFFFFMqccY0ZiGqc3TEd3YVxo8cHsGX
                 "></div>-->
            <button type="submit"
                    class="btn btn-primary"><fmt:message
                    key="login"/></button>
            <button type="submit" class="btn btn-primary"><fmt:message
                    key="login.registration"/>
            </button>
        </form>
    </div>
</div>
<hr class="my-5">

<!-- Footer -->
<footer>
    <div class="container">
        <div class="d-flex justify-content-between">
            <div class="footer-left">
                <a href="#">Terms and conditions</a>
                <a href="#">Privacy</a>
                <p class="pull-left">Copyright © 2019</p>
            </div>
            <div class="footer-right">
                <span>Follow us:</span>
                <a href="#"><em class="fab fa-facebook-f"></em></a>
                <a href="#"><em class="fab fa-twitter"></em></a>
                <a href="#"><em class="fab fa-instagram"></em></a>
            </div>
        </div>
    </div>
</footer>
</body>
</html>