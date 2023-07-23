<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/23/23
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Customer Signup</title>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/signupPage.css">
  <style>
    .custom-card {
      background-color: #ffffff;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      padding: 20px;
    }
  </style>
</head>
<body>
<div class="signup-container custom-card">
  <h2 class="signup-title">Signup</h2>
  <form:form action="/customer/${customer.id}/edit" method="post" modelAttribute="customer">
    <div class="form-group">
      <form:input type="text" class="form-control" path="name" placeholder="Name"/>
      <form:errors path="name" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
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

    <form:hidden path="id"/>
    <button type="submit" class="btn btn-primary signup-button">Submit</button>
  </form:form>
</div>


<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>