<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="page" rtexprvalue="true" type="java.lang.Integer"
              required="true" %>
<%@ attribute name="lastPage" rtexprvalue="true" type="java.lang.Integer"
              required="true" %>
<%@ attribute name="pageURL" rtexprvalue="true" type="java.lang.String"
              required="true" %>
<fmt:setLocale value="${cookie.get('locale').value}"/>
<fmt:setBundle basename="property/localization"/>
<c:choose>
    <c:when test="${page == 1}">
        <c:set var="prevPage" value="1"/>
    </c:when>
    <c:otherwise>
        <c:set var="prevPage" value="${page - 1}"/>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${page < lastPage}">
        <c:set var="nextPage" value="${page + 1}"/>
    </c:when>
    <c:otherwise>
        <c:set var="nextPage" value="${page}"/>
    </c:otherwise>
</c:choose>

<div class="row">
    <div class="col-sm-12 mx-auto">
        <ul class="pagination" style="justify-content: center">
            <li class="page-item">
                <a class="page-link"
                   href="${pageURL}<c:choose>
<c:when test='${pageURL.contains("?")}'>&
</c:when><c:otherwise>?</c:otherwise></c:choose>page=1">
                    <fmt:message key="pagination.first"/></a>
            </li>
            <li class="page-item">
                <a class="page-link"
                   href="${pageURL}<c:choose><c:when test='${pageURL.contains("?")}'>&
</c:when><c:otherwise>?</c:otherwise></c:choose>page=${prevPage}">
                    <fmt:message key="pagination.previous"/> </a>
            </li>
            <span class="text-primary"
                  style="margin: 6px 10px">${page} of ${lastPage}</span>
            <li class="page-item">
                <a class="page-link"
                   href="${pageURL}<c:choose><c:when test='${pageURL.contains("?")}'>&
</c:when><c:otherwise>?</c:otherwise></c:choose>page=${nextPage}">
                    <fmt:message key="pagination.next"/> </a>
            </li>
            <li class="page-item">
                <a class="page-link"
                   href="${pageURL}<c:choose><c:when test='${pageURL.contains("?")}'>&
</c:when><c:otherwise>?</c:otherwise></c:choose>page=${lastPage}">
                    <fmt:message key="pagination.last"/> </a>
            </li>
        </ul>
    </div>
</div>