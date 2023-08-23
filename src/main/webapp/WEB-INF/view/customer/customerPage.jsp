<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <c:set var="customerRole" value="CUSTOMER"/>
        <c:if test="${sessionContext!=null && sessionContext.role==customerRole && sessionContext.id==customer.id}">
            <div style="display: flex; justify-content: center; margin-top: 2rem;">
                <a href="/customer/${customer.id}/bookingList" style="text-decoration: none;">
                    <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                            border-radius: 4px; cursor: pointer; font-size: 16px; width: 130px; margin: 2px">
                        <spring:message code="customer.page.bookingList" />
                    </button>
                </a>
                <a href="/customer/${customer.id}/edit" style="text-decoration: none;">
                    <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                            border-radius: 4px; cursor: pointer; font-size: 16px; width: 130px; margin: 2px">
                        <spring:message code="customer.page.edit" />
                    </button>
                </a>
            </div>
        </c:if>
    </div>
</div>

</body>
</html>
