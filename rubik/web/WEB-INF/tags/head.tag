<%@tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" rtexprvalue="true"
              type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <c:url value="/css" var="cssUrl"/>
    <link rel="stylesheet" type="text/css" href="${cssUrl}/mdb.min.css">
    <link rel="stylesheet" href="${cssUrl}/bootstrap.min.css">
    <link rel="stylesheet" href="${cssUrl}/style.css">

    <title>${title}</title>
</head>