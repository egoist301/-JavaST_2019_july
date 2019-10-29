<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11.10.2019
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.get('locale').value}"/>
<fmt:setBundle basename="property/localization"/>
<%@taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title><fmt:message key="header.users"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr class="my-5">
<div class="container">
    <div>
        <h2><fmt:message key="header.users"/></h2>
    </div>
    <div class="card mb-3 div-bg table-responsive" style="max-width: 1150px;">
        <form action="findUsername.html" method="get">
            <div class="md-form mt-0">
                Find by username
                <input class="form-control" type="text" name="username"
                       placeholder="<fmt:message key="search.username"/>"
                       aria-label="Search" maxlength="16">
            </div>
        </form>
        <table class="table table-hover table-bordered">
            <div class="row after-header">
                <thead class="send-button-color">
                <tr>
                    <th><fmt:message key="label.id"/></th>
                    <th><fmt:message key="label.username"/></th>
                    <th><fmt:message key="label.role"/></th>
                    <th><fmt:message key="label.phone"/></th>
                    <th><fmt:message key="label.email"/></th>
                    <th><fmt:message key="label.blocked"/></th>
                    <th><fmt:message key="users.change"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <jsp:useBean id="user"
                                 type="by.training.catalog.bean.User"/>
                    <tr>
                        <td>
                            <label><c:out value="${user.id}"/></label>
                        </td>
                        <td>
                            <label><c:out value="${user.username}"/></label>
                        </td>
                        <td>
                            <label><c:out value="${user.role}"/></label>
                        </td>
                        <td>
                            <label><a href="tel:${user.phone}"><c:out value="
                                    ${user.phone}"/></a>
                            </label>
                        </td>
                        <td>
                            <label><a
                                    href="mailto:${user.email}"><c:out value=
                                    "${user.email}"/></a>
                            </label>
                        </td>
                        <td>
                            <label>
                                    <c:out value="${user.blocked}"/>
                            </label>
                        </td>
                        <td>
                            <c:if test="${!user.blocked}">
                                <form action="blocked.html?id=${user.id}"
                                      method="post">
                                    <button type="submit"
                                            class="btn btn-danger"><fmt:message
                                            key="users.change"/></button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </div>
        </table>
    </div>
    <ctgg:pagination page="${page}" lastPage="${lastPage}"
                     pageURL="users.html"/>
</div>
<hr class="my-5">
<ctgg:footer/>
</body>
</html>
