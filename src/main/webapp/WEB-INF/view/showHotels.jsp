<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customStyle.css">
  <style>
    .custom-header {
      background-color: rgba(228, 234, 226, 0.55);
      box-shadow: 0 0 10px rgb(169, 135, 135);
      border-radius: 10px;
      color: #350505;
      text-align: center;
      padding: 1rem;
      margin-bottom: 20px;
      text-transform: uppercase;
    }
  </style>
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
                    <a href="/hotel/${hotel.id}">
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
                        Location: <c:out value="${hotel.location}"/>
                        <br/>
                        Parking Facility: <c:out value="${hotel.parkingFacility}"/>
                        <br/>
                        Wifi Facility: <c:out value="${hotel.wifiFacility}"/>
                        <br/>
                        Swimming Pool: <c:out value="${hotel.swimmingPool}"/>
                        <br/>
                        Fitness Centre: <c:out value="${hotel.fitnessCentre}"/>
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

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

</body>
</html>
