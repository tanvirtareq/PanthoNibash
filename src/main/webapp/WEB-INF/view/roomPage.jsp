<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/20/23
  Time: 10:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Room Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <style>
        .room-container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }
        .room-image {
            max-width: 100%;
            height: auto;
        }
        .hotel-image {
            max-width: 100%;
            height: auto;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="container room-container">
    <h2>Hotel Name: <c:out value="${room.hotel.name}"/></h2>
    <p>Location: <c:out value="${room.hotel.location}"/></p>
    <div class="row">
        <div class="col-md-6">
            <img src="data:image/jpeg;base64,${room.roomImageBase64Image}" class="room-image" alt="Room Image">
        </div>
        <div class="col-md-6">
            <h3>Room Type: <c:out value="${room.type}"/></h3>
            <p>Price: <c:out value="${room.price}"/></p>
            <p>Number of Beds: <c:out value="${room.numberOfBed}"/></p>
            <p>Parking Facility: <c:out value="${room.hotel.parkingFacility}"/></p>
            <p>Swimming Pool: <c:out value="${room.hotel.swimmingPool}"/></p>
            <p>Fitness Centre: <c:out value="${room.hotel.fitnessCentre}"/></p>
            <p>Wi-Fi Facility: <c:out value="${room.hotel.wifiFacility}"/></p>
            <p>Email: <c:out value="${room.hotel.email}"/></p>
            <p>Phone Number: <c:out value="${room.hotel.phoneNumber}"/></p>

        </div>

    </div>
    <div style="display: flex; justify-content: center; margin-top: 2rem;">
        <a href="/room/${room.id}/book" style="text-decoration: none;">
            <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                            border-radius: 4px; cursor: pointer; font-size: 16px; width: 130px; margin: 2px">
                Book Now
            </button>
        </a>
        <c:set var="hotelRole" value="HOTEL"/>

        <c:if test="${not empty sessionContext && sessionContext.role==hotelRole && sessionContext.id==room.hotel.id}">
            <a href="/room/${room.id}/edit" style="text-decoration: none;">
                <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                            border-radius: 4px; cursor: pointer; font-size: 16px; width: 130px; margin: 2px">
                    Edit
                </button>
            </a>
        </c:if>
    </div>

    <hr>

    <h3>Hotel Image</h3>
    <a href="/hotel/${room.hotel.id}">
        <img src="data:image/jpeg;base64,${room.hotel.hotelImageBase64Image}" class="hotel-image" alt="Hotel Image">
    </a>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>