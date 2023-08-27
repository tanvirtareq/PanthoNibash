<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8"   %>
<html>
<head>

</head>
<body>

<div class="container text-center alert alert-success">
    <div class="h1">
        <strong>
            <c:out value="${doneMessageDto.header}"/>
        </strong>
    </div>
    <div class="h3">
        <div class="text">
            <c:out value="${doneMessageDto.message}"/>
        </div>
    </div>
    <c:forEach items="${doneMessageDto.buttonList}" var="button">
        <a href="${button.link}">
            <button class="btn btn-primary">
                <c:out value="${button.name}"/>
            </button>
        </a>
    </c:forEach>
</div>

</body>
</html>
