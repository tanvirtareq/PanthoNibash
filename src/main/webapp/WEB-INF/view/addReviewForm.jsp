<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Add Review</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/signupPage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customStyle.css">

</head>
<body>
<div class="signup-container custom-card">
    <h2 class="signup-title">Review</h2>
    <form:form action="${pageContext.request.contextPath}/customer/${customerId}/booking/${bookingId}/addreview" method="post" modelAttribute="review">
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
