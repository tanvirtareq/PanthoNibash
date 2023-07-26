<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            <li class="nav-item">
                <a class="nav-link" href="/hotels">Hotels</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/rooms">Rooms</a>
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
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/customer/signup">Signup
                                        as customer</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/hotel/signup">Signup
                                        as hotel</a>
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
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/customer/login">Login
                                        as customer</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/hotel/login">Login
                                        as hotel</a>
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
            if (dropdownLink === null) {
                return;
            }
            const dropDownMenu = document.getElementById(dropDownMenuId);
            if(dropDownMenu === null) {
                return;
            }
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


</body>
</html>
