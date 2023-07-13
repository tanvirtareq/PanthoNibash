<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/loginPage.css">
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="login-container">
    <h2 class="login-title">Login</h2>
    <form:form action="/login" method="post" modelAttribute="loginForm">
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
    <p class="login-footer">Don't have an account? <a href="/signup">Sign up</a></p>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
</div>

<jsp:include page="clock.jsp"/>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
