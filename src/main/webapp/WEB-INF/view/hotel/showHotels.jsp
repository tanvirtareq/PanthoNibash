<%@ page contentType="text/html;charset=UTF-8"   %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="page.title" /></title>
    <link rel="stylesheet" href="/assets/css/customStyle.css">
</head>
<body>

<header class="custom-header">
    <h1><spring:message code="header.title" /></h1>
</header>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="custom-card">
                <div class="card-body" style="height: 700px; overflow-y: auto;">
                    <c:if test="${not empty hotels}">
                        <c:forEach items="${hotels}" var="hotel">
                            <div class="custom-card mb-3" style="max-width: 840px;">
                                <div class="row g-0">
                                    <div class="col-md-6">
                                        <a href="/hotel/${hotel.id}">
                                            <img
                                                    src="data:image/jpeg;base64,${hotel.hotelImageBase64Image}"
                                                    alt="<c:out value="${hotel.name}"/>"
                                                    height="250px"
                                                    width="400px"
                                                    class=" rounded-start img-search-card"
                                            />
                                        </a>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="card-body">
                                            <h5 class="card-title"><spring:message code="hotel.name" arguments="${hotel.name}" /></h5>
                                            <p class="card-text">
                                                <spring:message code="hotel.location" arguments="${hotel.location}" /><br/>
                                                <spring:message code="hotel.parking" arguments="${hotel.parkingFacility}" /><br/>
                                                <spring:message code="hotel.wifi" arguments="${hotel.wifiFacility}" /><br/>
                                                <spring:message code="hotel.pool" arguments="${hotel.swimmingPool}" /><br/>
                                                <spring:message code="hotel.fitness" arguments="${hotel.fitnessCentre}" /><br/>
                                                <c:choose>
                                                    <c:when test="${hotel.rating == null}">
                                                        <spring:message code="hotel.noRating" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <spring:message code="hotel.rating" arguments="${hotel.rating.rating}" />
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty hotels}">
                        <p><spring:message code="hotel.noHotelsFound" /></p>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
