<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 26.09.2019
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:choose>
    <c:when test="${empty locale}">
        <fmt:setLocale value="us_EN"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${locale}"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="resources.localization"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Inconsolata|Playfair+Display|Ubuntu:300&display=swap">
    <link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
          rel="stylesheet"
          integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
          crossorigin="anonymous">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="css/mdb.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <%--<script src="js/jquery-3.4.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/mdb.min.js"></script>
    <script src="js/mdb.js"></script>
--%>
    <title>Rubik</title>
</head>
<body>
<header>
    <nav
            class="navbar navbar-expand-lg navbar-dark fixed-top scrolling-navbar">
        <div class="container">
            <a href="#" class="navbar-brand">Rubiks</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#basicExampleNav"
                    aria-controls="basicExampleNav" aria-expanded="false"
                    aria-label="Toggle Navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="basicExampleNav">
                <ul class="navbar-nav mr-auto smooth-scroll">
                    <li class="nav-item">
                        <a href=#
                           class="nav-link waves-effect waves-light">Intro</a>
                    </li>
                    <li class="nav-item">
                        <a href=""
                           class="nav-link waves-effect waves-light">
                            <fmt:message key="header.contactUs"/></a>
                    </li>
                    <li class="nav-item">
                        <a href=""
                           class="nav-link waves-effect waves-light"><fmt:message
                                key="header.catalog"/></a>
                    </li>
                    <li class="nav-item">
                        <a href=""
                           class="nav-link waves-effect
                           waves-light"><fmt:message key="header.users"/></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#"
                           id="languageDropdown"
                           role="button"ннннннннннннннннннннннннннннннннннн\
                           data-toggle="dropdown" aria-haspopup="true"
                           aria-expanded="false">
                            <fmt:message key="header.locale"/>
                        </a>
                        <div class="dropdown-menu"
                             aria-labelledby="languageDropdown">
                            <a class="dropdown-item" href=""><fmt:message
                                    key="header.language_dropdown.english"/></a>
                            <a class="dropdown-item" href=""><fmt:message
                                    key="header.language_dropdown.russian"/></a>
                        </div>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle"
                           id="navbarDropdownMenuLink-4" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false"><em
                                class="fas fa-user"></em><fmt:message
                                key="header.profile"/></a>
                        <div class="dropdown-menu dropdown-menu-right dropdown-info"
                             aria-labelledby="navbarDropdownMenuLink-4">
                            <a class="dropdown-item" href=""><fmt:message
                                    key="header.account"/></a>
                            <a class="dropdown-item" href=""><fmt:message
                                    key="header.bookmarks"/></a>
                            <a class="dropdown-item" href=""><fmt:message
                                    key="header.logout"/></a>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div id="intro" class="view">
        <div class="mask rgba-black-strong">
            <div class="container-fluid d-flex align-items-center justify-content-center h-100">
                <div class="row d-flex justify-content-center text-center">
                    <div class="col-md-10">
                        <h2 class="display-4 font-weight-bold white-text pt-5 mb-2">
                            Rubiks</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>


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
                <a href="#"><em class="fab fa-facebook-f"></em></a>
                <a href="#"><em class="fab fa-twitter"></em></a>
                <a href="#"><em class="fab fa-instagram"></em></a>
            </div>
        </div>
    </div>
</footer>
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/mdb.min.js"></script>
<script src="js/mdb.js"></script>
</body>
</html>
