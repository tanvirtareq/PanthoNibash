<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/19/23
  Time: 12:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Room Image Upload</title>
</head>
<body>

<div class="container">
  <h2 class="text-center mt-5">Room Image Upload</h2>
  <form:form action="/hotel/${hotelId}/addRoom/roomImageUpload" method="post" enctype="multipart/form-data">
    <div class="row justify-content-center mt-5">
      <div class="col-md-6">
        <div class="form-group">
          <label for="roomImage">Room Image</label>
          <input type="file" id="roomImage" name="roomImage" class="form-control"/>
        </div>
        <c:if test="${not empty error}">
          <div class="alert alert-danger">${error}</div>
        </c:if>
        <button type="submit" class="btn btn-primary">Upload</button>
      </div>
    </div>
  </form:form>
</div>

</body>
</html>
