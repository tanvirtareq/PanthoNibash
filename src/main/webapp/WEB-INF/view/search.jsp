<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customStyle.css">
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 ml-auto">
            <div class="custom-card">
                <div class="card-body">
                    <h2 class="card-title">Room Search</h2>
                    <form:form action="${pageContext.request.contextPath}/search" method="get" modelAttribute="searchRoomFilter">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="parkingFacility">Parking Facility:</label>
                                        <c:forEach items="${facilityOptions}" var="facilityOption">
                                            <div class="form-check">
                                                <form:radiobutton class="form-check-input" id="${facilityOption.value}"
                                                                  path="parkingFacility" value="${facilityOption.value}"/>
                                                <label class="form-check-label" for="${facilityOption.value}">
                                                        ${facilityOption.value}
                                                </label>
                                            </div>
                                        </c:forEach>
                                    </div>

                                    <div class="mb-3">
                                        <label for="wifiFacility">Wifi facility:</label>
                                        <c:forEach items="${facilityOptions}" var="facilityOption">
                                            <div class="form-check">
                                                <form:radiobutton class="form-check-input" id="${facilityOption.value}"
                                                                  path="wifiFacility" value="${facilityOption.value}"/>
                                                <label class="form-check-label" for="${facilityOption.value}">
                                                        ${facilityOption.value}
                                                </label>
                                            </div>
                                        </c:forEach>
                                    </div>

                                    <div class="mb-3">
                                        <label for="swimmingPool">Swimming Pool facility:</label>
                                        <c:forEach items="${facilityOptions}" var="facilityOption">
                                            <div class="form-check">
                                                <form:radiobutton class="form-check-input" id="${facilityOption.value}"
                                                                  path="swimmingPool" value="${facilityOption.value}"/>
                                                <label class="form-check-label" for="${facilityOption.value}">
                                                        ${facilityOption.value}
                                                </label>
                                            </div>
                                        </c:forEach>
                                    </div>

                                    <div class="mb-3">
                                        <label for="fitnessCentre">Fitness Centre:</label>
                                        <c:forEach items="${facilityOptions}" var="facilityOption">
                                            <div class="form-check">
                                                <form:radiobutton class="form-check-input" id="${facilityOption.value}"
                                                                  path="fitnessCentre" value="${facilityOption.value}"/>
                                                <label class="form-check-label" for="${facilityOption.value}">
                                                        ${facilityOption.value}
                                                </label>
                                            </div>
                                        </c:forEach>
                                    </div>

                                    <div class="mb-3">
                                        <label for="roomType">Room Type:</label>
                                        <c:forEach items="${roomTypeOptions}" var="roomTypeOption">
                                            <div class="form-check">
                                                <form:radiobutton class="form-check-input" id="${roomTypeOption.value}"
                                                                  path="roomType" value="${roomTypeOption.value}"/>
                                                <label class="form-check-label" for="${roomTypeOption.value}">
                                                        ${roomTypeOption.value}
                                                </label>
                                            </div>
                                        </c:forEach>
                                    </div>

                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label class="form-label" for="hotelName">Hotel name:</label>
                                        <form:input type="text" class="form-control" path="hotelName" id="hotelName" name="hotelName"
                                               placeholder="Hotel name" oninput="searchHotel()"/>
                                        <select id="hotelSelect" class="form-select" size="5"
                                                style="display: none;"></select>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label" for="location">Location:</label>
                                        <form:input type="text" class="form-control" path="location" id="location" name="location"
                                               placeholder="Location"/>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label" for="priceMin">Price Range:</label>
                                        <div class="input-group">
                                            <span class="input-group-text">$</span>
                                            <form:input type="number" path="priceMin" class="form-control" id="priceMin" name="priceMin"
                                                   placeholder="Min Price"/>
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <div class="input-group">
                                            <span class="input-group-text">$</span>
                                            <form:input type="number"  path="priceMax" class="form-control" id="priceMax" name="priceMax"
                                                   placeholder="Max Price"/>
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label" for="numberOfBed">Number of Beds:</label>
                                        <form:input path="numberOfBed" type="number" class="form-control" id="numberOfBed" name="numberOfBed"
                                               placeholder="Number of Beds"/>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label" for="checkIn">Check-In Date:</label>
                                        <form:input path="checkIn" type="date" class="form-control" id="checkIn" name="checkIn"/>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label" for="checkOut">Check-Out Date:</label>
                                        <form:input path="checkOut" type="date" class="form-control" id="checkOut" name="checkOut"/>
                                    </div>

                                </div>

                            </div>
                        </div>

                        <button type="submit" class="btn btn-primary">Search</button>
                        <button type="reset" class="btn btn-primary">Reset</button>
                    </form:form>
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

                                                <c:if test="${room.hotel.rating == null}">
                                                    <span class="hotel-rating">Rating: No Rating </span>
                                                </c:if>
                                                <c:if test="${room.hotel.rating != null}">
                                                    <span class="hotel-rating">Rating:
                                                        <fmt:formatNumber maxFractionDigits="1"
                                                                          value="${room.hotel.rating.rating}"/>
                                                        out of 5</span>
                                                </c:if>
                                                <br/>
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

<script src="/assets/js/searchHotelName.js"></script>
</body>
</html>
