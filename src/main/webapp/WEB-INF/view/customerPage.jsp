<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/13/23
  Time: 2:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Page</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            background-color: #f9f9f9;
        }

        header {
            background-color: rgba(228, 234, 226, 0.55);
            color: #350505;
            text-align: center;
            padding: 1rem;
            margin-bottom: 20px;
        }

        .custom-header {
            background-color: rgba(228, 234, 226, 0.55);
            box-shadow: 0 0 10px rgb(169, 135, 135);
            border-radius: 10px;
            color: #350505;
            text-align: center;
            padding: 1rem;
            margin-bottom: 20px;
            text-transform: uppercase;
        }


        .custom-card {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        .customer-photo {
            max-width: 200px;
            height: auto;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

    </style>
</head>
<body>
<header class="custom-header">
    <h1>${customer.name}</h1>
</header>

<div class="container custom-card">
    <div class="row">
        <div class="col-md-4">
            <a href="/customer/${customer.id}">
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
                <a href="/customer/${customer.id}/bookingList" style="text-decoration: none;">
                    <button style="background-color: #4CAF50; color: white; padding: 12px 20px; border: none;
                            border-radius: 4px; cursor: pointer; font-size: 16px; width: 130px; margin: 2px">
                        Booking List
                    </button>
                </a>
                <a href="/customer/${customer.id}/edit" style="text-decoration: none;">
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
