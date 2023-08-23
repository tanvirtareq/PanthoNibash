<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 8/14/23
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pantho Nibash</title>

</head>
<body>

<div class="container text-center alert alert-success">
    <div class="h1">
        <strong>
            <spring:message code="label.success"/>
        </strong>
    </div>
    <div class="h3">
        <div class="text">
            <c:out value="${successMessageDto.message}"/>
        </div>
    </div>
    <c:forEach items="${successMessageDto.buttonList}" var="button">
        <a href="${button.link}">
            <button class="btn btn-primary">
                <c:out value="${button.name}"/>
            </button>
        </a>
    </c:forEach>
</div>

</body>
</html>
