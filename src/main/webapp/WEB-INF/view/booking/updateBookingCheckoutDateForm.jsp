<%@ page contentType="text/html;charset=UTF-8"   %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="booking.details.page.title" /></title>
    <link rel="stylesheet" href="/assets/css/bookingDetailsPage.css">
    <link rel="stylesheet" href="/assets/css/customStyle.css">
</head>
<body>

<form:form action="${formPostUrl}" method="post" modelAttribute="booking">
    <div class="container-xxl booking-info">
        <div id="bookingContainer" class="p-4">
            <header class="booking-details-header"><spring:message code="booking.details.page.header" /></header>
            <div class="row">
                <div class="col-md-6">
                    <div class="row">
                        <div class="col-md-4">
                            <img src="data:image/jpeg;base64,${booking.guestImageBase64Image}" alt="<spring:message code="booking.details.page.guestPhoto.alt" />"
                                 class="guest-photo">
                        </div>
                        <div class="col-md-8">
                            <h4><spring:message code="booking.details.page.guestInfo" /></h4>
                            <p><strong><spring:message code="booking.details.page.name" />:</strong> ${booking.guestName}</p>
                            <p><strong><spring:message code="booking.details.page.email" />:</strong> ${booking.guestEmail}</p>
                            <p><strong><spring:message code="booking.details.page.phone" />:</strong> ${booking.guestPhoneNumber}</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <c:if test="${booking.customer != null}">
                        <div class="row">
                            <div class="col-md-4">
                                <a href="/customer/${booking.customer.id}">
                                    <img src="data:image/jpeg;base64,${booking.customer.profilePicBase64Image}"
                                         alt="<spring:message code="booking.details.page.customerPhoto.alt" />"
                                         class="guest-photo">
                                </a>
                            </div>
                            <div class="col-md-8">
                                <h4><spring:message code="booking.details.page.customerReferralInfo" /></h4>
                                <p><strong><spring:message code="booking.details.page.name" />:</strong> ${booking.customer.name}</p>
                                <p><strong><spring:message code="booking.details.page.email" />:</strong> ${booking.customer.email}</p>
                                <p><strong><spring:message code="booking.details.page.phone" />:</strong> ${booking.customer.phoneNumber}</p>
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
                                <img src="data:image/jpeg;base64,${booking.room.roomImageBase64Image}" alt="<spring:message code="booking.details.page.roomPhoto.alt" />"
                                     class="room-photo">
                            </a>
                        </div>
                        <div class="col-md-8">
                            <h4><spring:message code="booking.details.page.roomInfo" /></h4>
                            <p><strong><spring:message code="booking.details.page.roomNumber" />:</strong> ${booking.roomNumber}</p>
                            <p><strong><spring:message code="booking.details.page.roomType" />:</strong> ${booking.room.type}</p>
                            <p><strong><spring:message code="booking.details.page.price" />:</strong> ${booking.room.price}</p>
                            <p><strong><spring:message code="booking.details.page.numberOfBeds" />:</strong> ${booking.room.numberOfBed}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="h4"><spring:message code="booking.details.page.bookingInfo" /></div>
                            <div class="p"><strong><spring:message code="booking.details.page.checkinDate" />:</strong> ${booking.checkInDate}</div>
                            <div class="p"><strong><spring:message code="booking.details.page.checkoutDate" />:</strong>
                                <form:input path="checkOutDate" type="date"/>
                                <div class="error text-danger">${errorMessage}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="row">
                        <div class="col-md-4">
                            <a href="/hotel/${booking.room.hotel.id}">
                                <img src="data:image/jpeg;base64,${booking.room.hotel.hotelImageBase64Image}"
                                     alt="<spring:message code="booking.details.page.hotelPhoto.alt" />"
                                     class="hotel-photo">
                            </a>
                        </div>
                        <div class="col-md-8">
                            <h4><spring:message code="booking.details.page.hotelInfo" /></h4>
                            <p><strong><spring:message code="booking.details.page.name" />:</strong> ${booking.room.hotel.name}</p>
                            <p><strong><spring:message code="booking.details.page.email" />:</strong> ${booking.room.hotel.email}</p>
                            <p><strong><spring:message code="booking.details.page.phone" />:</strong> ${booking.room.hotel.phoneNumber}</p>
                            <p><strong><spring:message code="booking.details.page.location" />:</strong> ${booking.room.hotel.location}</p>
                            <p><strong><spring:message code="booking.details.page.parkingFacility" />:</strong> ${booking.room.hotel.parkingFacility}</p>
                            <p><strong><spring:message code="booking.details.page.swimmingPool" />:</strong> ${booking.room.hotel.swimmingPool}</p>
                            <p><strong><spring:message code="booking.details.page.fitnessCentre" />:</strong> ${booking.room.hotel.fitnessCentre}</p>
                            <p><strong><spring:message code="booking.details.page.wifiFacility" />:</strong> ${booking.room.hotel.wifiFacility}</p>
                            <c:if test="${booking.room.hotel.rating == null}">
                                <p class="hotel-rating"><spring:message code="booking.details.page.noRating" /></p>
                            </c:if>
                            <c:if test="${booking.room.hotel.rating != null}">
                                <p class="hotel-rating"><spring:message code="booking.details.page.rating" />
                                    <fmt:formatNumber maxFractionDigits="1" value="${booking.room.hotel.rating.rating}"/>
                                    <spring:message code="booking.details.page.ratingOutOf" /></p>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="custom-button-container">
            <a href="#">
                <button type="submit" class="btn btn-primary"><spring:message code="booking.details.page.update" /></button>
            </a>
            <a href="/booking/${booking.id}" class="btn btn-primary"><spring:message code="booking.details.page.cancel" /></a>
        </div>
    </div>
</form:form>

<script src="/assets/js/downloadBookingDetails.js"></script>

</body>
</html>
