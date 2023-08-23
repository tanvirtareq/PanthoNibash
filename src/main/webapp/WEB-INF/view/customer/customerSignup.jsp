<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="customer.signup.title" /></title>
    <link rel="stylesheet" href="/assets/css/signupPage.css">
</head>
<body>

<div class="signup-container">
    <h2 class="signup-title"><spring:message code="customer.signup.header" /></h2>
    <form:form action="/customer/signup" method="post" modelAttribute="customer">
        <div class="form-group">
            <spring:message code="customer.signup.name.placeholder" var="customerNameplaceholder"/>
            <form:input type="text" class="form-control" path="name" placeholder="${customerNameplaceholder}"/>
            <form:errors path="name" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <spring:message code="customer.signup.email.placeholder" var="customerEmailPlaceholder"/>
            <form:input type="email" class="form-control" path="email" placeholder="${customerEmailPlaceholder}" required="true"/>
            <form:errors path="email" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <spring:message code="customer.signup.password.placeholder" var="customerPasswordPlaceholder"/>
            <form:input type="password" class="form-control" path="password" placeholder="${customerPasswordPlaceholder}" required="true"
                        cssStyle="margin-bottom: 10px;"
            />
            <form:errors path="password" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <spring:message code="customer.signup.phoneNumber.placeholder" var="customerPhoneNumberPlaceholder"/>
            <form:input type="text" class="form-control" path="phoneNumber" placeholder="${customerPhoneNumberPlaceholder}" required="true"/>
            <form:errors path="phoneNumber" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="dateOfBirth"><spring:message code="customer.signup.dateOfBirth.label" /></label>
            <form:input type="date" class="form-control" path="dateOfBirth" required="true"/>
            <form:errors path="dateOfBirth" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <button type="submit" class="btn btn-primary signup-button"><spring:message code="customer.signup.submit" /></button>
    </form:form>
    <p class="signup-footer">
        <spring:message code="customer.signup.alreadyHaveAccount" /> <a href="/customer/login"><spring:message code="customer.signup.loginLink" /></a>
    </p>
</div>

</body>
</html>
