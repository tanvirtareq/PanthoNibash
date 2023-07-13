<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/13/23
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Page</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="customer-container">
    <h2 class="customer-title">Customer Profile</h2>
    <div class="customer-details">
        <h4>Name: ${customer.name}</h4>
        <h4>Email: ${customer.email}</h4>
        <h4>Phone Number: ${customer.phoneNumber}</h4>
        <h4>Date of Birth: ${customer.dateOfBirth}</h4>
        <h4>Profile Picture:</h4>
        <img src="data:image/jpeg;base64,${customer.profilePicBase64Image}" alt="${customer.name}">

    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
