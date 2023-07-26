<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/22/23
  Time: 10:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Details</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            background-color: #f9f9f9;
            padding-top: 20px;
        }

        .booking-info {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        .guest-photo,
        .room-photo,
        .hotel-photo {
            max-width: 200px;
            height: auto;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .rating {
            color: gold;
        }
    </style>
</head>
<body>
<div class="container-xxl booking-info">

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
                <!-- Customer/Referral Information -->
                <div class="row">
                    <div class="col-md-4">
                        <a href="/customer/${booking.customer.id}">
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
                    <a href="/room/${booking.room.id}">
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
                    <a href="/hotel/${booking.room.hotel.id}">
                        <img src="data:image/jpeg;base64,${booking.room.hotel.hotelImageBase64Image}" alt="Hotel Photo"
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
                    <p class="rating"><strong>Rating:</strong> 4.8</p>
                </div>
            </div>
        </div>

    </div>

    <c:set var="customerRole" value="CUSTOMER"/>

    <c:if test="${booking.customer != null && booking.review==null && sessionContext!=null
  && sessionContext.role==customerRole  && booking.customer.id == sessionContext.id}">
        <div style="display: flex; justify-content: center; margin-top: 2rem;">
            <a href="/customer/${booking.customer.id}/booking/${booking.id}/addReview" style="text-decoration: none;">
                <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                        border-radius: 4px; cursor: pointer; font-size: 16px;">
                    Add Review
                </button>
            </a>
        </div>
    </c:if>
</div>

</body>
</html>