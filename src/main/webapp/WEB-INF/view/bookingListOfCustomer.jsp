<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/23/23
  Time: 11:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Customer Booking List</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      line-height: 1.6;
      background-color: #f9f9f9;
    }

    header {
      background-color: rgba(228, 234, 226, 0.55);
      color: #350505;
      text-align: center;
      padding: 1rem;
      margin-bottom: 20px;
      text-transform: uppercase;
    }

    .custom-card {
      background-color: #ffffff;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      padding: 20px;
    }


    .container {
      max-width: 1200px;
      margin: 0 auto;
      padding: 1rem;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 2rem;
    }

    th, td {
      padding: 0.8rem;
      text-align: left;
    }

    .first-letter-capital {
      text-transform: capitalize;
    }

    th {
      background-color: #333;
      color: #fff;
    }

    tr:nth-child(even) {
      background-color: #f2f2f2;
    }

    .customer-image, .room-image {
      max-width: 50px;
      height: auto;
    }

    .filter-form {
      margin-top: 2rem;
      display: flex;
      flex-wrap: wrap;
      gap: 1rem;
    }

    .filter-form label {
      margin-right: 0.5rem;
    }

    .filter-form input[type="date"], .filter-form input[type="text"] {
      padding: 0.5rem;
      border-radius: 5px;
      border: 1px solid #ccc;
    }

    .filter-form input[type="submit"] {
      padding: 0.5rem 1rem;
      border-radius: 5px;
      background-color: #333;
      color: #fff;
      border: none;
      cursor: pointer;
    }

    .filter-form input[type="submit"]:hover {
      background-color: #555;
    }

    th {
      padding: 0.8rem;
      text-align: left;
      background-color: #f2f2f2;
      color: #333;
      font-weight: bold;
      cursor: pointer;
      position: relative;
    }

    th a {
      color: #333;
      text-decoration: none;
    }

    /* Arrow icons for sorting */
    .arrow-up,
    .arrow-down {
      display: inline-block;
      position: absolute;
      right: 0px;
      top: 50%;
      transform: translateY(-50%);
      width: 0;
      height: 0;
      border-left: 6px solid transparent;
      border-right: 6px solid transparent;
    }

    /*.arrow-up {*/
    /*  border-bottom: 10px solid #333;*/
    /*}*/

    /*.arrow-down {*/
    /*  border-top: 6px solid #333;*/
    /*}*/
  </style>
</head>
<body>
<header>
  <h1>Customer's Booking List - ${customer  .name}</h1>
</header>

<div class="container-xxl">
  <div class="row">
    <div class="col-md-2">
      <div class="container">
        <div class="card custom-card">
          <div class="card-body">
            <form action="/customer/${customer.id}/bookingList" method="GET">
              <div class="mb-3">
                <label class="form-label" for="checkInDate">Check-in Date:</label>
                <input type="date" class="form-control" id="checkInDate" name="checkInDate"
                       placeholder="Check-in Date">
              </div>

              <div class="mb-3">
                <label class="form-label" for="checkOutDate">Check-out Date:</label>
                <input type="date" class="form-control" id="checkOutDate" name="checkOutDate"
                       placeholder="Check-out Date">
              </div>

              <div class="mb-3">
                <label class="form-label" for="hotelName">Hotel Name:</label>
                <input type="text" class="form-control" id="hotelName" name="hotelName"
                       placeholder="Hotel Name">
              </div>

              <div class="mb-3">
                <label class="form-label">Room type:</label>
                <c:forEach items="${roomTypeOptions}" var="option">
                  <div class="form-check">
                    <input class="form-check-input" type="radio" name="roomType"
                           id="roomType" value="${option.value}">
                    <label class="form-check-label" for="roomType">${option.value}</label>
                  </div>
                </c:forEach>
              </div>

              <button type="submit" class="btn btn-primary">Filter</button>
            </form>
          </div>

        </div>

      </div>

    </div>

    <div class="col-md-10">
      <div class="container-xxl">
        <table class="custom-card">
          <thead>
          <tr>
            <th>Guest Photo</th>
            <th>
              <a href="?sort=name">Guest Name</a>
              <div class="arrow-up"></div>
              <div class="arrow-down"></div>
            </th>
            <th>
              <a href="?sort=email">Guest Email</a>
              <div class="arrow-up"></div>
              <div class="arrow-down"></div>
            </th>
            <th>
              <a href="?sort=email">Guest Phone Number</a>
              <div class="arrow-up"></div>
              <div class="arrow-down"></div>
            </th>
            <th>
              <a href="?sort=email">Hotel Name</a>
              <div class="arrow-up"></div>
              <div class="arrow-down"></div>
            </th>
            <th>
              <a href="?sort=roomNumber">Room Number</a>
              <div class="arrow-up"></div>
              <div class="arrow-down"></div>
            </th>
            <th>
              <a href="?sort=checkInDate">Check-in Date</a>
              <div class="arrow-up"></div>
              <div class="arrow-down"></div>
            </th>
            <th>
              <a href="?sort=checkOutDate">Check-out Date</a>
              <div class="arrow-up"></div>
              <div class="arrow-down"></div>
            </th>
            <th>
              <a href="?sort=roomType">Room Type</a>
              <div class="arrow-up"></div>
              <div class="arrow-down"></div>
            </th>
            <th>Booking Details</th>

          </tr>
          </thead>
          <tbody>
          <c:forEach items="${bookingList}" var="booking">
            <tr>
              <td>
                <img src="data:image/jpeg;base64,${booking.guestImageBase64Image}" alt="Guest"
                     class="customer-image">
              </td>
              <td class="first-letter-capital">${booking.guestName}</td>
              <td>${booking.guestEmail}</td>
              <td>${booking.guestPhoneNumber}</td>
              <td class="first-letter-capital">${booking.room.hotel.name}</td>
              <td>${booking.roomNumber}</td>
              <td>${booking.checkInDate}</td>
              <td>${booking.checkOutDate}</td>
              <td class="first-letter-capital">${booking.room.type}</td>
              <td>
                <a href="/booking/${booking.id}" class="btn btn-primary">Details</a>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

</body>
</html>
