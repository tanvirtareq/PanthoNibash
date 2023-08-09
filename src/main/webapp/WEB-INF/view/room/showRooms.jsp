<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Room Search</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customStyle.css">
</head>
<body>


<header class="custom-header">
    <h1>Rooms</h1>
</header>


<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="custom-card">
                <div class="card-body" style="height: 650px; overflow-y: auto;">

                    <c:if test="${not empty rooms}">
                        <c:forEach items="${rooms}" var="room" varStatus="loopStatus">
                            <div class="custom-card mb-3" style="max-width: 840px;">
                                <div class="row g-0">
                                    <div class="col-md-6">
                                        <a href="${pageContext.request.contextPath}/room/${room.id}">
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

                    <c:if test="${empty rooms}">
                        <p>No rooms found.</p>
                    </c:if>


                </div>

                <div style="display: flex; justify-content: center;">
                    <nav aria-label="...">
                        <ul class="pagination pagination-circle">
                            <c:if test="${currentPage>2}">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/rooms?page=${currentPage-2}">${currentPage-2}</a>
                                </li>
                            </c:if>
                            <c:if test="${currentPage>1}">
                                <li class="page-item">
                                    <a class="page-link"
                                       href="${pageContext.request.contextPath}/rooms?page=${currentPage-1}">${currentPage-1}</a>
                                </li>
                            </c:if>
                            <li class="page-item active">
                                <a class="page-link" href="#">${currentPage} </a>
                            </li>
                            <li class="page-item">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/rooms?page=${currentPage+1}">${currentPage+1}</a>
                            </li>
                            <li class="page-item">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/rooms?page=${currentPage+2}">${currentPage+2}</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>

        </div>


    </div>
</div>

</body>
</html>
