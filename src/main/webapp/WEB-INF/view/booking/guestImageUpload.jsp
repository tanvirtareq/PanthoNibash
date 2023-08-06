<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/22/23
  Time: 9:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Guest Photo</title>
</head>
<body>

<div class="container">
    <h2 class="text-center mt-5">Guest Photo Upload</h2>
    <form:form action="${pageContext.request.contextPath}/room/${roomId}/book/guestimageupload" method="post" enctype="multipart/form-data">
        <div class="row justify-content-center mt-5">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="guestImage">Guest Photo</label>
                    <input type="file" id="guestImage" name="guestImage" class="form-control"/>
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
