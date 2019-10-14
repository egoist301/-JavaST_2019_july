<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 07.10.2019
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.get('locale').value}"/>
<fmt:setBundle basename="property/localization"/>
<%@ taglib prefix="ctgg" tagdir="/WEB-INF/tags" %>
<head>
    <title><fmt:message key="contact"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr class="my-5">
<div class="container" id="contact">
    <h2 class="mb-5 font-weight-bold text-center"><fmt:message
            key="contact"/></h2>
    <div class="row">
        <div class="col-lg-5 col-md-12">
            <form class="p-5 needs-validation">
                <div class="md-form form-sm">
                    <em class="fa fa-user prefix grey-text"></em>
                    <input id="form3" type="text"
                           class="validate form-control form-control-sm"
                           required maxlength="10">
                    <label for="form3"><fmt:message
                            key="contact.name"/> </label>
                </div>
                <div class="md-form form-sm">
                    <em class="fa fa-envelope prefix grey-text"></em>
                    <input type="email" id="inputValidationEx67 form2"
                           class="form-control form-control-sm validate"
                           required data-msg-containerid="emailError">
                    <label for="inputValidationEx67 form2" data-error="wrong"
                           data-success="right"><fmt:message
                            key="contact.email"/> </label>
                </div>
                <div class="md-form form-sm">
                    <em class="fa fa-tag prefix grey-text"></em>
                    <input id="form23"
                           class="form-control form-control-sm validate"
                           type="text">
                    <label for="form23"><fmt:message key="contact.subject"/>
                    </label>
                </div>
                <div class="md-form form-sm">
                    <em class="fa fa-pen prefix grey-text"></em>
                    <textarea id="form32" type="text"
                              class="md-textaria form-control validate form-control-sm"
                              rows="3" required></textarea>
                    <label for="form32"><fmt:message
                            key="contact.message"/></label>
                </div>
                <div class="text-center mt-4">
                    <button class="btn btn-primary waves-effect waves-light"
                            id="validate"><fmt:message key="contact.send"/> <em
                            class="far fa-paper-plane ml-1"></em></button>
                </div>
            </form>
        </div>
        <div class="col-lg-7 col-md-12">
            <div class="row text-center">
                <div class="col-lg-4 col-md-4 mb-3">
                    <p><em class="fa fa-map fa-1x mr-2
                    grey-text"></em><fmt:message key="contact.city"/></p>
                </div>
                <div class="col-lg-4 col-md-4 mb-3">
                    <p><em class="fa fa-building fa-1x mr-2
                    grey-text"></em><fmt:message key="contact.date"/></p>
                </div>
                <div class="col-lg-4 col-md-4 mb-3">
                    <p><em
                            class="fa fa-phone fa-1x mr-2 grey-text"></em><a
                            href="tel:+375333221114">
                        +375(33)32-21-114</a>
                    </p>
                </div>
            </div>
            <div id="map-container-google-1"
                 class="z-depth-1-half map-container" style="height: 400px">
                <iframe src="https://maps.google.com/maps?q=minsk&t=&z=10&ie=UTF8&iwloc=&output=embed"
                        frameborder="0" style="border:0"
                        allowfullscreen></iframe>
            </div>
        </div>
    </div>
</div>
<hr class="my-5">
<ctgg:footer/>
</body>
</html>
