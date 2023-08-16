<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Details</title>

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/0.9.0rc1/jspdf.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.3.2/html2canvas.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bookingDetailsPage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customStyle.css">
</head>
<body>
<div class="container-xxl booking-info">
    <div id="bookingContainer" style="padding: 20px;">
        <header class="booking-details-header">Booking Details</header>
        <div class="row">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 ">
                        <img src="data:image/jpeg;base64,${booking.guestImageBase64Image}" alt="Guest Photo"
                             class="guest-photo">
                    </div>
                    <div class="col-md-8 ">
                        <h4>Guest Information</h4>
                        <p><strong>Name:</strong> ${booking.guestName}</p>
                        <p><strong>Email:</strong> ${booking.guestEmail}</p>
                        <p><strong>Phone:</strong> ${booking.guestPhoneNumber}</p>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <c:if test="${booking.customer != null}">
                    <div class="row">
                        <div class="col-md-4">
                            <a href="${pageContext.request.contextPath}/customer/${booking.customer.id}">
                                <img src="data:image/jpeg;base64,${booking.customer.profilePicBase64Image}"
                                     alt="Customer Photo" class="guest-photo">
                            </a>
                        </div>
                        <div class="col-md-8">
                            <h4>Customer/Referral Information</h4>
                            <p><strong>Name:</strong> ${booking.customer.name}</p>
                            <p><strong>Email:</strong> ${booking.customer.email}</p>
                            <p><strong>Phone:</strong> ${booking.customer.phoneNumber}</p>
                        </div>
                    </div>
                </c:if>
            </div>

        </div>
        <hr/>
        <div class="row">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4">
                        <a href="${pageContext.request.contextPath}/room/${booking.room.id}">
                            <img src="data:image/jpeg;base64,${booking.room.roomImageBase64Image}" alt="Room Photo"
                                 class="room-photo">
                        </a>
                    </div>
                    <div class="col-md-8">
                        <h4>Room Information</h4>
                        <p><strong>Room Number:</strong> ${booking.roomNumber}</p>
                        <p><strong>Room Type:</strong> ${booking.room.type}</p>
                        <p><strong>Price:</strong> ${booking.room.price}</p>
                        <p><strong>Number of Beds:</strong> ${booking.room.numberOfBed}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <h4>Booking Information</h4>
                        <p><strong>Check-in Date:</strong> ${booking.checkInDate}</p>
                        <p><strong>Check-out Date:</strong> ${booking.checkOutDate}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4 ">
                        <a href="${pageContext.request.contextPath}/hotel/${booking.room.hotel.id}">
                            <img src="data:image/jpeg;base64,${booking.room.hotel.hotelImageBase64Image}"
                                 alt="Hotel Photo"
                                 class="hotel-photo">
                        </a>
                    </div>
                    <div class="col-md-8 ">
                        <h4>Hotel Information</h4>
                        <p><strong>Name:</strong> ${booking.room.hotel.name}</p>
                        <p><strong>Email:</strong> ${booking.room.hotel.email}</p>
                        <p><strong>Phone:</strong> ${booking.room.hotel.phoneNumber}</p>
                        <p><strong>Location:</strong> ${booking.room.hotel.location}</p>
                        <p><strong>Parking Facility:</strong> ${booking.room.hotel.parkingFacility}</p>
                        <p><strong>Swimming Pool:</strong> ${booking.room.hotel.swimmingPool}</p>
                        <p><strong>Fitness Centre:</strong> ${booking.room.hotel.fitnessCentre}</p>
                        <p><strong>WiFi Facility:</strong> ${booking.room.hotel.wifiFacility}</p>

                        <c:if test="${booking.room.hotel.rating == null}">
                            <p class="hotel-rating">Rating: No Rating </p>
                        </c:if>
                        <c:if test="${booking.room.hotel.rating != null}">

                            <p class="hotel-rating">Rating: <fmt:formatNumber maxFractionDigits="1"
                                                                              value="${booking.room.hotel.rating.rating}"/>
                                out of 5</p>
                        </c:if>
                    </div>
                </div>
            </div>

        </div>


    </div>
    <c:set var="customerRole" value="CUSTOMER"/>

    <div style="display: flex; justify-content: center; margin-top: 2rem;">
        <button onclick="downloadAsPdf()" style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                        border-radius: 4px; cursor: pointer; font-size: 16px; margin: 2px; width: 200px;">
            Download as PDF
        </button>
        <c:forEach items="${buttonDtoList}" var="buttonDto">
            <a href="${buttonDto.link}" style="text-decoration: none;">
                <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                        border-radius: 4px; cursor: pointer; font-size: 16px; margin: 2px; width: 200px;">
                    <c:out value="${buttonDto.name}"/>
                </button>
            </a>
        </c:forEach>
    </div>

</div>

<script src="${pageContext.request.contextPath}/assets/js/downloadBookingDetails.js"></script>

</body>
</html>