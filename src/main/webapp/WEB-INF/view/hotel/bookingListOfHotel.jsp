<%@ page contentType="text/html;charset=UTF-8"   %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title><spring:message code="hotel.bookingList.title" /></title>
  <link rel="stylesheet" href="/assets/css/customStyle.css">
  <link rel="stylesheet" href="/assets/css/customTable.css">
</head>
<body>

<header class="custom-header">
  <h1><spring:message code="hotel.bookingList.header" /> - ${hotel.name}</h1>
</header>

<div class="container-xxl">
  <div class="row">
    <div class="col-md-3">
      <div class="container-xxl">
        <div class="card custom-card">
          <div class="card-body">
            <form action="/hotel/${hotel.id}/bookingList" method="GET">
              <div class="mb-3">
                <label class="form-label" for="checkInDate"><spring:message code="hotel.bookingList.checkInDate" /></label>
                <input type="date" class="form-control" id="checkInDate" name="checkInDate"
                       placeholder="<spring:message code="hotel.bookingList.checkInDate.placeholder" />">
              </div>
              <div class="mb-3">
                <label class="form-label" for="checkOutDate"><spring:message code="hotel.bookingList.checkOutDate" /></label>
                <input type="date" class="form-control" id="checkOutDate" name="checkOutDate"
                       placeholder="<spring:message code="hotel.bookingList.checkOutDate.placeholder" />">
              </div>
              <div class="mb-3">
                <label class="form-label" for="roomNumber"><spring:message code="hotel.bookingList.roomNumber" /></label>
                <input type="text" class="form-control" id="roomNumber" name="roomNumber"
                       placeholder="<spring:message code="hotel.bookingList.roomNumber.placeholder" />">
              </div>
              <div class="mb-3">
                <label class="form-label" for="customerName"><spring:message code="hotel.bookingList.customerName" /></label>
                <input type="text" class="form-control" id="customerName" name="customerName"
                       placeholder="<spring:message code="hotel.bookingList.customerName.placeholder" />">
              </div>
              <div class="mb-3">
                <label class="form-label" for="customerEmail"><spring:message code="hotel.bookingList.customerEmail" /></label>
                <input type="text" class="form-control" id="customerEmail" name="customerEmail"
                       placeholder="<spring:message code="hotel.bookingList.customerEmail.placeholder" />">
              </div>
              <div class="mb-3">
                <label class="form-label"><spring:message code="hotel.bookingList.roomType" /></label>
                <c:forEach items="${roomTypeOptions}" var="option">
                  <div class="form-check">
                    <input class="form-check-input" type="radio" name="roomType"
                           id="roomType" value="${option.value}">
                    <label class="form-check-label" for="roomType">${option.value}</label>
                  </div>
                </c:forEach>
              </div>
              <button type="submit" class="btn btn-primary"><spring:message code="hotel.bookingList.filterButton" /></button>
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
            <th><spring:message code="hotel.bookingList.guestPhoto" /></th>
            <th><spring:message code="hotel.bookingList.guestName" /></th>
            <th><spring:message code="hotel.bookingList.guestEmail" /></th>
            <th><spring:message code="hotel.bookingList.guestPhoneNumber" /></th>
            <th><spring:message code="hotel.bookingList.roomNumber" /></th>
            <th><spring:message code="hotel.bookingList.checkInDate" /></th>
            <th><spring:message code="hotel.bookingList.checkOutDate" /></th>
            <th><spring:message code="hotel.bookingList.roomType" /></th>
            <th><spring:message code="hotel.bookingList.bookingDetails" /></th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${bookingList}" var="booking">
            <tr>
              <td>
                <img src="data:image/jpeg;base64,${booking.guestImageBase64Image}" alt="<spring:message code="hotel.bookingList.guestImageAlt" />"
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
                <a href="/booking/${booking.id}" class="btn btn-primary"><spring:message code="hotel.bookingList.bookingDetailsButton" /></a>
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
