<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/2/23
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup Page - Step 1</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/signupPage.css">
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="signup-container">
    <h2 class="signup-title">Signup</h2>
    <form:form action="/signup" method="post" modelAttribute="trainee">
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
        <button type="submit" class="btn btn-primary signup-button">Next</button>
    </form:form>
    <p class="signup-footer">Already have an account? <a href="/login">Login</a></p>
</div>
<%--<jsp:include page="clock.jsp"/>--%>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

</body>
</html>
