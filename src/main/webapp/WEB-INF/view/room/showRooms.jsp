<%@ page contentType="text/html;charset=UTF-8"   %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="roomsearch.page.title"/></title>
    <link rel="stylesheet" href="/assets/css/customStyle.css">
</head>
<body>
<header class="custom-header">
    <h1><spring:message code="roomsearch.page.heading"/></h1>
</header>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="custom-card">
                <div class="card-body" style="height: 650px; overflow-y: auto;">
                    <c:if test="${not empty rooms}">
                        <c:forEach items="${rooms}" var="room" varStatus="loopStatus">
                            <div class="custom-card mb-3" style="max-width: 840px;">
                                <div class="row g-0">
                                    <div class="col-md-6">
                                        <a href="/room/${room.id}">
                                            <img
                                                    src="data:image/jpeg;base64,${room.roomImageBase64Image}"
                                                    alt="${room.hotel.name}"
                                                    height="250px"
                                                    width="400px"
                                                    class=" rounded-start img-search-card"
                                            />
                                        </a>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="card-body">
                                            <h5 class="card-title">
                                                <spring:message code="roomsearch.page.hotel"/>
                                                : <c:out value="${room.hotel.name}"/>
                                            </h5>
                                            <p class="card-text">
                                                <c:choose>
                                                    <c:when test="${room.hotel.rating == null}">
                                                        <span class="hotel-rating">
                                                            <spring:message code="roomsearch.page.no.rating"/>
                                                        </span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="hotel-rating">
                                                            <spring:message code="roomsearch.page.rating"/>
                                                            :
                                                            <fmt:formatNumber maxFractionDigits="1"
                                                                              value="${room.hotel.rating.rating}"/>
                                                            <spring:message code="roomsearch.page.rating.outof"/>
                                                        </span>
                                                    </c:otherwise>
                                                </c:choose>
                                                <br/>
                                                <spring:message code="roomsearch.page.room.type"/>
                                                : <c:out value="${room.type}"/>
                                                <br/>
                                                <spring:message code="roomsearch.page.location"/>
                                                : <c:out value="${room.hotel.location}"/>
                                                <br/>
                                                <spring:message code="roomsearch.page.room.price"/>
                                                : <c:out value="${room.price}"/>
                                                <br/>
                                                <spring:message code="roomsearch.page.bed"/>
                                                : <c:out value="${room.numberOfBed}"/>
                                                <br/>
                                                <spring:message code="label.parking.facility"/>
                                                : <c:out value="${room.hotel.parkingFacility}"/>
                                                <br/>
                                                <spring:message code="label.wifi.facility"/>
                                                : <c:out value="${room.hotel.wifiFacility}"/>
                                                <br/>
                                                <spring:message code="label.swimming.pool.facility"/>
                                                : <c:out value="${room.hotel.swimmingPool}"/>
                                                <br/>
                                                <spring:message code="label.fitness.centre"/>
                                                : <c:out value="${room.hotel.fitnessCentre}"/>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty rooms}">
                        <p><spring:message code="roomsearch.page.no.rooms"/></p>
                    </c:if>
                </div>
                <div style="display: flex; justify-content: center;">
                    <nav aria-label="...">
                        <ul class="pagination pagination-circle">
                            <c:if test="${currentPage>2}">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="/rooms?page=${currentPage-2}">${currentPage-2}</a>
                                </li>
                            </c:if>
                            <c:if test="${currentPage>1}">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="/rooms?page=${currentPage-1}">${currentPage-1}</a>
                                </li>
                            </c:if>
                            <li class="page-item active">
                                <a class="page-link" href="#">${currentPage} </a>
                            </li>
                            <li class="page-item">
                                <a class="page-link"
                                   href="/rooms?page=${currentPage+1}">${currentPage+1}</a>
                            </li>
                            <li class="page-item">
                                <a class="page-link"
                                   href="/rooms?page=${currentPage+2}">${currentPage+2}</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
