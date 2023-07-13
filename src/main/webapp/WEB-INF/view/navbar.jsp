<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navbar.css">
    <title>Pantho Nibash</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top">
    <a class="navbar-brand" href="/">Pantho Nibash</a>

    <button class="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <c:choose>
                <c:when test="${empty sessionContext}">
                    <li class="nav-item">
                        <a class="nav-link" href="/signup">Signup</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/login">Login</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/customer/logout">Logout</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/${sessionContext.profileLink}">
                            <img src="data:image/jpeg;base64,${sessionContext.profilePictureBase64Image}"
                                 alt="${sessionContext.name}" class="rounded-3" style="width: 50px; height: 50px;">
                        </a>
                    </li>

                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
