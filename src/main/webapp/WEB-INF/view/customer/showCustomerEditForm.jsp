<%@ page contentType="text/html;charset=UTF-8"   %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="customer.edit.title" /></title>
    <link rel="stylesheet" href="/assets/css/signupPage.css">
    <link rel="stylesheet" href="/assets/css/customStyle.css">
</head>
<body>

<div class="signup-container custom-card">
    <h2 class="signup-title"><spring:message code="customer.edit.header" /></h2>
    <form:form action="/customer/${customer.id}/edit" method="post" modelAttribute="customer">
        <div class="form-group">
            <label for="name"><spring:message code="customer.edit.nameLabel" /></label>
            <form:input type="text" class="form-control" path="name"/>
            <form:errors path="name" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="phoneNumber"><spring:message code="customer.edit.phoneNumberLabel" /></label>
            <form:input type="text" class="form-control" path="phoneNumber" required="true"/>
            <form:errors path="phoneNumber" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="dateOfBirth"><spring:message code="customer.edit.dateOfBirthLabel" /></label>
            <form:input type="date" class="form-control" path="dateOfBirth" required="true"/>
            <form:errors path="dateOfBirth" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <form:hidden path="id"/>
        <form:errors path="id"/>
        <form:hidden path="password"/>
        <form:errors path="password"/>
        <form:hidden path="email"/>
        <form:errors path="email"/>
        <form:hidden path="profilePicture"/>
        <form:errors path="profilePicture"/>
        <button type="submit" class="btn btn-primary signup-button"><spring:message code="customer.edit.submitButton" /></button>
    </form:form>
</div>

</body>
</html>
