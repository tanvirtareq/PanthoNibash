<%@ page contentType="text/html;charset=UTF-8"   %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><spring:message code="error.page.title"/></title>
    <link rel="stylesheet" href="/assets/css/errorPage.css">
</head>
<body>

<div class="container">
    <div class="error-container">
        <h1 class="error-code"><spring:message code="error.page.heading"/></h1>
        <p class="error-message"><spring:message code="error.page.message"/></p>
        <a href="/" class="btn btn-home"><spring:message code="error.page.home.button"/></a>
    </div>
</div>

</body>
</html>
