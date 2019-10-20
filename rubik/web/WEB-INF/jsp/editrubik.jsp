<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 15.10.2019
  Time: 2:38
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
        <form action="editc.html" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="exampleModel"><fmt:message
                        key="cube.model"/></label>
                <input type="text" class="form-control" id="exampleModel"
                       required name="model" value="${cube.model}">
            </div>
            <div class="form-group">
                <label for="examplePrice"><fmt:message
                        key="cube.price"/></label>
                <input type="number" class="form-control" id="examplePrice"
                       min="1" max="2000" value="${cube.price}"
                       required name="price">
            </div>
            <div class="form-group">
                <label for="exampleWeight"><fmt:message
                        key="cube.weight"/></label>
                <input type="number" class="form-control" id="exampleWeight"
                       min="1" max="2000" value="${cube.weight}"
                       required name="weight">
            </div>
            <div class="form-group">
                <label for="exampleInfo"><fmt:message
                        key="cube.info"/></label>
                <textarea id="exampleInfo" type="text"
                          class="md-textaria form-control validate form-control-sm"
                          rows="3" required name="info">${cube.info}</textarea>
            </div>
            <div class="form-group">
                <label for="examplePrimaryPlastic"><fmt:message
                        key="cube.primaryplastic"/></label>
                <input type="checkbox" class="form-control"
                       id="examplePrimaryPlastic"
                       value="${cube.primaryPlastic}" name="primaryPlastic">
            </div>
            <div class="form-group">
                <label for="exampleSize"><fmt:message
                        key="cube.size"/></label>
                <input type="text" class="form-control" id="exampleSize"
                       required value="${cube.size}"
                       pattern="([0-9]{1,2}x[0-9]{1,2})|([0-9]{1,2}x[0-9]{1,2}x[0-9]{1,2})"
                       name="size">
            </div>
            <div class="form-group">
                <label for="examplePlasticColor"><fmt:message
                        key="cube.plasticcolor"/></label>
                <select class="custom-select mb-1" name="plasticcolor"
                        id="examplePlasticColor">
                    <c:forEach items="${requestScope.get('colors')}"
                               var="color">
                        <option value="${color}" <c:if
                                test="${color.equals(cube.plasticColor)}">
                            selected</c:if>>${color}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="exampleManufacturer"><fmt:message
                        key="cube.manufacturer"/></label>
                <select class="custom-select mb-1" name="manufacturer"
                        id="exampleManufacturer">
                    <c:forEach items="${requestScope.get('manufacturers')}"
                               var="manufact">
                        <option value="${manufact}" <c:if
                                test="${manufact.equals(cube.manufacturer)}">
                            selected
                        </c:if>>${manufact}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="exampleForm"><fmt:message
                        key="cube.form"/></label>
                <select class="custom-select mb-1" name="forma"
                        id="exampleForm">
                    <c:forEach items="${requestScope.get('forms')}" var="form">
                        <option value="${form}"
                                <c:if test="${form.equals(cube.form)}"> selected</c:if>>
                                ${form}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <input type="file" multiple name="img" class="custom-file">
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
