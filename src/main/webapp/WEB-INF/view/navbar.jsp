<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navbar.css">
    <title>Pantho Nibash</title>
    <style>
        .dropdown-menu {
            display: none;
            top: 100%;
            left: 0;
            padding: 0px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin: 0px;
            max-width: 100px;
        }

        .dropdown-menu li {
            list-style: none;
        }

        .dropdown-menu li a {
            display: block;
            padding: 5px;
            text-decoration: none;
            color: #333;
        }

        .dropdown-menu li a:hover {
            background-color: #ddd;
        }
    </style>
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
            <li class="nav-item">
                <a class="nav-link" href="/search">Search</a>
            </li>
            <c:choose>
                <c:when test="${empty sessionContext}">
                    <li class="nav-item">
                        <div class="dropdown show">
                            <a class="nav-link" href="#" role="button" id="dropdownSignupLink">
                                Signup
                            </a>

                            <ul class="dropdown-menu" id="signUpDropDownMenu">
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/customer/signup">Signup as customer</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/hotel/signup">Signup as hotel</a>
                                </li>
                                </ul>
                        </div>
                    </li>
                    <li class="nav-item">
                        <div class="dropdown show">
                            <a class="nav-link" href="#" role="button" id="dropdownLoginLink">
                                Login
                            </a>

                            <ul class="dropdown-menu" id="loginDropDownMenu">
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/customer/login">Login as customer</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/hotel/login">Login as hotel</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="/logout">Logout</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${sessionContext.profileLink}">
                            <img src="data:image/jpeg;base64,${sessionContext.profilePictureBase64Image}"
                                 alt="${sessionContext.name}" class="rounded-3" style="width: 50px; height: 50px;">
                        </a>
                    </li>

                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>

<script>
    document.addEventListener("DOMContentLoaded", function () {

        addDropDown("dropdownSignupLink", "signUpDropDownMenu");
        addDropDown("dropdownLoginLink", "loginDropDownMenu")

        function addDropDown(dropdownLinkId, dropDownMenuId) {
            const dropdownLink = document.getElementById(dropdownLinkId);
            const dropDownMenu = document.getElementById(dropDownMenuId);
            dropdownLink.addEventListener("mouseover", () => {
                dropDownMenu.style.display = "block";
            });

            dropdownLink.addEventListener("mouseout", () => {
                dropDownMenu.style.display = "none";
            });

            dropDownMenu.addEventListener("mouseover", () => {
                dropDownMenu.style.display = "block";
            });

            dropDownMenu.addEventListener("mouseout", () => {
                dropDownMenu.style.display = "none";
            });
        }

    });
</script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
