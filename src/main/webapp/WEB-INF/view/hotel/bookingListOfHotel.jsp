<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Hotel Booking List</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customStyle.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customTable.css">

</head>
<body>
<header class="custom-header">
  <h1>Hotel Booking List - ${hotel.name}</h1>
</header>

<div class="container-xxl">
  <div class="row">
    <div class="col-md-3">
      <div class="container-xxl">
        <div class="card custom-card">
          <div class="card-body">
            <form action="/hotel/${hotel.id}/bookingList" method="GET">
              <div class="mb-3">
                <label class="form-label" for="checkInDate">Check-in Date:</label>
                <input type="date" class="form-control" id="checkInDate" name="checkInDate"
                       placeholder="Check-in Date">
              </div>

              <div class="mb-3">
                <label class="form-label" for="checkOutDate">Check-out Date:</label>
                <input type="date" class="form-control" id="checkOutDate" name="checkOutDate"
                       placeholder="Check-out Date">
              </div>

              <div class="mb-3">
                <label class="form-label" for="roomNumber">Room Number:</label>
                <input type="text" class="form-control" id="roomNumber" name="roomNumber"
                       placeholder="Room Number">
              </div>

              <div class="mb-3">
                <label class="form-label" for="customerName">Customer Name:</label>
                <input type="text" class="form-control" id="customerName" name="customerName"
                       placeholder="Customer Name">
              </div>

              <div class="mb-3">
                <label class="form-label" for="customerEmail">Customer Email:</label>
                <input type="text" class="form-control" id="customerEmail" name="customerEmail"
                       placeholder="Customer Email">
              </div>
              <div class="mb-3">
                <label class="form-label">Room type:</label>
                <c:forEach items="${roomTypeOptions}" var="option">
                  <div class="form-check">
                    <input class="form-check-input" type="radio" name="roomType"
                           id="roomType" value="${option.value}">
                    <label class="form-check-label" for="roomType">${option.value}</label>
                  </div>
                </c:forEach>
              </div>

              <button type="submit" class="btn btn-primary">Filter</button>
            </form>
          </div>

        </div>
      </div>

    </div>

    <div class="col-md-9">
      <div class="container-xxl">
        <table id="bookingTable" class="custom-card">
          <thead>
          <tr>
            <th>Guest Photo</th>
            <th>
              Guest Name
            </th>
            <th>
              Guest Email
            </th>
            <th>
              Guest Phone Number
            </th>
            <th>
              Room Number
            </th>
            <th>
              Check-in Date
            </th>
            <th>
              Check-out Date
            </th>
            <th>
              Room Type
            </th>
            <th>Booking Details</th>

          </tr>
          </thead>
          <tbody>
          <c:forEach items="${bookingList}" var="booking">
            <tr>
              <td>
                <img src="data:image/jpeg;base64,${booking.guestImageBase64Image}" alt="Guest"
                     class="customer-image">
              </td>
              <td>${booking.guestName}</td>
              <td>${booking.guestEmail}</td>
              <td>${booking.guestPhoneNumber}</td>
              <td>${booking.roomNumber}</td>
              <td>${booking.checkInDate}</td>
              <td>${booking.checkOutDate}</td>
              <td>${booking.room.type}</td>
              <td>
                <a href="${pageContext.request.contextPath}/booking/${booking.id}" class="btn btn-primary">Details</a>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/bookingTable.js"></script>
</body>
</html>