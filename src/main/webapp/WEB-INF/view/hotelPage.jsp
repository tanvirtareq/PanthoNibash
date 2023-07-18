<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/18/23
  Time: 11:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Hotel Page</title>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
</head>
<body>
<div class="customer-container">
  <h2 class="customer-title">Hotel</h2>
  <div class="customer-details">
    <h4>Name: ${hotel.name}</h4>
    <h4>Email: ${hotel.email}</h4>
    <h4>Phone Number: ${hotel.phoneNumber}</h4>
    <h4>Hotel Picture:</h4>
    <img src="data:image/jpeg;base64,${hotel.hotelImageBase64Image}" alt="${hotel.name}">

  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
