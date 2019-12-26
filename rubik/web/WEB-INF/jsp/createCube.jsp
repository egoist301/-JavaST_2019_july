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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:if test="${not empty error}">
            <div class="text-center text-warning">
                <label class="text">
                    <p><fmt:message key="attention"/></p>
                    <fmt:message key="${error}"/>
                </label>
            </div>
        </c:if>
        <form action="createCube.html" method="post"
              enctype="multipart/form-data">
            <div class="form-group">
                <label for="exampleModel"><fmt:message
                        key="cube.model"/></label>
                <input type="text" class="form-control" id="exampleModel"
                       placeholder="<fmt:message
                               key="createcube.placeholder.model"/>"
                       required name="model" maxlength="30">
            </div>
            <div class="form-group">
                <label for="examplePrice"><fmt:message
                        key="cube.price"/></label>
                <input type="text" class="form-control" id="examplePrice"
                       placeholder="<fmt:message
                               key="createcube.placeholder.price"/>"
                       pattern="^[1-9]{1}[\d]{0,3}\.[\d]{1,2}$"
                       required name="price" maxlength="7">
            </div>
            <div class="form-group">
                <label for="exampleWeight"><fmt:message
                        key="cube.weight"/></label>
                <input type="text" class="form-control" id="exampleWeight"
                       placeholder="<fmt:message
                               key="createcube.placeholder.weight"/>"
                       pattern="^[1-9]{1}[\d]{0,3}\.[\d]{1,2}$"
                       required name="weight" maxlength="7">
            </div>
            <div class="form-group">
                <label for="exampleInfo"><fmt:message
                        key="cube.info"/></label>
                <textarea id="exampleInfo" type="text"
                          class="md-textaria form-control validate form-control-sm"
                          rows="3" required name="info"></textarea>
            </div>
            <div class="form-group left">
                <label for="examplePrimaryPlastic"><fmt:message
                        key="cube.primaryplastic"/></label>
                <input type="checkbox" class="form-control"
                       id="examplePrimaryPlastic" name="primaryPlastic">
            </div>
            <div class="form-group">
                <label for="exampleSize"><fmt:message
                        key="cube.size"/></label>
                <input type="text" class="form-control" id="exampleSize"
                       placeholder="<fmt:message
                               key="creactecube.placeholder.size"/>"
                       required
                       pattern="([0-9]{1,2}x[0-9]{1,2})|([0-9]{1,2}x[0-9]{1,2}x[0-9]{1,2})"
                       name="size" maxlength="8">
            </div>
            <div class="form-group">
                <label for="examplePlasticColor"><fmt:message
                        key="cube.plasticcolor"/></label>
                <select class="custom-select mb-1" name="plasticColor"
                        id="examplePlasticColor">
                    <c:forEach items="${requestScope.get('colors')}"
                               var="color">
                        <option value="${color}"><c:out value="${color}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="exampleManufacturer"><fmt:message
                        key="cube.manufacturer"/></label>
                <select class="custom-select mb-1" name="manufacturer"
                        id="exampleManufacturer">
                    <c:forEach items="${requestScope.get('manufacturers')}"
                               var="manufacturer">
                        <option value="${manufacturer}">
                            <c:out value="${manufacturer}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="exampleForm"><fmt:message
                        key="cube.form"/></label>
                <select class="custom-select mb-1" name="form"
                        id="exampleForm">
                    <c:forEach items="${requestScope.get('forms')}" var="form">
                        <option value="${form}">
                            <c:out value="${form}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <input formenctype="multipart/form-data" type="file" multiple
                       name="img" class="custom-file" required>
            </div>
            <button type="submit" class="btn btn-primary" id="submit">
                <fmt:message key="cube.add"/>
            </button>
            ${pageContext.session.removeAttribute("error")}
        </form>
    </div>
</div>
<hr class="my-5">
<ctgg:footer/>
</body>
</html>
