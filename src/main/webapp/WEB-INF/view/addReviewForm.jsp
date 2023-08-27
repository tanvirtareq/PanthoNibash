<%@ page contentType="text/html;charset=UTF-8"   %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title><spring:message code="addreview.page.title"/></title>
    <link rel="stylesheet" href="/assets/css/signupPage.css">
    <link rel="stylesheet" href="/assets/css/customStyle.css">
</head>
<body>

<div class="signup-container custom-card">
    <h2 class="signup-title"><spring:message code="addreview.page.heading"/></h2>
    <c:set var="reviewPlaceholder"><spring:message code="addreview.page.review.label"/></c:set>
    <c:set var="ratingPlaceholder"><spring:message code="addreview.page.rating.label"/></c:set>
    <form:form action="/booking/${bookingId}/addReview" method="post" modelAttribute="review">
        <div class="form-group">
            <label for="description"><spring:message code="addreview.page.review.label"/></label>
            <form:textarea class="form-control" path="description" placeholder="${reviewPlaceholder}"/>
            <form:errors path="description" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="rating"><spring:message code="addreview.page.rating.label"/></label>
            <form:input type="number" class="form-control" path="rating" placeholder="${ratingPlaceholder}"/>
            <form:errors path="rating" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <button type="submit" class="btn btn-primary signup-button"><spring:message code="addreview.page.submit.button"/></button>
    </form:form>
</div>

</body>
</html>
