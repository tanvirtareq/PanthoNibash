<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="page.title" /></title>
    <link rel="stylesheet" href="/assets/css/loginPage.css"/>
</head>
<body>

<div class="login-container">
    <h2 class="login-title"><spring:message code="login.hotel.title" /></h2>
    <form:form action="/hotel/login" method="post" modelAttribute="loginForm">
        <div class="form-group">
            <form:errors cssClass="alert alert-danger"/>
        </div>
        <div class="form-group">
            <spring:message code="login.email.placeholder" var="emailPlaceholder"/>
            <form:input type="text" class="form-control" path="email" placeholder="${emailPlaceholder}"/>
            <form:errors path="email" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <spring:message code="login.password.placeholder" var="${passwordPlaceholder}"/>
            <form:input type="password" class="form-control" path="password" placeholder="${passwordPlaceholder}"/>
            <form:errors path="password" cssClass="error-message"/>
        </div>
        <button type="submit" class="btn btn-primary login-button"><spring:message code="login.button" /></button>
    </form:form>
    <p class="login-footer">
        <spring:message code="login.noAccount" /><a href="/hotel/signup"><spring:message code="login.signUpLink" /></a>
    </p>
</div>

</body>
</html>
