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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customStyle.css">
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body custom-card">
                    <h2 class="card-title mb-4">Room Booking</h2>
                    <form:form action="${pageContext.request.contextPath}/room/${roomId}/book" modelAttribute="booking" method="post">
                        <div class="mb-3">
                            <label class="form-label">Guest Name:</label>
                            <form:input path="guestName" class="form-control"/>
                            <form:errors path="guestName" cssClass="text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Guest Email:</label>
                            <form:input path="guestEmail" class="form-control"/>
                            <form:errors path="guestEmail" cssClass="text-danger"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Guest Phone Number:</label>
                            <form:input path="guestPhoneNumber" class="form-control"/>
                            <form:errors path="guestPhoneNumber" cssClass="text-danger"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Check-In Date:</label>
                            <form:input path="checkInDate" id="checkInDate" type="date" class="form-control"
                                        onchange="updateTotalCost()"/>
                            <form:errors path="checkInDate" cssClass="text-danger"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Check-Out Date:</label>
                            <form:input path="checkOutDate" type="date" id="checkOutDate" class="form-control"
                                        onchange="updateTotalCost()"/>
                            <form:errors path="checkOutDate" cssClass="text-danger"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Room Price:</label>
                            <label class="form-label" id="roomPrice">${roomPrice}</label>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Total Cost:</label>
                            <label class="form-label" id="totalCost"></label>
                        </div>
                        <button type="submit" class="btn btn-primary">Book Now</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/roomBook.js"></script>

</body>
</html>