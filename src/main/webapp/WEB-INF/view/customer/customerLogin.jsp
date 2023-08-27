<%@ page contentType="text/html;charset=UTF-8"   %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title><spring:message code="customer.login.page.title" /></title>
    <link rel="stylesheet" href="/assets/css/loginPage.css">
</head>
<body>

<div class="login-container">
    <h2 class="login-title"><spring:message code="customer.login.page.login" /></h2>
    <form:form action="/customer/login" method="post" modelAttribute="loginForm">
        <div class="form-group">
            <form:errors cssClass="alert alert-danger"/>
        </div>
        <div class="form-group">
            <spring:message code="customer.login.page.email" var="customerEmailPlaceholder"/>
            <form:input type="text" class="form-control" path="email" placeholder="${customerEmailPlaceholder}"/>
            <form:errors path="email" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <spring:message code="customer.login.page.password" var="customerPasswordPlaceholder"/>
            <form:input type="password" class="form-control" path="password" placeholder="${customerPasswordPlaceholder}"/>
            <form:errors path="password" cssClass="error-message"/>
        </div>
        <button type="submit" class="btn btn-primary login-button"><spring:message code="customer.login.page.loginButton" /></button>
    </form:form>
    <p class="login-footer">
        <spring:message code="customer.login.page.noAccount" /> <a href="/customer/signup"><spring:message code="customer.login.page.signUp" /></a>
    </p>
</div>

</body>
</html>
