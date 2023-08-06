<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/16/23
  Time: 1:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/signupPage.css">
</head>
<body>

<div class="signup-container">
    <h2 class="signup-title">Signup as Hotel</h2>
    <form:form action="${pageContext.request.contextPath}/hotel/signup" method="post" modelAttribute="hotel">
        <div class="form-group">
            <form:input type="text" class="form-control" path="name" placeholder="Name"/>
            <form:errors path="name" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <form:input type="email" class="form-control" path="email" placeholder="Email" required="true"/>
            <form:errors path="email" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <form:input type="password" class="form-control" path="password" placeholder="Password" required="true"
                        cssStyle="margin-bottom: 10px;"
            />
            <form:errors path="password" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <form:input type="text" class="form-control" path="phoneNumber" placeholder="Phone Number" required="true"/>
            <form:errors path="phoneNumber" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <form:input type="text" class="form-control" path="location" placeholder="Location"/>
            <form:errors path="location" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="parkingFacility">Parking Facility:</label>
            <c:forEach items="${facilityOptions}" var="facilityOption">
                <div class="form-check">
                    <form:radiobutton class="form-check-input" id="${facilityOption.value}"
                                      path="parkingFacility" value="${facilityOption.value}"/>
                    <label class="form-check-label" for="${facilityOption.value}">
                            ${facilityOption.value}
                    </label>
                </div>
            </c:forEach>
            <form:errors path="parkingFacility" cssClass="alert alert-danger mt-3"
                         cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="swimmingPool">Swimming Pool facility:</label>
            <c:forEach items="${facilityOptions}" var="facilityOption">
                <div class="form-check">
                    <form:radiobutton class="form-check-input" id="${facilityOption.value}"
                                      path="swimmingPool" value="${facilityOption.value}"/>
                    <label class="form-check-label" for="${facilityOption.value}">
                            ${facilityOption.value}
                    </label>
                </div>
            </c:forEach>
            <form:errors path="swimmingPool" cssClass="alert alert-danger mt-3"
                         cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="fitnessCentre">Fitness Centre:</label>
            <c:forEach items="${facilityOptions}" var="facilityOption">
                <div class="form-check">
                    <form:radiobutton class="form-check-input" id="${facilityOption.value}"
                                      path="fitnessCentre" value="${facilityOption.value}"/>
                    <label class="form-check-label" for="${facilityOption.value}">
                            ${facilityOption.value}
                    </label>
                </div>
            </c:forEach>
            <form:errors path="fitnessCentre" cssClass="alert alert-danger mt-3"
                         cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="wifiFacility">Wifi facility:</label>
            <c:forEach items="${facilityOptions}" var="facilityOption">
                <div class="form-check">
                    <form:radiobutton class="form-check-input" id="${facilityOption.value}"
                                      path="wifiFacility" value="${facilityOption.value}"/>
                    <label class="form-check-label" for="${facilityOption.value}">
                            ${facilityOption.value}
                    </label>
                </div>
            </c:forEach>
            <form:errors path="wifiFacility" cssClass="alert alert-danger mt-3"
                         cssStyle="padding: 3px;"/>
        </div>
        <button type="submit" class="btn btn-primary signup-button">Submit</button>
    </form:form>
    <p class="signup-footer">
        Already have an account? <a href="${pageContext.request.contextPath}/hotel/login">Login</a>
    </p>
</div>

</body>
</html>
