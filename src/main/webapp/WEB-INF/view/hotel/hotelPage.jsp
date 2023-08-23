<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/assets/css/hotelPage.css">
    <title><spring:message code="hotel.details.title" arguments="${hotel.name}" /></title>
</head>
<body>

<header>
    <h1>${hotel.name}</h1>
</header>

<div class="container">
    <img src="data:image/jpeg;base64,${hotel.hotelImageBase64Image}" alt="<spring:message code="hotel.image.alt" />" class="hotel-image">
    <div class="hotel-info">
        <div class="hotel-details">
            <h2 class="hotel-name">${hotel.name}</h2>
            <p><spring:message code="hotel.email.label" />: ${hotel.email}</p>
            <p><spring:message code="hotel.phone.label" />: ${hotel.phoneNumber}</p>
            <p><spring:message code="hotel.location.label" />: ${hotel.location}</p>
            <c:if test="${hotel.rating == null}">
                <p class="hotel-rating"><spring:message code="hotel.noRating" /></p>
            </c:if>
            <c:if test="${hotel.rating != null}">
                <p class="hotel-rating">
                    <spring:message code="hotel.rating.label" />
                    <fmt:formatNumber maxFractionDigits="1" value="${hotel.rating.rating}"/>
                    <spring:message code="hotel.rating.outOfFive" />
                </p>
            </c:if>
        </div>
        <div class="hotel-amenities">
            <h3><spring:message code="hotel.amenities.title" /></h3>
            <ul>
                <li><spring:message code="hotel.parking.label" />: ${hotel.parkingFacility}</li>
                <li><spring:message code="hotel.pool.label" />: ${hotel.swimmingPool}</li>
                <li><spring:message code="hotel.fitness.label" />: ${hotel.fitnessCentre}</li>
                <li><spring:message code="hotel.wifi.label" />: ${hotel.wifiFacility}</li>
            </ul>
        </div>
    </div>
    <c:set var="hotelRole" value="HOTEL"/>
    <c:if test="${not empty sessionContext && sessionContext.role==hotelRole && sessionContext.id==hotel.id}">
        <div style="display: flex; justify-content: center; margin-top: 2rem;">
            <a href="/hotel/${hotel.id}/bookingList" style="text-decoration: none;">
                <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                            border-radius: 4px; cursor: pointer; font-size: 16px; width: 130px; margin: 2px">
                    <spring:message code="hotel.bookingList.button" />
                </button>
            </a>
            <a href="/hotel/${hotel.id}/edit" style="text-decoration: none;">
                <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                            border-radius: 4px; cursor: pointer; font-size: 16px; width: 130px; margin: 2px">
                    <spring:message code="hotel.edit.button" />
                </button>
            </a>
        </div>
    </c:if>
    <hr/>
    <h2><spring:message code="hotel.rooms.title" /></h2>
    <div class="room-images">
        <c:forEach items="${hotel.rooms}" var="room">
            <a href="/room/${room.id}">
                <img
                        src="data:image/jpeg;base64,${room.roomImageBase64Image}"
                        alt="<spring:message code="room.image.alt" />"
                        class="room-image">
            </a>
        </c:forEach>
    </div>
    <c:if test="${not empty sessionContext && sessionContext.role==hotelRole && sessionContext.id==hotel.id}">
        <div style="display: flex; justify-content: center; margin-top: 2rem;">
            <a href="/hotel/${hotel.id}/addRoom" style="text-decoration: none;">
                <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                        border-radius: 4px; cursor: pointer; font-size: 16px;">
                    <spring:message code="hotel.addRoom.button" />
                </button>
            </a>
        </div>
    </c:if>
</div>

</body>
</html>
