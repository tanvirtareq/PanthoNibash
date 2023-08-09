<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>add room</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/signupPage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customStyle.css">

</head>
<body>

<div class="signup-container custom-card">
    <h2 class="signup-title">Edit room</h2>
    <form:form id="addRoomForm" action="${pageContext.request.contextPath}/room/${room.id}/edit" method="post" modelAttribute="room">

        <div class="form-group">
            <label for="type">Type:</label>
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
            <label for="price">Price:</label>
            <form:input type="number" class="form-control" path="price"/>
            <form:errors path="price" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>

        <div class="form-group">
            <label for="numberOfBed">Number of bed:</label>
            <form:input type="number" class="form-control" path="numberOfBed"/>
            <form:errors path="numberOfBed" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <button type="submit" class="btn btn-primary signup-button">Update</button>
    </form:form>
</div>

</body>
</html>
