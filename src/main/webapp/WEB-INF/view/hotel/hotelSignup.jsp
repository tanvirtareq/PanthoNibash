<%@ page contentType="text/html;charset=UTF-8"   %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="page.title"/></title>
    <link rel="stylesheet" href="/assets/css/signupPage.css">
</head>
<body>

<div class="signup-container">
    <h2 class="signup-title"><spring:message code="signup.title.hotel"/></h2>
    <form:form action="/hotel/signup" method="post" modelAttribute="hotel">
        <div class="form-group">
            <spring:message code="signup.placeholder.name" var="namePlaceholder" />
            <form:input type="text" class="form-control" path="name" placeholder="${namePlaceholder}"/>
            <form:errors path="name" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <spring:message code="signup.placeholder.email" var="emailPlaceholder" />
            <form:input type="email" class="form-control" path="email" placeholder="${emailPlaceholder}" required="true"/>
            <form:errors path="email" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <spring:message code="signup.placeholder.password" var="passwordPlaceholder" />
            <form:input type="password" class="form-control" path="password" placeholder="${passwordPlaceholder}" required="true"
                        cssStyle="margin-bottom: 10px;"/>
            <form:errors path="password" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <spring:message code="signup.placeholder.phoneNumber" var="phoneNumberPlaceholder" />
            <form:input type="text" class="form-control" path="phoneNumber" placeholder="${phoneNumberPlaceholder}" required="true"/>
            <form:errors path="phoneNumber" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <spring:message code="signup.placeholder.location" var="locationPlaceholder" />
            <form:input type="text" class="form-control" path="location" placeholder="${locationPlaceholder}"/>
            <form:errors path="location" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="parkingFacility"><spring:message code='signup.label.parkingFacility'/></label>
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
            <label for="swimmingPool"><spring:message code='signup.label.swimmingPool'/></label>
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
            <label for="fitnessCentre"><spring:message code='signup.label.fitnessCentre'/></label>
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
            <label for="wifiFacility"><spring:message code='signup.label.wifiFacility'/></label>
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
        <button type="submit" class="btn btn-primary signup-button"><spring:message code='signup.button.submit'/></button>
    </form:form>
    <p class="signup-footer">
        <spring:message code='signup.footer.haveAccount'/>
        <a href="/hotel/login"><spring:message code='signup.footer.login'/></a>
    </p>
</div>

</body>
</html>
