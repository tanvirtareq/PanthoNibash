<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/22/23
  Time: 2:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Hotel Booking List</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      line-height: 1.6;
      margin: 0;
      padding: 0;
    }

    header {
      background-color: #333;
      color: #fff;
      text-align: center;
      padding: 1rem;
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
  <h1>Hotel Booking List - ${hotel.name}</h1>
</header>

<div class="container-xxl">
  <div class="row">
    <div class="col-md-3">
      <div class="container">
        <div class="card">
          <div class="card-body">
            <form action="/hotel/${hotel.id}/booking/list" method="GET">
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
                <label class="form-label" for="roomNumber">Room Number:</label>
                <input type="text" class="form-control" id="roomNumber" name="roomNumber"
                       placeholder="Room Number">
              </div>

              <div class="mb-3">
                <label class="form-label" for="customerName">Customer Name:</label>
                <input type="text" class="form-control" id="customerName" name="customerName"
                       placeholder="Customer Name">
              </div>

              <div class="mb-3">
                <label class="form-label" for="customerEmail">Customer Email:</label>
                <input type="text" class="form-control" id="customerEmail" name="customerEmail"
                       placeholder="Customer Email">
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

    <div class="col-md-9">
      <div class="container">
        <table>
          <thead>
          <tr>
            <th>Customer Picture</th>
            <th>
              <a href="?sort=name">Customer Name</a>
              <div class="arrow-up"></div>
              <div class="arrow-down"></div>
            </th>
            <th>
              <a href="?sort=email">Customer Email</a>
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

            <th>Room Image</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${bookingList}" var="booking">
            <tr>
              <td>
                <img src="data:image/jpeg;base64,${booking.customer.profilePicBase64Image}" alt="Customer"
                     class="customer-image">
              </td>
              <td>${booking.customer.name}</td>
              <td>${booking.customer.email}</td>
              <td>${booking.roomNumber}</td>
              <td>${booking.checkInDate}</td>
              <td>${booking.checkOutDate}</td>
              <td>${booking.room.type}</td>
              <td>
                <img src="data:image/jpeg;base64,${booking.room.roomImageBase64Image}" alt="Room Image"
                     class="room-image">
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>

    </div>

  </div>
</div>


<!-- Add filtering options -->
</body>
</html>