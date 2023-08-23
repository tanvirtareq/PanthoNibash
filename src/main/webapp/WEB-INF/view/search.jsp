<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Room Search</title>
    <link rel="stylesheet" href="/assets/css/customStyle.css">
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 ml-auto">
            <div class="custom-card">
                <div class="card-body">
                    <div class="h2 card-title"><spring:message code="label.room.search"/></div>
                    <form:form action="/search" method="get"
                               modelAttribute="searchRoomFilter">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="parkingFacility"><spring:message
                                                code="label.parking.facility"/></label>
                                        <c:forEach items="${facilityOptions}" var="facilityOption">
                                            <div class="form-check">
                                                <form:radiobutton class="form-check-input" id="${facilityOption.value}"
                                                                  path="parkingFacility"
                                                                  value="${facilityOption.value}"/>
                                                <label class="form-check-label" for="${facilityOption.value}">
                                                        ${facilityOption.value}
                                                </label>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="mb-3">
                                        <label for="wifiFacility"><spring:message code="label.wifi.facility"/> </label>
                                        <c:forEach items="${facilityOptions}" var="facilityOption">
                                            <div class="form-check">
                                                <form:radiobutton class="form-check-input" id="${facilityOption.value}"
                                                                  path="wifiFacility" value="${facilityOption.value}"/>
                                                <label class="form-check-label" for="${facilityOption.value}">
                                                        ${facilityOption.value}
                                                </label>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="mb-3">
                                        <label for="swimmingPool"><spring:message
                                                code="label.swimming.pool.facility"/> </label>
                                        <c:forEach items="${facilityOptions}" var="facilityOption">
                                            <div class="form-check">
                                                <form:radiobutton class="form-check-input" id="${facilityOption.value}"
                                                                  path="swimmingPool" value="${facilityOption.value}"/>
                                                <label class="form-check-label" for="${facilityOption.value}">
                                                        ${facilityOption.value}
                                                </label>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="mb-3">
                                        <label for="fitnessCentre"><spring:message
                                                code="label.fitness.centre"/> </label>
                                        <c:forEach items="${facilityOptions}" var="facilityOption">
                                            <div class="form-check">
                                                <form:radiobutton class="form-check-input" id="${facilityOption.value}"
                                                                  path="fitnessCentre" value="${facilityOption.value}"/>
                                                <label class="form-check-label" for="${facilityOption.value}">
                                                        ${facilityOption.value}
                                                </label>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="mb-3">
                                        <label for="roomType"><spring:message code="label.room.type"/> </label>
                                        <c:forEach items="${roomTypeOptions}" var="roomTypeOption">
                                            <div class="form-check">
                                                <form:radiobutton class="form-check-input" id="${roomTypeOption}" path="roomType" value="${roomTypeOption}"/>
                                                <label class="form-check-label" for="${roomTypeOption}">
                                                        ${roomTypeOption.displayName} <!-- Display the enum display name -->
                                                </label>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label class="form-label" for="hotelName"><spring:message
                                                code="label.hotel.name"/> </label>
                                        <form:input type="text" class="form-control" path="hotelName" id="hotelName"
                                                    name="hotelName"
                                                    placeholder="Hotel name" oninput="searchHotel()"/>
                                        <select id="hotelSelect" class="form-select" size="5"
                                                style="display: none;"></select>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label" for="location"><spring:message
                                                code="label.location"/> </label>
                                        <form:input type="text" class="form-control" path="location" id="location"
                                                    name="location"
                                                    placeholder="Location"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label" for="priceMin"><spring:message
                                                code="label.price.range"/> </label>
                                        <div class="input-group">
                                            <span class="input-group-text">$</span>
                                            <form:input type="number" path="priceMin" class="form-control" id="priceMin"
                                                        name="priceMin"
                                                        placeholder="Min Price"/>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <div class="input-group">
                                            <span class="input-group-text">$</span>
                                            <form:input type="number" path="priceMax" class="form-control" id="priceMax"
                                                        name="priceMax"
                                                        placeholder="Max Price"/>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label" for="numberOfBed"><spring:message
                                                code="label.number.of.bed"/> </label>
                                        <form:input path="numberOfBed" type="number" class="form-control"
                                                    id="numberOfBed" name="numberOfBed"
                                                    placeholder="Number of Beds"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label" for="checkIn"><spring:message
                                                code="label.check.in.date"/> </label>
                                        <form:input path="checkIn" type="date" class="form-control" id="checkIn"
                                                    name="checkIn"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label" for="checkOut"><spring:message
                                                code="label.check.out.date"/> </label>
                                        <form:input path="checkOut" type="date" class="form-control" id="checkOut"
                                                    name="checkOut"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary"><spring:message code="label.search"/></button>
                        <button type="reset" class="btn btn-primary"><spring:message code="label.reset"/></button>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-md-8">
            <div class="custom-card">
                <div class="card-body" style="height: 800px; overflow-y: auto;">
                    <div class="h3 card-title">
                        <spring:message code="label.search.results"/>
                    </div>
                    <c:if test="${not empty searchResults}">
                        <c:forEach items="${searchResults}" var="room">
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
                                            <div class="h5 card-title"><spring:message code="label.hotel"/> <c:out
                                                    value="${room.hotel.name}"/></div>
                                            <div class="card-text">
                                                <c:if test="${room.hotel.rating == null}">
                                                    <span class="hotel-rating"><spring:message
                                                            code="label.rating"/> <spring:message
                                                            code="label.no.rating"/> </span>
                                                </c:if>
                                                <c:if test="${room.hotel.rating != null}">
                                                    <span class="hotel-rating"><spring:message code="label.rating"/>
                                                        <fmt:formatNumber maxFractionDigits="1"
                                                                          value="${room.hotel.rating.rating}"/>
                                                        <spring:message code="label.out.of"/> 5 </span>
                                                </c:if>
                                                <br/>
                                                <spring:message code="label.room.type"/> <c:out value="${room.type}"/>
                                                <br/>
                                                <spring:message code="label.location"/> <c:out
                                                    value="${room.hotel.location}"/>
                                                <br/>
                                                <spring:message code="label.room.price"/> <c:out value="${room.price}"/>
                                                <br/>
                                                <spring:message code="label.number.of.bed"/> <c:out
                                                    value="${room.numberOfBed}"/>
                                                <br/>
                                                <spring:message code="label.parking.facility"/> <c:out
                                                    value="${room.hotel.parkingFacility}"/>
                                                <br/>
                                                <spring:message code="label.wifi.facility"/> <c:out
                                                    value="${room.hotel.wifiFacility}"/>
                                                <br/>
                                                <spring:message code="label.swimming.pool.facility"/> <c:out
                                                    value="${room.hotel.swimmingPool}"/>
                                                <br/>
                                                <spring:message code="label.fitness.centre"/> <c:out
                                                    value="${room.hotel.fitnessCentre}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty searchResults}">
                        <p><spring:message code="label.no.matching.rooms.found"/></p>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/assets/js/searchHotelName.js"></script>
</body>
</html>
