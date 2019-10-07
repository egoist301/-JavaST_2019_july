<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 04.10.2019
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<html lang="en">
<head>
    <title><fmt:message key="registration"/></title>
</head>
<body>
<hr class="my-5">
<!-- Form to log in-->
<div class="grid-posts">
    <div class="container div-bg">
        <h2 class="text-center h2-pad-top"><fmt:message key="registration"/>
        </h2>
        <form>
            <div class="form-group">
                <label for="exampleName"><fmt:message
                        key="registration.username"/></label>
                <input type="text" class="form-control" id="exampleName"
                       placeholder=
                       <fmt:message
                               key="registration.placeholder.username"/>
                               required
                       disabled>
            </div>
            <div class="form-group">
                <label for="exampleMail"><fmt:message
                        key="registration.email"/></label>
                <input type="email" class="form-control" id="exampleMail"
                       placeholder=
                       <fmt:message
                               key="registration.placeholder.email"/> required
                       disabled>
            </div>
            <div class="form-group">
                <label for="examplePhone"><fmt:message
                        key="registration.phone"/></label>
                <input type="tel" pattern="[0-9]{3}-[0-9]{2}-[0-9]{2}"
                       class="form-control" id="examplePhone"
                       placeholder=
                           <fmt:message
                                   key="registration.placeholder.phone"/> required
                       disabled>
            </div>
            <div class="form-group">
                <label
                        for="exampleInputPassword1"><fmt:message
                        key="registration.password"/> </label>
                <input type="password" class="form-control form-control-sm"
                       id="exampleInputPassword1" placeholder=
                           <fmt:message
                                   key="registration.placeholder.password"/>
                               required>
            </div>
            <button type="submit" class="btn btn-primary" id="submit"
                    disabled=""><fmt:message key="registration"/>
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
<script src="js/main.js"></script>
</body>
</html>