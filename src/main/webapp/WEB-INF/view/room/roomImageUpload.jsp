<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <form:form action="${pageContext.request.contextPath}/hotel/${hotelId}/addroom/roomImageUpload" method="post" enctype="multipart/form-data">
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
