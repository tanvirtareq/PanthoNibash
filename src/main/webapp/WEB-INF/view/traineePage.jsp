<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trainee Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <style>
        /* Custom styles */
        body {
            background-color: #f8f9fa;
            padding-top: 20px;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
        }

        .trainee-details {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
            max-width: 700px; /* Added max-width */
            margin-left: auto; /* Centering the section horizontally */
            margin-right: auto; /* Centering the section horizontally */
        }

        h2 {
            margin-bottom: 20px;
            text-align: center;
        }

        .section {
            margin-bottom: 30px;
        }

        .section h3 {
            margin-bottom: 10px;
        }

        .section p {
            margin: 5px 0;
        }

        .edit-link {
            text-align: center;
            display: flex;
            justify-content: center;
        }

        .edit-link a {
            display: inline-block;
            padding: 8px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .edit-link a:hover {
            background-color: #0056b3;
        }

        .course-card {
            padding: 10px;
            background-color: #f8f9fa;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            margin-bottom: 10px;
            transition: box-shadow 0.3s;
        }

        .course-card:hover {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .scrollable-list {
            max-height: 300px;
            overflow-y: auto;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container">
    <div class="trainee-details">
        <h2>Trainee Details</h2>
        <div class="row">
            <div class="col-md-6">
                <div class="section">
                    <h3>Basic Information</h3>
                    <p><strong>Name:</strong> <c:out value="${trainee.name}"/></p>
                    <p><strong>Email:</strong> <c:out value="${trainee.email}"/></p>
                </div>
                <div class="section">
                    <h3>Address</h3>
                    <p><strong>Street:</strong> <c:out value="${trainee.street}"/></p>
                    <p><strong>City:</strong> <c:out value="${trainee.city}"/></p>
                    <p><strong>Zip Code:</strong> <c:out value="${trainee.zipCode}"/></p>
                </div>
            </div>
            <div class="col-md-6">
                <div class="section">
                    <h3>Personal Information</h3>
                    <p><strong>Date of Birth:</strong> <c:out value="${trainee.dateOfBirth}"/></p>
                    <p><strong>Favorite Programming Language:</strong> <c:out
                            value="${trainee.favoriteProgrammingLanguage}"/></p>
                    <p><strong>Country:</strong> <c:out value="${trainee.country}"/></p>

                    <c:if test="${not empty trainee.skills}">
                        <ul>
                            <c:forEach items="${trainee.skills}" var="skill">
                                <li><c:out value="${skill}"/></li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <p><strong>Gender:</strong> <c:out value="${trainee.gender}"/></p>
                    <p><strong>Phone Number:</strong> <c:out value="${trainee.phoneNumber}"/></p>
                    <p><strong>Experience:</strong> <c:out value="${trainee.experience}"/></p>
                </div>

            </div>

        </div>
        <div class="section edit-link">
            <c:if test="${not empty SESSION_USER && ( SESSION_USER.id == trainee.id || SESSION_USER.role == 'ADMIN' )}">
                <a href="/trainee/${trainee.id}/edit">Edit</a>
            </c:if>
            <c:if test="${not empty SESSION_USER && ( SESSION_USER.role == 'ADMIN' )}">
                <a href="/deletetrainee/${trainee.id}">Delete</a>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="clock.jsp"/>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
