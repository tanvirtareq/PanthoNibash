<%@ page contentType="text/html;charset=UTF-8"   %>

<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<html>
<head>
    <title><decorator:title default="Pantho Nibash"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.5/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.js">
    </script>
    <decorator:head/>
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <jsp:include page="../navbar.jsp"/>
</header>

<div class="flex-grow-1">
    <main style="margin-top: 100px; margin-left: 40px;margin-right: 40px">
        <decorator:body/>
    </main>
    <jsp:include page="../footer.jsp"/>
</div>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
