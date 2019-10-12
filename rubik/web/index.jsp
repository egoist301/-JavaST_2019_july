<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 26.09.2019
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.get('locale').value}"/>
<fmt:setBundle basename="property/localization"/>
<html>
<head>
    <title>Rubik</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr class="my-5">
<ctgg:footer/>
</body>
</html>