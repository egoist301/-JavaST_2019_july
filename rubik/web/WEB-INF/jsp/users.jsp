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

<hr class="my-5">
<ctgg:footer/>
</body>
</html>
