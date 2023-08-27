<%@ page contentType="text/html;charset=UTF-8"   %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/assets/css/navbar.css">
    <title><spring:message code="page.title"/></title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top">
    <a class="navbar-brand" href=""><spring:message code="brand.name"/></a>
    <button class="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="<spring:message code='toggle.nav'/>">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href=""><spring:message code="nav.home"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/search"><spring:message code="nav.search"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/hotels"><spring:message code="nav.hotels"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/rooms"><spring:message code="nav.rooms"/></a>
            </li>
            <c:choose>
                <c:when test="${empty sessionContext}">
                    <li class="nav-item">
                        <div class="dropdown show">
                            <a class="nav-link" href="#" role="button" id="dropdownSignupLink">
                                <spring:message code="nav.signup"/>
                            </a>
                            <ul class="dropdown-menu" id="signUpDropDownMenu">
                                <li>
                                    <a class="dropdown-item" href="/customer/signup"><spring:message code="nav.signup.customer"/></a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="/hotel/signup"><spring:message code="nav.signup.hotel"/></a>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li class="nav-item">
                        <div class="dropdown show">
                            <a class="nav-link" href="#" role="button" id="dropdownLoginLink">
                                <spring:message code="nav.login"/>
                            </a>
                            <ul class="dropdown-menu" id="loginDropDownMenu">
                                <li>
                                    <a class="dropdown-item" href="/customer/login"><spring:message code="nav.login.customer"/></a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="/hotel/login"><spring:message code="nav.login.hotel"/></a>
                                </li>
                            </ul>
                        </div>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="/logout"><spring:message code="nav.logout"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${sessionContext.profileLink}">
                            <img src="data:image/jpeg;base64,${sessionContext.profilePictureBase64Image}"
                                 alt="${sessionContext.name}" class="rounded-3" style="width: 50px; height: 50px;">
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
            <li class="nav-item">
                <div class="dropdown show">
                    <a class="nav-link" href="#" role="button" id="dropdownLanguageOptions">
                        <spring:message code="nav.language"/>
                    </a>
                    <ul class="dropdown-menu" id="languageOptionsDropDownMenu">
                        <li>
                            <a class="dropdown-item" id="englishLanguageOption" href="#"><spring:message code="nav.language.english"/></a>
                        </li>
                        <li>
                            <a class="dropdown-item" id="banglaLanguageOption" href="#"><spring:message code="nav.language.bangla"/></a>
                        </li>
                    </ul>
                </div>
            </li>
        </ul>
    </div>
</nav>

<script src="/assets/js/navbar.js"></script>

</body>
</html>
