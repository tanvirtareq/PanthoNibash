<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/16/23
  Time: 8:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sitemesh" uri="http://www.sitemesh.org/taglib" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Website</title>
    <!-- Add your CSS and JavaScript files here -->
    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/main.js"></script>
    <sitemesh:write property='head'/>
</head>
<body>
<header>
    <!-- Add your header content here -->
    <nav>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/about">About</a></li>
            <li><a href="/contact">Contact</a></li>
        </ul>
    </nav>
</header>
<div id="content">
    <sitemesh:write property='body'/>
</div>
<footer>
    <!-- Add your footer content here -->
    &copy; 2023 Pantho Nibash. All rights reserved.
</footer>
</body>
</html>

</body>
</html>
