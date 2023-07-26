<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/6/23
  Time: 9:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
    <style>
        body {
            background-color: #f8f9fa;
        }

        .error-container {
            max-width: 500px;
            margin: 100px auto;
            padding: 40px;
            background-color: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .error-code {
            font-size: 80px;
            font-weight: bold;
            margin-bottom: 30px;
            color: #dc3545;
        }

        .error-message {
            font-size: 24px;
            margin-bottom: 30px;
        }

        .btn-home {
            color: #fff;
            background-color: #007bff;
            border-color: #007bff;
            padding: 10px 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="error-container">
        <h1 class="error-code">Oops!</h1>
        <p class="error-message">Something went wrong.</p>
        <a href="/" class="btn btn-home">Go to Home</a>
    </div>
</div>

</body>
</html>
