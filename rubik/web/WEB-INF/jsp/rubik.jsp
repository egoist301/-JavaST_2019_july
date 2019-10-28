<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 12.10.2019
  Time: 8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${cookie.get('locale').value}"/>
<fmt:setBundle basename="property/localization"/>
<html>
<head>
    <title><fmt:message key="cube"/></title>
</head>

<body>
<jsp:include page="header.jsp"/>
<hr class="my-5">
<div class="container">
    <div>
        <h2 style="text-align: center">${requestScope.get('cube').model}</h2>
    </div>
    <c:if test="${not empty error}">
        <div class="text-center text-warning">
            <label class="text">
                <p><fmt:message key="attention"/></p>
                <fmt:message key="${error}"/>
            </label>
        </div>
    </c:if>
    <div class="card mb-3 div-bg table-responsive" style="max-width: 1150px;">
        <table class="table table-hover table-bordered">
            <div class="row after-header">
                <thead class="send-button-color">
                <tr>
                    <th><fmt:message key="label.image"/></th>
                    <th><fmt:message key="label.info"/></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <div class="view overlay zoom col-md-6">
                            <img class="d-block img-fluid"
                                 src="${cube.paths.get(0)}"
                                 style="width: 300px; height: 300px;"
                                 alt="${cube.model}"/>
                        </div>
                    </td>
                    <td>
                        <ul class="navbar-nav">
                            <li class="nav-item"><fmt:message
                                    key="cube.model"/>: <c:out value="${cube
                                    .model}"/></li>
                            <li class="nav-item"><fmt:message
                                    key="cube.form"/>: <c:out
                                    value="${cube.form}"/></li>
                            <li class="nav-item"><fmt:message
                                    key="cube.size"/>: <c:out
                                    value="${cube.size}"/></li>
                            <li class="nav-item"><fmt:message
                                    key="cube.primaryplastic"/>:
                                <c:out value="${cube.primaryPlastic}"/></li>
                            <li value="nav-item"><fmt:message
                                    key="cube.plasticcolor"/>:
                                <c:out value="${cube.plasticColor}"/></li>
                            <li value="nav-item"><fmt:message
                                    key="cube.manufacturer"/>:
                                <c:out value="${cube.manufacturer}"/></li>
                            <li class="nav-item"><fmt:message
                                    key="cube.weight"/>: <c:out
                                    value="${cube.weight}"/>
                                <fmt:message key="cube.weight.value"/></li>
                            <li class="nav-item"><fmt:message
                                    key="cube.price"/>:
                                <c:out value="${cube.price}"/> <fmt:message
                                        key="cube.price.value"/>
                            </li>
                            <li class="nav-item"><fmt:message
                                    key="cube.date"/>: <c:out
                                    value="${cube.date}"/>
                            </li>
                        </ul>
                    </td>
                </tr>
                </tbody>
            </div>
        </table>
        <label style="padding-left: 50px;padding-right: 20px;">
            <c:out value="${cube.info}"/>
        </label>
        <hr class="my-5">
        <div class="container">
            <div class="row">
                <c:forEach items="${cube.paths}" var="path">
                    <div class="col-md-4 view overlay zoom">
                        <img src="<c:url value='${path}'/>"
                             style="width: 200px; height: 200px;"
                             alt="placeholder"
                             class="img-fluid">
                    </div>
                </c:forEach>
            </div>
        </div>
        <hr class="my-5">
        <c:choose>
            <c:when test="${sessionScope.get('user')!=null &&
            !sessionScope.get('user').blocked}">
                <form action="likecube.html?id=${cube.id}" method="post">
                    <button type="submit" class="btn
                    btn-primary"><fmt:message key="cube.like"/></button>

                </form>
                ${pageContext.session.removeAttribute("error")}
                <c:if test="${sessionScope.get('user').role =='ADMIN' &&
                !sessionScope.get('user').blocked}">
                    <form action="editcube.html" method="get">
                        <input type="hidden" value="${cube.id}" name="id">
                        <button type="submit"
                                class="btn-primary btn"><fmt:message
                                key="cube.edit"/>
                        </button>
                    </form>
                </c:if>
            </c:when>
        </c:choose>
    </div>
</div>
<hr class="my-5">
<ctgg:footer/>
</body>
</html>
