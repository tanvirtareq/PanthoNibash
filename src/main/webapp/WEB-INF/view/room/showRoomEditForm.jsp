<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="editroom.page.title"/></title>
    <link rel="stylesheet" href="/assets/css/signupPage.css">
    <link rel="stylesheet" href="/assets/css/customStyle.css">
</head>
<body>
<div class="signup-container custom-card">
    <h2 class="signup-title"><spring:message code="editroom.page.heading"/></h2>
    <form:form id="editRoomForm" action="/room/${room.id}/edit" method="post" modelAttribute="room">
        <div class="form-group">
            <label for="type"><spring:message code="editroom.page.type"/></label>
            <c:forEach items="${roomTypeOptions}" var="roomTypeOption">
                <div class="form-check">
                    <form:radiobutton class="form-check-input" id="${roomTypeOption.value}" path="type" value="${roomTypeOption.value}"/>
                    <label class="form-check-label" for="${roomTypeOption.value}">
                            ${roomTypeOption.value}
                    </label>
                </div>
            </c:forEach>
            <form:errors path="type" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="price"><spring:message code="editroom.page.price"/></label>
            <form:input type="number" class="form-control" path="price"/>
            <form:errors path="price" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="numberOfBed"><spring:message code="editroom.page.bed.number"/></label>
            <form:input type="number" class="form-control" path="numberOfBed"/>
            <form:errors path="numberOfBed" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <button type="submit" class="btn btn-primary signup-button"><spring:message code="editroom.page.update"/></button>
    </form:form>
</div>
</body>
</html>
