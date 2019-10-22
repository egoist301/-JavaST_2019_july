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
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <form action="findform.html" method="get">
                        <fmt:message key="search.form"/>
                        <input class="form-control" type="text" name="form"
                               placeholder="<fmt:message key="search.form"/>"
                               aria-label="Search" maxlength="20">
                    </form>
                </div>
                <div class="col-md-3">
                    <form action="findsize.html" method="get">
                        <fmt:message key="search.size"/>
                        <input class="form-control" type="text"
                               pattern="([0-9]{1,2}x[0-9]{1,2})|([0-9]{1,2}x[0-9]{1,2}x[0-9]{1,2})"
                               name="size" placeholder="<fmt:message
                       key="search.size"/>" maxlength="8" aria-label="Search">

                    </form>
                </div>
                <div class="col-md-3">
                    <form action="findmodel.html" method="get">
                        <fmt:message key="search.model"/>
                        <input class="form-control" type="text"
                               name="model" placeholder="<fmt:message
                       key="search.model"/>" aria-label="Search" maxlength="30">

                    </form>
                </div>
                <div class="col-md-3">
                    <form action="findprice.html" method="get">
                        <fmt:message key="search.price"/>
                        <input class="form-control" type="text"
                               name="minprice"
                               onkeypress='return event.charCode >= 48 &&
                               event.charCode <= 57'
                               placeholder="<fmt:message
                       key="search.price.min"/>" required aria-label="Search"
                               min="1" max="2000">
                        <input class="form-control" required type="text"
                               name="minprice" placeholder="<fmt:message
                       key="search.price.max"/>" aria-label="Search" min="1"
                               max="2000" onkeypress='return event.charCode
                               >= 48 && event.charCode <= 57'>
                    </form>
                </div>
            </div>
        </div>
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
                                <th><fmt:message key="cube.change"/></th>
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
                            <div class="container col-md-6">
                                <a href="rubik.html?id=${cube.id}">
                                    <img class="small-icon"
                                         src="<c:url
                                     value="${cube.paths.get(0)}"
                                      />"
                                         style="width: 100px; height: 100px;"
                                         alt="${cube.model}"/>
                                </a>
                            </div>
                        </td>
                        <td>
                            <a href="rubik.html?id=${cube.id}">
                                <ul class="navbar-nav">
                                    <li class="nav-item"><fmt:message
                                            key="cube.model"/>: ${cube.model}
                                    </li>
                                    <li
                                            class="nav-item"><fmt:message
                                            key="cube.form"/>: ${cube.form}</li>
                                    <li
                                            class="nav-item"><fmt:message
                                            key="cube.size"/>: ${cube.size}</li>
                                    <li
                                            class="nav-item"><fmt:message
                                            key="cube.price"/>:
                                            ${cube.price}
                                        <fmt:message
                                                key="cube.price.value"/>
                                    </li>
                                    <li
                                            class="nav-item"><fmt:message
                                            key="cube.date"/>: ${cube.date}
                                    </li>
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
                                                        key="cube.change"/>
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
