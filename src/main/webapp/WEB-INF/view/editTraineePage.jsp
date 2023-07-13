<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Trainee</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/signupPage.css">
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="signup-container">
    <h2 class="signup-title">Edit Trainee</h2>
    <form:form action="/updateTrainee/${trainee.id}" method="post" modelAttribute="trainee">
        <div class="form-group">
            <label for="name">Name:</label>
            <form:input type="text" class="form-control" path="name"/>
            <form:errors path="name" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="street">Street:</label>
            <form:input type="text" class="form-control" path="street"/>
            <form:errors path="street" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="city">City:</label>
            <form:input type="text" class="form-control" path="city"/>
            <form:errors path="city" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="zipCode">Zip Code:</label>
            <form:input type="text" class="form-control" path="zipCode"/>
            <form:errors path="zipCode" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="dateOfBirth">Date of Birth:</label>
            <form:input type="date" class="form-control" path="dateOfBirth"/>
            <form:errors path="dateOfBirth" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="favoriteProgrammingLanguage">Favorite Programming Language:</label>
            <c:forEach items="${languageOptions}" var="languageOption">
                <div class="form-check">
                    <form:radiobutton class="form-check-input" id="${languageOption.value}"
                                      path="favoriteProgrammingLanguage" value="${languageOption.value}"/>
                    <label class="form-check-label" for="${languageOption.value}">
                            ${languageOption.value}
                    </label>
                </div>
            </c:forEach>
            <form:errors path="favoriteProgrammingLanguage" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="country">Country:</label>
            <form:select class="form-control" path="country">
                <form:options items="${countryOptions}"/>
            </form:select>
            <form:errors path="country" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="skills">Skills:</label>
            <c:forEach items="${skillOptions}" var="skillOption">
                <div class="form-check">
                    <form:checkbox class="form-check-input" id="${skillOption.value}" path="skills"
                                   value="${skillOption.value}"/>
                    <label class="form-check-label" for="${skillOption.value}">
                            ${skillOption.value}
                    </label>
                </div>
            </c:forEach>
            <form:errors path="skills" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="gender">Gender:</label>
            <form:select class="form-control" path="gender">
                <form:options items="${genderOptions}"/>
            </form:select>
            <form:errors path="gender" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Phone Number:</label>
            <form:input type="text" class="form-control" path="phoneNumber"/>
            <form:errors path="phoneNumber" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="experience">Experience:</label>
            <form:input type="number" class="form-control" path="experience"/>
            <form:errors path="experience" class="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <button type="submit" class="btn btn-primary">Save Changes</button>
        <a href="/trainee/${trainee.id}" class="btn btn-secondary">Cancel</a>
    </form:form>
</div>

<jsp:include page="clock.jsp"/>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
