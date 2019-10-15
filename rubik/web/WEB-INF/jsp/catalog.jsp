<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 04.10.2019
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.get('locale').value}"/>
<fmt:setBundle basename="property/localization"/>
<%@ taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title><fmt:message key="header.catalog"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr class="my-5">
<div class="container">
    <div>
        <h2><fmt:message key="header.catalog"/></h2>
    </div>
    <div class="card mb-3 div-bg table-responsive" style="max-width: 1150px;">
        <form action="find1.html" method="get">
            <div class="md-form mt-0">
                Find by form
                <input class="form-control" type="text" name="form"
                       placeholder="Search" aria-label="Search">
            </div>
        </form>
        <form action="find2.html" method="get">
            <div class="md-form mt-0">
                Find by size
                <input class="form-control" type="text"
                       pattern="([0-9]{1,2}x[0-9]{1,2})|([0-9]{1,2}x[0-9]{1,2}x[0-9]{1,2})"
                       name="size" placeholder="Search" aria-label="Search">
            </div>
        </form>
        <table class="table table-hover table-bordered">
            <div class="row after-header">
                <thead class="send-button-color">
                <tr>
                    <th><fmt:message key="label.image"/></th>
                    <th><fmt:message key="label.info"/></th>
                    <c:choose>
                        <c:when test="${sessionScope.get('user')!=null}">
                            <c:if test="${sessionScope.get('user').role ==
                            'ADMIN'}">
                                <th><fmt:message key="label.blocked"/></th>
                                <th><fmt:message key="users.change"/></th>
                            </c:if>
                        </c:when>
                    </c:choose>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${rubiks}" var="cube">
                    <jsp:useBean id="cube"
                                 type="by.training.catalog.bean.RubiksCube"/>
                    <tr>
                        <td>
                            <a href="rubik.html?id=${cube.id}">
                                <img class="small-icon"
                                     src="<c:url
                                     value="${paths.get(cube).get(0)}"
                                      />"
                                     style="width: 100px; height: 100px;"
                                     alt="${cube.model}"/>
                            </a>
                        </td>
                        <td>
                            <a href="rubik.html?id=${cube.id}">
                                <ul class="navbar-nav">
                                    <li class="nav-item">${cube.model}</li>
                                    <li class="nav-item">${cube.form}</li>
                                    <li class="nav-item">${cube.size}</li>
                                    <li class="nav-item">${cube.price}</li>
                                </ul>
                            </a>
                        </td>
                        <c:choose>
                            <c:when test="${sessionScope.get('user')!=null}">
                                <c:if test="${sessionScope.get('user').role ==
                            'ADMIN'}">
                                    <td>${cube.blocked}</td>
                                    <td>
                                        <form
                                                action="blockedcube.html?id=${cube.id}"
                                                method="post">
                                            <button type="submit"
                                                    class="btn btn-primary">
                                                <fmt:message
                                                        key="users.change"/>
                                            </button>
                                        </form>
                                    </td>
                                </c:if>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
            </div>
        </table>
        <c:choose>
            <c:when test="${sessionScope.get('user')!=null}">
                <c:if test="${sessionScope.get('user').role == 'ADMIN'}">
                    <form action="addcube.html" method="get">
                        <button type="submit" class="btn-primary btn">
                            <fmt:message key="cube.add"/></button>
                    </form>
                </c:if>
            </c:when>
        </c:choose>
    </div>
    <ctgg:pagination page="${page}" lastPage="${lastPage}"
                     pageURL="catalog"/>
</div>
<hr class="my-5">
<ctgg:footer/>
</body>
</html>
