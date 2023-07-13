<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/16/23
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title><decorator:title default="Pantho Nibash"/></title>
  <decorator:head/>
</head>
<body>
<header>
  <jsp:include page="../navbar.jsp"/>
</header>
<main style="margin-top: 100px">
  <decorator:body/>
</main>

</body>
</html>
