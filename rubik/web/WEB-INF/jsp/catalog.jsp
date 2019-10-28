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
                <div class="col-md-4">
                    <form action="findform.html" method="get">
                        <label for="exampleForm"><fmt:message
                                key="search.form"/></label>
                        <select class="custom-select mb-1" name="form"
                                id="exampleForm" required>
                            <option value="" disabled selected><fmt:message
                                    key="search.form.option"/>
                            </option>
                            <c:forEach items="${requestScope.get('forms')}"
                                       var="form">
                                <option value="${form}">${form}</option>
                            </c:forEach>
                        </select>
                        <button type="submit"
                                class="btn-primary btn"><fmt:message
                                key="search"/>
                        </button>
                    </form>
                </div>
                <div class="col-md-4">
                    <form action="findmanufacturer.html" method="get">
                        <label for="exampleManufacturer"><fmt:message
                                key="search.manufacturer"/></label>
                        <select class="custom-select mb-1" name="manufacturer"
                                id="exampleManufacturer" required>
                            <option value="" disabled selected>
                                <fmt:message key="search.manufacturer.option"/>
                            </option>
                            <c:forEach
                                    items="${requestScope.get('manufacturer')}"
                                    var="manufacturer">
                                <option value="${manufacturer}">
                                    <c:out value="${manufacturer}"/></option>
                            </c:forEach>
                        </select>
                        <button type="submit"
                                class="btn-primary btn"><fmt:message
                                key="search"/>
                        </button>
                    </form>
                </div>
                <div class="col-md-4">
                    <form action="findprice.html" method="get">
                        <fmt:message key="search.price"/>
                        <input class="form-control" type="search"
                               name="minprice" value="1.00" maxlength="7"
                               placeholder="<fmt:message
                       key="search.price.min"/>" required aria-label="Search"
                               pattern="^[1-9]{1}[\d]{0,3}\.[\d]{1,2}$">
                        <input class="form-control" required type="search"
                               name="maxprice" placeholder="<fmt:message
                       key="search.price.max"/>" aria-label="Search"
                               pattern="^[1-9]{1}[\d]{0,3}\.[\d]{1,2}$"
                               value="2000.00" maxlength="7">
                        <button type="submit"
                                class="btn-primary btn"><fmt:message
                                key="search"/>
                        </button>
                    </form>
                </div>
                <div class="col-md-4">
                    <form action="findsize.html" method="get">
                        <fmt:message key="search.size"/>
                        <input class="form-control" type="search"
                               pattern="([0-9]{1,2}x[0-9]{1,2})|([0-9]{1,2}x[0-9]{1,2}x[0-9]{1,2})"
                               name="size" placeholder="<fmt:message
                       key="search.size"/>" maxlength="8" aria-label="Search">
                    </form>
                </div>
                <div class="col-md-4">
                    <form action="findmodel.html" method="get">
                        <fmt:message key="search.model"/>
                        <input class="form-control" type="search"
                               name="model" placeholder="<fmt:message
                       key="search.model"/>" aria-label="Search" maxlength="30">
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
                                            key="cube.model"/>:
                                        <c:out value=" ${cube.model}"/>
                                    </li>
                                    <li class="nav-item">
                                        <fmt:message key="cube.manufacturer"/>:
                                        <c:out value=" ${cube.manufacturer}"/>
                                    </li>
                                    <li
                                            class="nav-item"><fmt:message
                                            key="cube.form"/>:
                                        <c:out value="${cube.form}"/></li>
                                    <li
                                            class="nav-item"><fmt:message
                                            key="cube.size"/>:
                                        <c:out value=" ${cube.size}"/></li>
                                    <li
                                            class="nav-item"><fmt:message
                                            key="cube.price"/>:
                                        <c:out value=" ${cube.price}"/>
                                        <fmt:message
                                                key="cube.price.value"/>
                                    </li>
                                    <li
                                            class="nav-item"><fmt:message
                                            key="cube.date"/>:
                                        <c:out value=" ${cube.date}"/>
                                    </li>
                                </ul>
                            </a>
                        </td>
                        <c:choose>
                            <c:when test="${sessionScope.get('user')!=null}">
                                <c:if test="${sessionScope.get('user').role ==
                            'ADMIN'}">
                                    <td><c:out value="${cube.blocked}"/></td>
                                    <td>
                                        <c:if test="${!cube.blocked}">
                                            <form action="blockedcube.html?id=${cube.id}"
                                                  method="post">
                                                <button type="submit"
                                                        class="btn btn-danger">
                                                    <fmt:message
                                                            key="cube.change"/>
                                                </button>
                                            </form>
                                        </c:if>
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
                     pageURL="${query}"/>
</div>
<hr class="my-5">
<ctgg:footer/>
</body>
</html>