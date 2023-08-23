<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="room.page.title"/></title>
    <link rel="stylesheet" href="/assets/css/roomPage.css">
    <link rel="stylesheet" href="/assets/css/customStyle.css">
</head>
<body>

<div class="container room-container">
    <h2><spring:message code="room.page.hotel.name"/>: <c:out value="${room.hotel.name}"/></h2>
    <p><spring:message code="room.page.location"/>: <c:out value="${room.hotel.location}"/></p>
    <div class="row">
        <div class="col-md-6">
            <img src="data:image/jpeg;base64,${room.roomImageBase64Image}" class="room-image" alt="<spring:message code='room.page.room.image.alt'/>">
        </div>
        <div class="col-md-6">
            <h3><spring:message code="room.page.room.type"/>: <c:out value="${room.type}"/></h3>
            <p><spring:message code="room.page.price"/>: <c:out value="${room.price}"/></p>
            <p><spring:message code="room.page.number.of.beds"/>: <c:out value="${room.numberOfBed}"/></p>
            <p><spring:message code="room.page.parking.facility"/>: <c:out value="${room.hotel.parkingFacility}"/></p>
            <p><spring:message code="room.page.swimming.pool"/>: <c:out value="${room.hotel.swimmingPool}"/></p>
            <p><spring:message code="room.page.fitness.centre"/>: <c:out value="${room.hotel.fitnessCentre}"/></p>
            <p><spring:message code="room.page.wifi.facility"/>: <c:out value="${room.hotel.wifiFacility}"/></p>
            <p><spring:message code="room.page.email"/>: <c:out value="${room.hotel.email}"/></p>
            <p><spring:message code="room.page.phone.number"/>: <c:out value="${room.hotel.phoneNumber}"/></p>
            <c:if test="${room.hotel.rating == null}">
                <p class="hotel-rating"><spring:message code="room.page.no.rating"/></p>
            </c:if>
            <c:if test="${room.hotel.rating != null}">
                <p class="hotel-rating"><spring:message code="room.page.rating"/>:
                    <fmt:formatNumber maxFractionDigits="1" value="${room.hotel.rating.rating}"/> <spring:message code="room.page.rating.out.of"/> 5
                </p>
            </c:if>
        </div>
    </div>
    <div style="display: flex; justify-content: center; margin-top: 2rem;">
        <a href="/room/${room.id}/book" style="text-decoration: none;">
            <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                            border-radius: 4px; cursor: pointer; font-size: 16px; width: 130px; margin: 2px">
                <spring:message code="room.page.book.now.button"/>
            </button>
        </a>
        <c:set var="hotelRole" value="HOTEL"/>
        <c:if test="${not empty sessionContext && sessionContext.role==hotelRole && sessionContext.id==room.hotel.id}">
            <a href="/room/${room.id}/edit" style="text-decoration: none;">
                <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                            border-radius: 4px; cursor: pointer; font-size: 16px; width: 130px; margin: 2px">
                    <spring:message code="room.page.edit.button"/>
                </button>
            </a>
        </c:if>
    </div>
    <hr>
    <h3><spring:message code="room.page.hotel.image"/></h3>
    <a href="/hotel/${room.hotel.id}">
        <img src="data:image/jpeg;base64,${room.hotel.hotelImageBase64Image}" class="hotel-image" alt="<spring:message code='room.page.hotel.image.alt'/>">
    </a>
</div>

</body>
</html>
