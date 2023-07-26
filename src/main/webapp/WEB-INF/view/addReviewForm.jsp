<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/23/23
  Time: 9:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Review</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/signupPage.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            background-color: #f9f9f9;
            padding-top: 20px;
        }

        .custom-card {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 40px;
        }

    </style>
</head>
<body>
<div class="signup-container custom-card">
    <h2 class="signup-title">Review</h2>
    <form:form action="/customer/${customerId}/booking/${bookingId}/addReview" method="post" modelAttribute="review">
        <div class="form-group">
            <label for="review">Review:</label>
            <form:textarea class="form-control" path="review" placeholder="Review"/>
            <form:errors path="review" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>

        <div class="form-group">
            <label for="rating">Rating (Out of 5) :</label>
            <form:input type="number" class="form-control" path="rating"/>
            <form:errors path="rating" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <button type="submit" class="btn btn-primary signup-button">Submit</button>
    </form:form>
</div>

</body>
</html>
