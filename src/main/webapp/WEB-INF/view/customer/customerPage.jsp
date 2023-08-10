<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/customer.css">
    <title>Customer Page</title>
</head>
<body>
<header class="custom-header">
    <h1>${customer.name}</h1>
</header>

<div class="container custom-card">
    <div class="row">
        <div class="col-md-4">
            <a href="${pageContext.request.contextPath}/customer/${customer.id}">
                <img src="data:image/jpeg;base64,${customer.profilePicBase64Image}" alt="Customer Photo"
                     class="customer-photo">
            </a>
        </div>
        <div class="col-md-8">
            <p><strong>Name:</strong> ${customer.name}</p>
            <p><strong>Email:</strong> ${customer.email}</p>
            <p><strong>Phone:</strong> ${customer.phoneNumber}</p>
            <p><strong>Date of birth:</strong> ${customer.dateOfBirth}</p>
        </div>
        <c:set var="customerRole" value="CUSTOMER"/>
        <c:if test="${sessionContext!=null && sessionContext.role==customerRole && sessionContext.id==customer.id}">
            <div style="display: flex; justify-content: center; margin-top: 2rem;">
                <a href="${pageContext.request.contextPath}/customer/${customer.id}/bookingList" style="text-decoration: none;">
                    <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                            border-radius: 4px; cursor: pointer; font-size: 16px; width: 130px; margin: 2px">
                        Booking List
                    </button>
                </a>
                <a href="${pageContext.request.contextPath}/customer/${customer.id}/edit" style="text-decoration: none;">
                    <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                            border-radius: 4px; cursor: pointer; font-size: 16px; width: 130px; margin: 2px">
                        Edit
                    </button>
                </a>
            </div>
        </c:if>
    </div>

</div>

</body>
</html>
