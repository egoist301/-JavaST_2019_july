<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 04.10.2019
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<html lang="en">
<head>

    <title><fmt:message key="profile"/></title>
</head>
<body>
<hr class="my-5">
<!-- Form to log in-->
<div class="grid-posts">
    <div class="container div-bg">
        <h2 class="text-center h2-pad-top"><fmt:message key="profile"/></h2>
        <form>
            <div class="form-group">
                <label for="exampleName"><fmt:message
                        key="profile.username"/></label>
                <input type="text" class="form-control" id="exampleName"
                       placeholder="user" required disabled>
            </div>
            <div class="form-group">
                <label
                        for="exampleRole"><fmt:message
                        key="profile.role"/></label>
                <input type="password" class="form-control form-control-sm"
                       id="exampleRole" placeholder="role" required disabled>
            </div>
            <div class="form-group">
                <label
                        for="exampleMail"><fmt:message
                        key="profile.email"/></label>
                <input type="email" class="form-control" id="exampleMail"
                       placeholder="email" required disabled>
            </div>
            <div class="form-group">
                <label
                        for="examplePhone"><fmt:message
                        key="profile.phone"/></label>
                <input type="tel" pattern="[0-9]{3}-[0-9]{2}-[0-9]{2}"
                       class="form-control" id="examplePhone"
                       placeholder="phone" required disabled>
            </div>
            <div class="form-group">
                <label
                        for="exampleInputPassword1"><fmt:message
                        key="profile.oldpassword"/> </label>
                <input type="password" class="form-control form-control-sm"
                       id="exampleInputPassword1" placeholder="Old password"
                       required>
            </div>
            <div class="form-group">
                <label
                        for="exampleInputPassword2"><fmt:message
                        key="profile.password"/> </label>
                <input type="password" id="password"
                       class="form-control form-control-sm"
                       id="exampleInputPassword2" placeholder="New password"
                       required>
            </div>
            <div class="form-group">
                <label
                        for="exampleInputPassword3"><fmt:message
                        key="profile.password.repeat"/></label>
                <input type="password" id="confirm_password"
                       class="form-control form-control-sm"
                       id="exampleInputPassword3" placeholder="New password"
                       required onkeyup='check();'>
                <span id="message"></span>
            </div>
            <button type="submit" class="btn btn-primary" id="submit"
                    disabled="">Change password
            </button>
            <button type="reset" class="btn btn-primary">Clear</button>
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