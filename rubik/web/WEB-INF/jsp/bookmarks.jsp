<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 04.10.2019
  Time: 17:30
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
    <title><fmt:message key="header.bookmarks"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr class="my-5">
<div class="container">
    <div>
        <h2><fmt:message key="header.bookmarks"/></h2>
    </div>
    <div class="card mb-3 div-bg table-responsive" style="max-width: 1150px;">
        <table class="table table-hover table-bordered">
            <div class="row after-header">
                <thead class="send-button-color">
                <tr>
                    <th><fmt:message key="label.image"/></th>
                    <th><fmt:message key="label.info"/></th>
                    <th><fmt:message key="bookmarks.action"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.get('user').cubes}" var="cube">
                    <jsp:useBean id="cube"
                                 type="by.training.catalog.bean.RubiksCube"/>
                    <tr>
                        <td>
                            <a href="rubik.html?id=${cube.id}">
                                <img class="small-icon"
                                     src="<c:url
                                     value="${cube.paths.get(0)}"
                                      />"
                                     style="width: 100px; height: 100px;"
                                     alt="${cube.model}"/>
                            </a>
                        </td>
                        <td>
                            <a href="rubik.html?id=${cube.id}">
                                <ul class="navbar-nav">
                                    <li class="nav-item"><c:out value="${cube
                                    .model}"/></li>
                                    <li class="nav-item"><c:out
                                            value="${cube.form}"/></li>
                                    <li class="nav-item"><c:out value="${cube
                                    .size}"/></li>
                                    <li class="nav-item"><c:out value="${cube
                                    .price}"/></li>
                                </ul>
                            </a>
                        </td>
                        <td>
                            <form
                                    action="removeCube.html?id=${cube.id}"
                                    method="post">
                                <button type="submit"
                                        class="btn btn-danger">
                                    <fmt:message
                                            key="bookmarks.remove"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </div>
        </table>
    </div>
    <ctgg:pagination page="${page}" lastPage="${lastPage}"
                     pageURL="bookmarks.html"/>
</div>
<hr class="my-5">
<ctgg:footer/>
</body>
</html>