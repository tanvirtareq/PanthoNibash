<%@ page contentType="text/html;charset=UTF-8"   %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="page.title" /></title>
</head>
<body>

<div class="container">
    <h2 class="text-center mt-5"><c:out value="${headerMessage}"/></h2>
    <form:form action="/${postMappingLink}" method="post" enctype="multipart/form-data">
        <div class="row justify-content-center mt-5">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="image"><spring:message code="label.image" /></label>
                    <input type="file" id="image" name="image" class="form-control"/>
                </div>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>
                <button type="submit" class="btn btn-primary"><spring:message code="upload.button.label" /></button>
            </div>
        </div>
    </form:form>
</div>

</body>
</html>
