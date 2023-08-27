<%@ page contentType="text/html;charset=UTF-8"   %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/assets/css/customStyle.css">
    <link rel="stylesheet" href="/assets/css/customTable.css">
    <title><spring:message code="customer.bookinglist.page.title" arguments="${customer.name}" /></title>
</head>
<body>
<header class="custom-header">
    <h1><spring:message code="customer.bookinglist.page.header" arguments="${customer.name}" /></h1>
</header>

<div class="container-xxl">
    <div class="row">
        <div class="col-md-2">
            <div class="container">
                <div class="card custom-card">
                    <div class="card-body">
                        <form action="/customer/${customer.id}/bookingList" method="GET">
                            <div class="mb-3">
                                <label class="form-label" for="checkInDate"><spring:message code="customer.bookinglist.page.checkinDate" /></label>
                                <input type="date" class="form-control" id="checkInDate" name="checkInDate"
                                       placeholder="<spring:message code="customer.bookinglist.page.checkinDate.placeholder" />">
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="checkOutDate"><spring:message code="customer.bookinglist.page.checkoutDate" /></label>
                                <input type="date" class="form-control" id="checkOutDate" name="checkOutDate"
                                       placeholder="<spring:message code="customer.bookinglist.page.checkoutDate.placeholder" />">
                            </div>
                            <div class="mb-3">
                                <label class="form-label" for="hotelName"><spring:message code="customer.bookinglist.page.hotelName" /></label>
                                <input type="text" class="form-control" id="hotelName" name="hotelName"
                                       placeholder="<spring:message code="customer.bookinglist.page.hotelName.placeholder" />">
                            </div>
                            <div class="mb-3">
                                <label class="form-label"><spring:message code="customer.bookinglist.page.roomType" /></label>
                                <c:forEach items="${roomTypeOptions}" var="option">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="roomType"
                                               id="roomType" value="${option.value}">
                                        <label class="form-check-label" for="roomType">${option.value}</label>
                                    </div>
                                </c:forEach>
                            </div>
                            <button type="submit" class="btn btn-primary"><spring:message code="customer.bookinglist.page.filter" /></button>
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
                        <th><spring:message code="customer.bookinglist.page.guestPhoto" /></th>
                        <th>
                            <spring:message code="customer.bookinglist.page.guestName" />
                        </th>
                        <th>
                            <spring:message code="customer.bookinglist.page.guestEmail" />
                        </th>
                        <th>
                            <spring:message code="customer.bookinglist.page.guestPhoneNumber" />
                        </th>
                        <th>
                            <spring:message code="customer.bookinglist.page.hotelName" />
                        </th>
                        <th>
                            <spring:message code="customer.bookinglist.page.roomNumber" />
                        </th>
                        <th>
                            <spring:message code="customer.bookinglist.page.checkinDate" />
                        </th>
                        <th>
                            <spring:message code="customer.bookinglist.page.checkoutDate" />
                        </th>
                        <th>
                            <spring:message code="customer.bookinglist.page.roomType" />
                        </th>
                        <th><spring:message code="customer.bookinglist.page.bookingDetails" /></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${bookingList}" var="booking">
                        <tr>
                            <td>
                                <img src="data:image/jpeg;base64,${booking.guestImageBase64Image}" alt="<spring:message code="customer.bookinglist.page.guestPhoto.alt" />"
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
                                <a href="/booking/${booking.id}" class="btn btn-primary"><spring:message code="customer.bookinglist.page.bookingDetails.button" /></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('#bookingTable').dataTable();
    });
</script>

</body>
</html>
