<%@ page contentType="text/html;charset=UTF-8"   %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title><spring:message code="room.page.title"/></title>
    <link rel="stylesheet" href="/assets/css/roomPage.css">
    <link rel="stylesheet" href="/assets/css/customStyle.css">
    <link rel="stylesheet" href="/assets/css/signupPage.css">
</head>
<body>

<div class="container room-container">
    <h2><spring:message code="room.page.hotel.name"/>: <c:out value="${hotel.name}"/></h2>
    <p><spring:message code="room.page.location"/>: <c:out value="${hotel.location}"/></p>
    <form:form id="addRoomForm" action="/${link}" method="post" modelAttribute="room">
        <div class="row">
            <c:if test="${room.roomImage != null}">
                <div class="col-md-6">
                    <img src="data:image/jpeg;base64,${room.roomImageBase64Image}" class="room-image" alt="<spring:message code='room.page.room.image.alt'/>">
                </div>
            </c:if>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="type"><spring:message code="addroom.page.type.label"/></label>
                    <c:if test="${readOnly == true}">
                        <c:out value="${room.type}"/>
                    </c:if>
                    <c:if test="${readOnly == false}">
                        <c:forEach items="${roomTypeOptions}" var="roomTypeOption">
                            <div class="form-check">
                                <form:radiobutton class="form-check-input" id="${roomTypeOption.value}"
                                                  path="type" value="${roomTypeOption.value}"/>
                                <label class="form-check-label" for="${roomTypeOption.value}">
                                        ${roomTypeOption.value}
                                </label>
                            </div>
                        </c:forEach>
                        <form:errors path="type" cssClass="alert alert-danger mt-3"
                                     cssStyle="padding: 3px;"/>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="price"><spring:message code="addroom.page.price.label"/></label>
                    <c:if test="${readOnly == true}">
                        <c:out value="${room.price}"/>
                    </c:if>
                    <c:if test="${readOnly == false}">
                        <form:input type="number" class="form-control" path="price"/>
                        <form:errors path="price" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="numberOfBed"><spring:message code="addroom.page.bed.label"/></label>
                    <c:if test="${readOnly == true}">
                        <c:out value="${room.numberOfBed}"/>
                    </c:if>
                    <c:if test="${readOnly == false}">
                        <form:input type="number" class="form-control" path="numberOfBed"/>
                        <form:errors path="numberOfBed" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="roomNumbers"><spring:message code="addroom.page.room.number.label"/></label>
                    <c:if test="${readOnly == false}">
                        <div class="input-group">
                            <spring:message code='addroom.page.room.number.placeholder' var="rommNumberPlaceHolder"/>
                            <input type="number" class="form-control" id="roomNumberInput" placeholder="${rommNumberPlaceHolder}"/>
                            <div class="input-group-append">
                                <button type="button" class="btn btn-primary" id="addRoomNumberButton"><spring:message code='addroom.page.room.number.button'/></button>
                            </div>
                        </div>
                    </c:if>
                </div>
                <div class="chips-container" id="roomNumberChipsContainer"></div>
                <form:hidden path="roomNumbers" id="roomNumbersHidden"/>
                <form:errors path="roomNumbers" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
                <input type="hidden" id="readOnly" value="${readOnly}"/>
                <hr>
                <p><spring:message code="room.page.parking.facility"/>: <c:out value="${hotel.parkingFacility}"/></p>
                <p><spring:message code="room.page.swimming.pool"/>: <c:out value="${hotel.swimmingPool}"/></p>
                <p><spring:message code="room.page.fitness.centre"/>: <c:out value="${hotel.fitnessCentre}"/></p>
                <p><spring:message code="room.page.wifi.facility"/>: <c:out value="${hotel.wifiFacility}"/></p>
                <p><spring:message code="room.page.email"/>: <c:out value="${hotel.email}"/></p>
                <p><spring:message code="room.page.phone.number"/>: <c:out value="${hotel.phoneNumber}"/></p>
                <c:if test="${hotel.rating == null}">
                    <p class="hotel-rating"><spring:message code="room.page.no.rating"/></p>
                </c:if>
                <c:if test="${hotel.rating != null}">
                    <p class="hotel-rating"><spring:message code="room.page.rating"/>:
                        <fmt:formatNumber maxFractionDigits="1" value="${hotel.rating.rating}"/> <spring:message code="room.page.rating.out.of"/> 5
                    </p>
                </c:if>
            </div>
        </div>
        <c:if test="${readOnly == false}">
            <button type="submit" class="btn btn-primary signup-button"><spring:message code='addroom.page.submit.button'/></button>
        </c:if>
    </form:form>
    <c:if test="${readOnly == true}">
        <div class="custom-button-container">
            <a href="/room/${room.id}/book" style="text-decoration: none;">
                <button class="custom-general-button">
                    <spring:message code="room.page.book.now.button"/>
                </button>
            </a>
            <c:if test="${hasEditAccess == true}">
                <a href="/room/${room.id}/edit" style="text-decoration: none;">
                    <button class="custom-general-button">
                        <spring:message code="room.page.edit.button"/>
                    </button>
                </a>
            </c:if>
        </div>
        <hr>
        <h3><spring:message code="room.page.hotel.image"/></h3>
        <a href="/hotel/${room.hotel.id}">
            <img src="data:image/jpeg;base64,${room.hotel.hotelImageBase64Image}" class="hotel-image" alt="<spring:message code='room.page.hotel.image.alt'/>">
        </a>
    </c:if>
</div>

<script src="/assets/js/addRoom.js"></script>

</body>
</html>
