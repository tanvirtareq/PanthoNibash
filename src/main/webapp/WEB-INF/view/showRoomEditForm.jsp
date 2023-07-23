<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/23/23
  Time: 2:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>add room</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/signupPage.css">
  <style>
    .chips-container {
      display: flex;
      flex-wrap: wrap;
      gap: 5px;
      margin-top: 10px;
    }

    .chip {
      display: flex;
      align-items: center;
      background-color: #f1f1f1;
      padding: 5px;
      border-radius: 3px;
    }

    .chip-text {
      margin-right: 5px;
    }

    .chip-close {
      cursor: pointer;
    }
  </style>
</head>
<body>

<div class="signup-container">
  <h2 class="signup-title">Add room</h2>
  <form:form id="addRoomForm" action="/room/${room.id}/edit" method="post" modelAttribute="room">

    <div class="form-group">
      <label for="type">Type:</label>
      <c:forEach items="${roomTypeOptions}" var="roomTypeOption">
        <div class="form-check">
          <form:radiobutton class="form-check-input" id="${roomTypeOption.value}"
                            path="type" value="${roomTypeOption.value}"/>
          <label class="form-check-label" for="${roomTypeOption.value}">
              ${roomTypeOption.value}
          </label>
        </div>
      </c:forEach>
      <form:errors path="type" cssClass="alert alert-danger mt-3"
                   cssStyle="padding: 3px;"/>
    </div>
    <div class="form-group">
      <label for="price">Price:</label>
      <form:input type="number" class="form-control" path="price"/>
      <form:errors path="price" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
    </div>

    <div class="form-group">
      <label for="numberOfBed">Number of bed:</label>
      <form:input type="number" class="form-control" path="numberOfBed"/>
      <form:errors path="numberOfBed" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
    </div>
    <button type="submit" class="btn btn-primary signup-button">Update</button>
  </form:form>
</div>

</body>
</html>
