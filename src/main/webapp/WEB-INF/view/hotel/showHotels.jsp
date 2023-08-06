<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/25/23
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Room Search</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customStyle.css">

</head>
<body>

<header class="custom-header">
    <h1>hotels</h1>
</header>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="custom-card">
                <div class="card-body" style="height: 700px; overflow-y: auto;">

                    <c:if test="${not empty hotels}">
                        <c:forEach items="${hotels}" var="hotel">

                            <div class="custom-card mb-3" style="max-width: 840px;">
                                <div class="row g-0">
                                    <div class="col-md-6">
                                        <a href="${pageContext.request.contextPath}/hotel/${hotel.id}">
                                            <img
                                                    src="data:image/jpeg;base64,${hotel.hotelImageBase64Image}"
                                                    alt="${hotel.name}"
                                                    height="250px"
                                                    width="400px"
                                                    class=" rounded-start img-search-card"

                                            />
                                        </a>

                                    </div>
                                    <div class="col-md-6">
                                        <div class="card-body">
                                            <h5 class="card-title">Hotel: <c:out value="${hotel.name}"/></h5>
                                            <p class="card-text">
                                                Location:
                                                    <c:out value="${hotel.location}"/>
                                                <br/>
                                                Parking Facility:
                                                    <c:out value="${hotel.parkingFacility}"/>
                                                <br/>
                                                Wifi Facility:
                                                    <c:out value="${hotel.wifiFacility}"/>
                                                <br/>
                                                Swimming Pool:
                                                    <c:out value="${hotel.swimmingPool}"/>
                                                <br/>
                                                Fitness Centre:
                                                    <c:out value="${hotel.fitnessCentre}"/>
                                                <br/>

                                                <c:if test="${hotel.rating == null}">
                                                    <span class="hotel-rating">Rating: No Rating </span>
                                                </c:if>
                                                <c:if test="${hotel.rating != null}">
                                                    <span class="hotel-rating">Rating:
                                                        <fmt:formatNumber maxFractionDigits="1"
                                                                          value="${hotel.rating.rating}"/>
                                                        out of 5</span>
                                                </c:if>
                                            </p>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>

                    <c:if test="${empty hotels}">
                        <p>No hotels found.</p>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
