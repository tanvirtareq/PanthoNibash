<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/hotelPage.css">
    <title>Hotel Details</title>

</head>
<body>
<header>
    <h1>${hotel.name}</h1>
</header>
<div class="container">
    <img src="data:image/jpeg;base64,${hotel.hotelImageBase64Image}" alt="Hotel" class="hotel-image">
    <div class="hotel-info">
        <div class="hotel-details">
            <h2 class="hotel-name">${hotel.name}</h2>
            <p>Email: ${hotel.email}</p>
            <p>Phone: ${hotel.phoneNumber}</p>
            <p>Location: ${hotel.location}</p>
            <c:if test="${hotel.rating == null}">
                <p class="hotel-rating">Rating: No Rating </p>
            </c:if>
            <c:if test="${hotel.rating != null}">
                <p class="hotel-rating">Rating:
                    <fmt:formatNumber maxFractionDigits="1" value="${hotel.rating.rating}"/> out of 5
                </p>
            </c:if>
        </div>
        <div class="hotel-amenities">
            <h3>Amenities</h3>
            <ul>
                <li>Parking facility: ${hotel.parkingFacility}</li>
                <li>Swimming pool: ${hotel.swimmingPool}</li>
                <li>Fitness centre: ${hotel.fitnessCentre}</li>
                <li>WiFi: ${hotel.wifiFacility}</li>
            </ul>
        </div>
    </div>

    <c:set var="hotelRole" value="HOTEL"/>

    <c:if test="${not empty sessionContext && sessionContext.role==hotelRole && sessionContext.id==hotel.id}">

        <div style="display: flex; justify-content: center; margin-top: 2rem;">
            <a href="${pageContext.request.contextPath}/hotel/${hotel.id}/bookingList" style="text-decoration: none;">
                <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                            border-radius: 4px; cursor: pointer; font-size: 16px; width: 130px; margin: 2px">
                    Booking List
                </button>
            </a>
            <a href="${pageContext.request.contextPath}/hotel/${hotel.id}/edit" style="text-decoration: none;">
                <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                            border-radius: 4px; cursor: pointer; font-size: 16px; width: 130px; margin: 2px">
                    Edit
                </button>
            </a>
        </div>

    </c:if>
    <hr/>
    <h2>Hotel Rooms</h2>
    <div class="room-images">
        <c:forEach items="${hotel.rooms}" var="room">
            <a href="${pageContext.request.contextPath}/room/${room.id}">
                <img
                        src="data:image/jpeg;base64,${room.roomImageBase64Image}"
                        alt="Room"
                        class="room-image">
            </a>
        </c:forEach>
    </div>

    <c:if test="${not empty sessionContext && sessionContext.role==hotelRole && sessionContext.id==hotel.id}">

        <div style="display: flex; justify-content: center; margin-top: 2rem;">
            <a href="${pageContext.request.contextPath}/hotel/${hotel.id}/addRoom" style="text-decoration: none;">
                <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                        border-radius: 4px; cursor: pointer; font-size: 16px;">
                    Add Room
                </button>
            </a>
        </div>

    </c:if>
</div>

</body>
</html>