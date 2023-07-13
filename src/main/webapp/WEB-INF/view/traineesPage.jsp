<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 6/7/23
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trainees</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/traineesPage.css">
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container">
    <h2>Trainees</h2>

    <c:forEach items="${trainees}" var="trainee">
        <div class="trainee-card">
            <h4>
                <a href="/trainee/${trainee.id}">
                    <c:out value="${trainee.name}"/>
                </a>
            </h4>
            <p>
                <a href="/trainee/${trainee.id}">
                    <c:out value="${trainee.email}"/>
                </a>
            </p>
            <c:if test="${not empty SESSION_USER && SESSION_USER.role eq 'ADMIN'}">
                <button type="button" class="btn btn-danger delete-button" data-toggle="modal"
                        data-target="#deleteModal"
                        data-trainee-id="${trainee.id}">
                    Delete
                </button>
            </c:if>
        </div>
    </c:forEach>
</div>

<!-- Delete Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">Delete Trainee</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete this trainee?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <a id="deleteLink" class="btn btn-danger">Delete</a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="clock.jsp"/>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function () {
        $('.delete-button').click(function () {
            let traineeId = $(this).data('trainee-id');
            let deleteUrl = '/deletetrainee/' + traineeId;
            $('#deleteLink').attr('href', deleteUrl);
        });
    });
</script>
</body>
</html>
