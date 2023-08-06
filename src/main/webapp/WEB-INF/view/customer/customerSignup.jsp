<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/13/23
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Signup</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/signupPage.css">
</head>
<body>
<div class="signup-container">
    <h2 class="signup-title">Signup</h2>
    <form:form action="${pageContext.request.contextPath}/customer/signup" method="post" modelAttribute="customer">
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
            <label for="dateOfBirth">Date of Birth</label>
            <form:input type="date" class="form-control" path="dateOfBirth" required="true"/>
            <form:errors path="dateOfBirth" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <button type="submit" class="btn btn-primary signup-button">Submit</button>
    </form:form>
    <p class="signup-footer">
        Already have an account? <a href="${pageContext.request.contextPath}/customer/login">Login</a>
    </p>
</div>

</body>
</html>
