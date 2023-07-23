<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/19/23
  Time: 3:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Room Search</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/customStyle.css">
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 ml-auto">
            <div class="custom-card">
                <div class="card-body">
                    <h2 class="card-title">Room Search</h2>
                    <form action="/search" method="get">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label class="form-label">Parking Facility:</label>
                                        <c:forEach items="${facilityOptions}" var="option">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="parkingFacility"
                                                       id="parkingFacility" value="${option.value}">
                                                <label class="form-check-label"
                                                       for="parkingFacility">${option.value}</label>
                                            </div>
                                        </c:forEach>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label">Wi-Fi Facility:</label>
                                        <c:forEach items="${facilityOptions}" var="option">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="wifiFacility"
                                                       id="wifiFacility" value="${option.value}">
                                                <label class="form-check-label"
                                                       for="wifiFacility">${option.value}</label>
                                            </div>
                                        </c:forEach>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label">Swimming Pool:</label>
                                        <c:forEach items="${facilityOptions}" var="option">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="swimmingPool"
                                                       id="swimmingPool" value="${option.value}">
                                                <label class="form-check-label"
                                                       for="swimmingPool">${option.value}</label>
                                            </div>
                                        </c:forEach>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label">Fitness Centre:</label>
                                        <c:forEach items="${facilityOptions}" var="option">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="fitnessCentre"
                                                       id="fitnessCentre" value="${option.value}">
                                                <label class="form-check-label"
                                                       for="fitnessCentre">${option.value}</label>
                                            </div>
                                        </c:forEach>
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

                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label class="form-label" for="hotelName">Hotel name:</label>
                                        <input type="text" class="form-control" id="hotelName" name="hotelName"
                                               placeholder="Hotel name">
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label" for="location">Location:</label>
                                        <input type="text" class="form-control" id="location" name="location"
                                               placeholder="Location">
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label" for="priceMin">Price Range:</label>
                                        <div class="input-group">
                                            <span class="input-group-text">$</span>
                                            <input type="number" class="form-control" id="priceMin" name="priceMin"
                                                   placeholder="Min Price">
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <div class="input-group">
                                            <span class="input-group-text">$</span>
                                            <input type="number" class="form-control" id="priceMax" name="priceMax"
                                                   placeholder="Max Price">
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label" for="numberOfBed">Number of Beds:</label>
                                        <input type="number" class="form-control" id="numberOfBed" name="numberOfBed"
                                               placeholder="Number of Beds">
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label" for="checkIn">Check-In Date:</label>
                                        <input type="date" class="form-control" id="checkIn" name="checkIn">
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label" for="checkOut">Check-Out Date:</label>
                                        <input type="date" class="form-control" id="checkOut" name="checkOut">
                                    </div>

                                </div>

                            </div>
                        </div>

                        <button type="submit" class="btn btn-primary">Search</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <div class="custom-card">
                <div class="card-body" style="height: 800px; overflow-y: auto;">
                    <h3 class="card-title">Search Results:</h3>

                    <c:if test="${not empty searchResults}">
                        <c:forEach items="${searchResults}" var="room">

                            <div class="custom-card mb-3" style="max-width: 840px;">
                                <div class="row g-0">
                                    <div class="col-md-6">
                                        <a href="/room/${room.id}">
                                            <img
                                                    src="data:image/jpeg;base64,${room.roomImageBase64Image}"
                                                    alt="${room.hotel.name}"
                                                    height="250px"
                                                    width="400px"
                                                    class=" rounded-start img-search-card"

                                            />
                                        </a>

                                    </div>
                                    <div class="col-md-6">
                                        <div class="card-body">
                                            <h5 class="card-title">Hotel: <c:out value="${room.hotel.name}"/></h5>
                                            <p class="card-text">
                                                Room type: <c:out value="${room.type}"/>
                                                <br/>
                                                Location: <c:out value="${room.hotel.location}"/>
                                                <br/>
                                                Room price: <c:out value="${room.price}"/>
                                                <br/>
                                                Number of bed: <c:out value="${room.numberOfBed}"/>
                                                <br/>
                                                Parking Facility: <c:out value="${room.hotel.parkingFacility}"/>
                                                <br/>
                                                Wifi Facility: <c:out value="${room.hotel.wifiFacility}"/>
                                                <br/>
                                                Swimming Pool: <c:out value="${room.hotel.swimmingPool}"/>
                                                <br/>
                                                Fitness Centre: <c:out value="${room.hotel.fitnessCentre}"/>
                                            </p>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>

                    <c:if test="${empty searchResults}">
                        <p>No matching rooms found.</p>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>
