<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 07.10.2019
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.get('locale').value}"/>
<fmt:setBundle basename="property/localization"/>
<meta name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Inconsolata|Playfair+Display|Ubuntu:300&display=swap">
<link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
      rel="stylesheet"
      integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
      crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
<link href="<c:url value="/css/mdb.min.css"/>" rel="stylesheet">
<link rel="stylesheet" href="<c:url value="/css/style.css"/>">
<script src="<c:url value="/js/jquery-3.4.1.min.js"/>"></script>
<script src="<c:url value="/js/popper.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.js"/>"></script>
<script src="<c:url value="/js/mdb.min.js"/>"></script>
<script src="<c:url value="/js/mdb.js"/>"></script>
<html>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top scrolling-navbar">
        <div class="container">
            <a href="index.html" class="navbar-brand">Rubiks</a>
            <button class="navbar-toggler" type="button"
                    data-toggle="collapse"
                    data-target="#basicExampleNav"
                    aria-controls="basicExampleNav" aria-expanded="false"
                    aria-label="Toggle Navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="basicExampleNav">
                <ul class="navbar-nav mr-auto smooth-scroll">
                    <li class="nav-item">
                        <a href=#intro
                           class="nav-link waves-effect waves-light">Intro</a>
                    </li>
                    <li class="nav-item">
                        <a href="contact.html"
                           class="nav-link waves-effect waves-light">
                            <fmt:message key="header.contactUs"/></a>
                    </li>
                    <li class="nav-item">
                        <a href="catalog.html"
                           class="nav-link waves-effect waves-light"><fmt:message
                                key="header.catalog"/></a>
                    </li>
                    <c:choose>
                        <c:when test="${user!=null}">
                            <c:if test="${user.role == 'ADMIN'}">
                                <li class="nav-item">
                                    <a href="users.html"
                                       class="nav-link waves-effect
                           waves-light"><fmt:message key="header.users"/></a>
                                </li>
                            </c:if>
                        </c:when>
                    </c:choose>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#"
                           id="languageDropdown"
                           role="button"
                           data-toggle="dropdown" aria-haspopup="true"
                           aria-expanded="false">
                            <fmt:message key="header.locale"/>
                        </a>
                        <div class="dropdown-menu"
                             aria-labelledby="languageDropdown">
                            <a class="dropdown-item"
                               href="lang.html?id=en"><fmt:message
                                    key="header.language_dropdown.english"/></a>
                            <a class="dropdown-item"
                               href="lang.html?id=ru"><fmt:message
                                    key="header.language_dropdown.russian"/></a>
                        </div>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle"
                           id="navbarDropdownMenuLink-4"
                           data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false"><em
                                class="fas fa-user"></em><fmt:message
                                key="header.profile"/></a>
                        <div class="dropdown-menu dropdown-menu-right dropdown-info"
                             aria-labelledby="navbarDropdownMenuLink-4">
                            <c:choose>
                                <c:when test="${sessionScope.get('user')!=null}">
                                    <a class="dropdown-item"
                                       href="profile.html"><fmt:message
                                            key="header.account"/></a>
                                    <a class="dropdown-item"
                                       href=""><fmt:message
                                            key="header.bookmarks"/></a>
                                    <a class="dropdown-item"
                                       href="signout.html"><fmt:message
                                            key="header.logout"/></a>
                                </c:when>
                                <c:otherwise>
                                    <a class="dropdown-item"
                                       href="login.html"><fmt:message
                                            key="header.login"/></a>
                                </c:otherwise>
                            </c:choose>


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
</body>
</html>