<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/18/23
  Time: 11:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Hotel Details</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      line-height: 1.6;
      margin: 0;
      padding: 0;
    }

    header {
      background-color: #333;
      color: #fff;
      text-align: center;
      padding: 1rem;
    }

    .container {
      max-width: 1200px;
      margin: 0 auto;
      padding: 1rem;
    }

    .hotel-image {
      max-width: 100%;
      height: auto;
    }

    .hotel-info {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-top: 1rem;
    }

    .hotel-details {
      flex: 1;
    }

    .hotel-name {
      font-size: 2rem;
      font-weight: bold;
      margin-bottom: 1rem;
    }

    .hotel-rating {
      font-size: 1.2rem;
      color: #f39c12;
      margin-bottom: 1rem;
    }

    .hotel-amenities {
      margin-top: 1rem;
    }

    .hotel-amenities h3 {
      margin-bottom: 0.5rem;
    }

    .hotel-amenities ul {
      list-style: none;
      padding: 0;
      margin: 0;
    }

    .hotel-amenities li {
      margin-bottom: 0.5rem;
    }

    .room-images {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
      grid-gap: 1rem;
      margin-top: 2rem;
    }

    .room-image {
      max-width: 100%;
      height: auto;
    }
  </style>
</head>
<body>
<body>
<header>
  <h1>${hotel.name}</h1>
</header>
<div class="container">
  <img src="data:image/jpeg;base64,${hotel.hotelImageBase64Image}" alt="Hotel ABC" class="hotel-image">
  <div class="hotel-info">
    <div class="hotel-details">
      <h2 class="hotel-name">${hotel.name}</h2>
      <p>Email: ${hotel.email}</p>
      <p>Phone: ${hotel.phoneNumber}</p>
      <p>Location: ${hotel.location}</p>
      <p class="hotel-rating">Rating: 4.5 out of 5</p>
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
  <!-- Add the button to see the booking list -->
  <div style="display: flex; justify-content: center; margin-top: 2rem;">
    <a href="/hotel/${hotel.id}/booking/list" style="text-decoration: none;">
      <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                        border-radius: 4px; cursor: pointer; font-size: 16px;">
        See Booking List
      </button>
    </a>
  </div>
  <h2>Hotel Rooms</h2>
  <div class="room-images">
    <c:forEach items="${hotel.rooms}" var="room">
      <a href="/room/${room.id}">
        <img src="data:image/jpeg;base64,${room.roomImageBase64Image}" alt="Room" class="room-image">
      </a>
    </c:forEach>
  </div>

  <div style="display: flex; justify-content: center; margin-top: 2rem;">
    <a href="/hotel/${hotel.id}/addroom" style="text-decoration: none;">
      <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                        border-radius: 4px; cursor: pointer; font-size: 16px;">
        Add Room
      </button>
    </a>
  </div>
</div>



</body>
</html>