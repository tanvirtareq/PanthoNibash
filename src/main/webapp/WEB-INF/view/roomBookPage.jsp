<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/20/23
  Time: 12:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Room Booking</title>
</head>
<body>

<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card">
        <div class="card-body">
          <h2 class="card-title mb-4">Room Booking</h2>
          <form:form action="/room/${roomId}/book" modelAttribute="booking" method="post">
            <div class="mb-3">
              <label class="form-label">Guest Name:</label>
              <form:input path="guestName" class="form-control" />
              <form:errors path="guestName" cssClass="text-danger" />
            </div>

            <div class="mb-3">
              <label class="form-label">Guest Email:</label>
              <form:input path="guestEmail" class="form-control" />
              <form:errors path="guestEmail" cssClass="text-danger" />
            </div>
            <div class="mb-3">
              <label class="form-label">Guest Phone Number:</label>
              <form:input path="guestPhoneNumber" class="form-control" />
              <form:errors path="guestPhoneNumber" cssClass="text-danger" />
            </div>
            <div class="mb-3">
              <label class="form-label">Check-In Date:</label>
              <form:input path="checkInDate" type="date" class="form-control" />
              <form:errors path="checkInDate" cssClass="text-danger" />
            </div>
            <div class="mb-3">
              <label class="form-label">Check-Out Date:</label>
              <form:input path="checkOutDate" type="date" class="form-control" />
              <form:errors path="checkOutDate" cssClass="text-danger" />
            </div>
            <button type="submit" class="btn btn-primary">Book Now</button>
          </form:form>
        </div>
      </div>
    </div>
  </div>
</div>


</body>
</html>