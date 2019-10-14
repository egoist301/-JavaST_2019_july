<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 13.10.2019
  Time: 1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>
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
<div class="grid-posts">
    <div class="container div-bg">
        <h2 class="text-center h2-pad-top"><fmt:message key="cube"/>
        </h2>
        <form action="createcube.html" method="post">
            <div class="form-group">
                <label for="exampleModel"><fmt:message
                        key="cube.model"/></label>
                <input type="text" class="form-control" id="exampleModel"
                       placeholder=
                               "<fmt:message
                               key="registration.placeholder.username"/>"
                       required name="model">
            </div>
            <div class="form-group">
                <label for="examplePrice"><fmt:message
                        key="cube.price"/></label>
                <input type="number" class="form-control" id="examplePrice"
                       placeholder=
                       <fmt:message
                               key="registration.placeholder.username"/>
                               required name="price">
            </div>
            <div class="form-group">
                <label for="exampleWeight"><fmt:message
                        key="cube.weight"/></label>
                <input type="number" class="form-control" id="exampleWeight"
                       placeholder=
                       <fmt:message
                               key="registration.placeholder.username"/>
                               required name="weight">
            </div>
            <div class="form-group">
                <label for="exampleInfo"><fmt:message
                        key="cube.info"/></label>
                <textarea id="exampleInfo" type="text"
                          class="md-textaria form-control validate form-control-sm"
                          rows="3" required name="info"></textarea>
            </div>
            <div class="form-group">
                <label for="examplePrimaryPlastic"><fmt:message
                        key="cube.primaryplastic"/></label>
                <input type="checkbox" class="form-control"
                       id="examplePrimaryPlastic"
                       placeholder=
                       <fmt:message
                               key="registration.placeholder.username"/>
                               required name="primaryPlastic">
            </div>
            <div class="form-group">
                <label for="exampleSize"><fmt:message
                        key="cube.size"/></label>
                <input type="text" class="form-control" id="exampleSize"
                       placeholder=
                       <fmt:message
                               key="registration.placeholder.username"/>
                               required pattern="(%d{1}x%d{1})|(%d{2}x%d{2})"
                       name="size"> <!--REGEX-->
            </div>
            <div class="form-group">
                <label for="examplePlasticColor"><fmt:message
                        key="cube.plasticcolor"/></label>
                <input type="text" class="form-control" id="examplePlasticColor"
                       placeholder=
                       <fmt:message
                               key="registration.placeholder.username"/>
                               required name="plasticColor">
            </div>
            <div class="form-group">
                <label for="exampleManufacturer"><fmt:message
                        key="cube.manufacturer"/></label>
                <input type="text" class="form-control" id="exampleManufacturer"
                       placeholder=
                       <fmt:message
                               key="registration.placeholder.username"/>
                               required name="manufacturer">
            </div>
            <div class="form-group">
                <label for="exampleForm"><fmt:message
                        key="cube.form"/></label>
                <input type="text" class="form-control" id="exampleForm"
                       placeholder=
                       <fmt:message
                               key="registration.placeholder.username"/>
                               required name="form">
            </div>
            <button type="submit" class="btn btn-primary" id="submit">
                <fmt:message key="registration"/>
            </button>
        </form>
    </div>
</div>
<hr class="my-5">
<ctgg:footer/>
</body>
</html>
