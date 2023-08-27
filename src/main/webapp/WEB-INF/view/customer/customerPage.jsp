<%@ page contentType="text/html;charset=UTF-8"   %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/assets/css/customStyle.css">
    <link rel="stylesheet" href="/assets/css/customer.css">
    <title><spring:message code="customer.page.title" arguments="${customer.name}" /></title>
</head>
<body>

<header class="custom-header">
    <h1>${customer.name}</h1>
</header>

<div class="container custom-card">
    <div class="row">
        <div class="col-md-4">
            <a href="/customer/${customer.id}">
                <img src="data:image/jpeg;base64,${customer.profilePicBase64Image}" alt="<spring:message code="customer.page.customerPhoto" />"
                     class="customer-photo">
            </a>
        </div>
        <div class="col-md-8">
            <p><strong><spring:message code="customer.page.name" />:</strong> ${customer.name}</p>
            <p><strong><spring:message code="customer.page.email" />:</strong> ${customer.email}</p>
            <p><strong><spring:message code="customer.page.phone" />:</strong> ${customer.phoneNumber}</p>
            <p><strong><spring:message code="customer.page.dateOfBirth" />:</strong> ${customer.dateOfBirth}</p>
        </div>
        <c:if test="${customerLoggedIn == true}">
            <div class="custom-button-container">
                <a href="/customer/${customer.id}/bookingList" style="text-decoration: none;">
                    <button class="custom-general-button">
                        <spring:message code="customer.page.bookingList" />
                    </button>
                </a>
                <a href="/customer/${customer.id}/edit" style="text-decoration: none;">
                    <button class="custom-general-button">
                        <spring:message code="customer.page.edit" />
                    </button>
                </a>
            </div>
        </c:if>
    </div>
</div>

</body>
</html>
