<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup Page - Step 3</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/signupPage.css">
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="signup-container">
    <h2 class="signup-title">Signup - Personal Information</h2>
    <form:form action="/signup/page3" method="post" modelAttribute="trainee">
        <div class="form-group">
            <label for="dateOfBirth">Date of Birth:</label>
            <form:input type="date" class="form-control" path="dateOfBirth"/>
            <form:errors path="dateOfBirth" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
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
            <form:errors path="favoriteProgrammingLanguage" cssClass="alert alert-danger mt-3"
                         cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="country">Country:</label>
            <form:select class="form-control" path="country">
                <form:options items="${countryOptions}"/>
            </form:select>
            <form:errors path="country" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="skills">Skills:</label>
            <c:forEach items="${skillOptions}" var="skillOption">
                <div class="form-check">
                    <form:checkbox class="form-check-input" id="${skillOption.value}"
                                   path="skills" value="${skillOption.value}"/>
                    <label class="form-check-label" for="${skillOption.value}">
                            ${skillOption.value}
                    </label>
                </div>
            </c:forEach>
            <form:errors path="skills" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="gender">Gender:</label>
            <form:select class="form-control" path="gender">
                <form:options items="${genderOptions}"/>
            </form:select>
            <form:errors path="gender" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="phoneNumber">Phone Number:</label>
            <form:input type="text" class="form-control" path="phoneNumber"/>
            <form:errors path="phoneNumber" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="experience">Experience:</label>
            <form:input type="number" class="form-control" path="experience"/>
            <form:errors path="experience" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <div class="btn-group w-100">
                <a href="/signup/page2" class="btn btn-primary">Previous</a>
                <button type="submit" class="btn btn-primary">Next</button>
            </div>
        </div>
    </form:form>
    <p class="signup-footer">Already have an account? <a href="/login">Login</a></p>
</div>

<jsp:include page="clock.jsp"/>
</body>
</html>
