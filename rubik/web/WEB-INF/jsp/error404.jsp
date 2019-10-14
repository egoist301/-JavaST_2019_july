<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 13.10.2019
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.get('locale').value}"/>
<fmt:setBundle basename="property/localization"/>
<%@ taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>
<html>
<head>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="row after-header">
    <div class="col-5 mx-auto">
        <h3>Page not found</h3>
    </div>
</div>
</body>
</html>
