<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/errorPage.css">
</head>
<body>
<div class="container">
    <div class="error-container">
        <h1 class="error-code">Oops!</h1>
        <p class="error-message">Something went wrong.</p>
        <a href="${pageContext.request.contextPath}/" class="btn btn-home">Go to Home</a>
    </div>
</div>

</body>
</html>
