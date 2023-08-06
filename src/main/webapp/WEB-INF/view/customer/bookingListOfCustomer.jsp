<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/23/23
  Time: 11:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customTable.css">
    <title>Customer Booking List</title>
</head>
<body>
<header class="custom-header">
    <h1>Customer's Booking List - ${customer.name}</h1>
</header>

<div class="container-xxl">
    <div class="row">
        <div class="col-md-2">
            <div class="container">
                <div class="card custom-card">
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/customer/${customer.id}/bookinglist" method="GET">
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
                                <label class="form-label" for="hotelName">Hotel Name:</label>
                                <input type="text" class="form-control" id="hotelName" name="hotelName"
                                       placeholder="Hotel Name">
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

        <div class="col-md-10">
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
                            Hotel Name
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
                            <td class="first-letter-capital">${booking.guestName}</td>
                            <td>${booking.guestEmail}</td>
                            <td>${booking.guestPhoneNumber}</td>
                            <td class="first-letter-capital">${booking.room.hotel.name}</td>
                            <td>${booking.roomNumber}</td>
                            <td>${booking.checkInDate}</td>
                            <td>${booking.checkOutDate}</td>
                            <td class="first-letter-capital">${booking.room.type}</td>
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
