<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Hotel Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/loginPage.css"/>
</head>
<body>
<div class="login-container">
    <h2 class="login-title">Login</h2>
    <form:form action="${pageContext.request.contextPath}/hotel/login" method="post" modelAttribute="loginForm">
        <div class="form-group">
            <form:errors cssClass="alert alert-danger"/>
        </div>
        <div class="form-group">
            <form:input type="text" class="form-control" path="email" placeholder="Email"/>
            <form:errors path="email" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <form:input type="password" class="form-control" path="password" placeholder="Password"/>
            <form:errors path="password" cssClass="error-message"/>
        </div>
        <button type="submit" class="btn btn-primary login-button">Login</button>
    </form:form>
    <p class="login-footer">
        Don't have an account? <a href="${pageContext.request.contextPath}/hotel/signup">Sign up</a>
    </p>

</div>
</body>
</html>
