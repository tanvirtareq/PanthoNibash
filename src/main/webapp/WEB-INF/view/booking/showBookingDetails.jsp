<%@ page contentType="text/html;charset=UTF-8"   %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="page.title"/> </title>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/0.9.0rc1/jspdf.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.3.2/html2canvas.min.js"></script>
    <link rel="stylesheet" href="/assets/css/bookingDetailsPage.css">
    <link rel="stylesheet" href="/assets/css/customStyle.css">
</head>
<body>

<div class="container-xxl booking-info">
    <div id="bookingContainer" class="p-4">
        <header class="booking-details-header"><spring:message code="booking.details.page.bookingInfo"/> </header>
        <div class="row">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4">
                        <img src="data:image/jpeg;base64,${booking.guestImageBase64Image}" alt="Guest Photo"
                             class="guest-photo">
                    </div>
                    <div class="col-md-8">
                        <h4><spring:message code="booking.details.page.guestInfo"/> </h4>
                        <p><strong><spring:message code="booking.details.page.name"/> :</strong> ${booking.guestName}</p>
                        <p><strong><spring:message code="booking.details.page.email"/> :</strong> ${booking.guestEmail}</p>
                        <p><strong><spring:message code="booking.details.page.phone"/> :</strong> ${booking.guestPhoneNumber}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <c:if test="${booking.customer != null}">
                    <div class="row">
                        <div class="col-md-4">
                            <a href="/customer/${booking.customer.id}">
                                <img src="data:image/jpeg;base64,${booking.customer.profilePicBase64Image}"
                                     alt="Customer Photo" class="guest-photo">
                            </a>
                        </div>
                        <div class="col-md-8">
                            <h4><spring:message code="customer.referral.info"/> </h4>
                            <p><strong><spring:message code="booking.details.page.name"/> :</strong> ${booking.customer.name}</p>
                            <p><strong><spring:message code="booking.details.page.email"/> </strong> ${booking.customer.email}</p>
                            <p><strong><spring:message code="booking.details.page.phone"/> :</strong> ${booking.customer.phoneNumber}</p>
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
                        <h4><spring:message code="room.info.header"/></h4>
                        <p><strong><spring:message code="room.number"/>:</strong> ${booking.roomNumber}</p>
                        <p><strong><spring:message code="room.type"/>:</strong> ${booking.room.type}</p>
                        <p><strong><spring:message code="room.price"/>:</strong> ${booking.room.price}</p>
                        <p><strong><spring:message code="room.beds"/>:</strong> ${booking.room.numberOfBed}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <h4><spring:message code="booking.info.header"/></h4>
                        <p><strong><spring:message code="checkin.date"/>:</strong> ${booking.checkInDate}</p>
                        <p><strong><spring:message code="checkout.date"/>:</strong> ${booking.checkOutDate}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-4">
                        <a href="/hotel/${booking.room.hotel.id}">
                            <img src="data:image/jpeg;base64,${booking.room.hotel.hotelImageBase64Image}"
                                 alt="Hotel Photo"
                                 class="hotel-photo">
                        </a>
                    </div>
                    <div class="col-md-8">
                        <c:set var="hotel" value="${booking.room.hotel}"/>
                        <h4><spring:message code="hotel.info.header"/></h4>
                        <p><strong><spring:message code="hotel.name"/>:</strong> ${hotel.name}</p>
                        <p><strong><spring:message code="hotel.email"/>:</strong> ${hotel.email}</p>
                        <p><strong><spring:message code="hotel.phone"/>:</strong> ${hotel.phoneNumber}</p>
                        <p><strong><spring:message code="hotel.location"/>:</strong> ${hotel.location}</p>
                        <p><strong><spring:message code="hotel.parking.facility"/>:</strong> ${hotel.parkingFacility}</p>
                        <p><strong><spring:message code="hotel.swimming.pool"/>:</strong> ${hotel.swimmingPool}</p>
                        <p><strong><spring:message code="hotel.fitness.centre"/>:</strong> ${hotel.fitnessCentre}</p>
                        <p><strong><spring:message code="hotel.wifi.facility"/>:</strong> ${hotel.wifiFacility}</p>
                        <c:choose>
                            <c:when test="${hotel.rating == null}">
                                <p class="hotel-rating"><spring:message code="hotel.no.rating"/></p>
                            </c:when>
                            <c:otherwise>
                                <p class="hotel-rating"><spring:message code="hotel.rating"/>: <fmt:formatNumber maxFractionDigits="1"
                                                                                                                 value="${hotel.rating.rating}"/>
                                    out of 5</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="custom-button-container">
        <button onclick="downloadAsPdf()" class="custom-general-button">
            <spring:message code="download.pdf.button.label"/>
        </button>
        <c:forEach items="${buttonDtoList}" var="buttonDto">
            <a href="${buttonDto.link}" style="text-decoration: none;">
                <button class="custom-general-button">
                    <c:out value="${buttonDto.name}"/>
                </button>
            </a>
        </c:forEach>
    </div>
</div>

<script src="/assets/js/downloadBookingDetails.js"></script>

</body>
</html>