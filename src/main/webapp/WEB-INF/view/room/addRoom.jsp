<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="addroom.page.title" var="pageTitle"/></title>
    <link rel="stylesheet" href="/assets/css/signupPage.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/customStyle.css">
</head>
<body>

<div class="signup-container custom-card">
    <h2 class="signup-title"><spring:message code="addroom.page.heading" var="pageHeading"/></h2>
    <form:form id="addRoomForm" action="/hotel/${hotelId}/addRoom" method="post"
               modelAttribute="room">
        <div class="form-group">
            <label for="type"><spring:message code="addroom.page.type.label"/></label>
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
        </div>
        <div class="form-group">
            <label for="price"><spring:message code="addroom.page.price.label"/></label>
            <form:input type="number" class="form-control" path="price"/>
            <form:errors path="price" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="numberOfBed"><spring:message code="addroom.page.bed.label"/></label>
            <form:input type="number" class="form-control" path="numberOfBed"/>
            <form:errors path="numberOfBed" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="roomNumbers"><spring:message code="addroom.page.room.number.label"/></label>
            <div class="input-group">
                <spring:message code='addroom.page.room.number.placeholder' var="rommNumberPlaceHolder"/>
                <input type="number" class="form-control" id="roomNumberInput" placeholder="${rommNumberPlaceHolder}"/>
                <div class="input-group-append">
                    <button type="button" class="btn btn-primary" id="addRoomNumberButton"><spring:message code='addroom.page.room.number.button'/></button>
                </div>
            </div>
        </div>
        <div class="chips-container" id="roomNumberChipsContainer"></div>
        <form:hidden path="roomNumbers" id="roomNumbersHidden"/>
        <form:errors path="roomNumbers" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        <button type="submit" class="btn btn-primary signup-button"><spring:message code='addroom.page.submit.button'/></button>
    </form:form>
</div>

<script src="/assets/js/addRoom.js"></script>

</body>
</html>
