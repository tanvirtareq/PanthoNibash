<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/homepage.css">
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container">
    <div class="row card-wrapper">
        <div class="col-md-6">
            <div class="card custom-card">
                <div class="card-body">
                    <h5 class="card-title">Registered Trainee</h5>
                    <p class="card-text">Description of registered trainees.</p>
                    <a href="/trainees" class="btn btn-primary">View Trainees</a>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="clock.jsp"/>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
