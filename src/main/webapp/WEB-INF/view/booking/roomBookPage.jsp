<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="room.booking.title"/></title>
    <link rel="stylesheet" href="/assets/css/customStyle.css">
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body custom-card">
                    <h2 class="card-title mb-4"><spring:message code="room.booking.heading"/></h2>
                    <form:form action="/room/${roomId}/book" modelAttribute="booking"
                               method="post">
                        <div class="mb-3">
                            <label class="form-label"><spring:message code="guest.name.label"/></label>
                            <form:input path="guestName" class="form-control"/>
                            <form:errors path="guestName" cssClass="text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label"><spring:message code="guest.email.label"/></label>
                            <form:input path="guestEmail" class="form-control"/>
                            <form:errors path="guestEmail" cssClass="text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label"><spring:message code="guest.phone.label"/></label>
                            <form:input path="guestPhoneNumber" class="form-control"/>
                            <form:errors path="guestPhoneNumber" cssClass="text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label"><spring:message code="checkin.date.label"/></label>
                            <form:input path="checkInDate" id="checkInDate" type="date" class="form-control"
                                        onchange="updateTotalCost()"/>
                            <form:errors path="checkInDate" cssClass="text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label"><spring:message code="checkout.date.label"/></label>
                            <form:input path="checkOutDate" type="date" id="checkOutDate" class="form-control"
                                        onchange="updateTotalCost()"/>
                            <form:errors path="checkOutDate" cssClass="text-danger"/>
                        </div>

                        <div class="mb-3">
                            <label class="form-label"><spring:message code="room.price.label"/></label>
                            <label class="form-label" id="roomPrice">${roomPrice}</label>
                        </div>

                        <div class="mb-3">
                            <label class="form-label"><spring:message code="total.cost.label"/></label>
                            <label class="form-label" id="totalCost"></label>
                        </div>
                        <button type="submit" class="btn btn-primary"><spring:message code="book.now.button"/></button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/assets/js/roomBook.js"></script>

</body>
</html>
