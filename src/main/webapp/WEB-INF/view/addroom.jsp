<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tanvirtareq
  Date: 7/18/23
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add room</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/signupPage.css">
    <style>
        .chips-container {
            display: flex;
            flex-wrap: wrap;
            gap: 5px;
            margin-top: 10px;
        }

        .chip {
            display: flex;
            align-items: center;
            background-color: #f1f1f1;
            padding: 5px;
            border-radius: 3px;
        }

        .chip-text {
            margin-right: 5px;
        }

        .chip-close {
            cursor: pointer;
        }
    </style>
</head>
<body>

<div class="signup-container">
    <h2 class="signup-title">Add room</h2>
    <form:form id="addRoomForm" action="/hotel/${sessionContext.id}/addroom" method="post" modelAttribute="room">

        <div class="form-group">
            <label for="type">Type:</label>
            <c:forEach items="${roomTypeOptions}" var="roomTypeOption">
                <div class="form-check">
                    <form:radiobutton class="form-check-input" id="${roomTypeOption.value}"
                                      path="type" value="${roomTypeOption.value}"/>
                    <label class="form-check-label" for="${roomTypeOption.value}">
                            ${roomTypeOption.value}
                    </label>
                </div>
            </c:forEach>
            <form:errors path="type" cssClass="alert alert-danger mt-3"
                         cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <form:input type="number" class="form-control" path="price"/>
            <form:errors path="price" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>

        <div class="form-group">
            <label for="numberOfBed">Number of bed:</label>
            <form:input type="number" class="form-control" path="numberOfBed"/>
            <form:errors path="numberOfBed" cssClass="alert alert-danger mt-3" cssStyle="padding: 3px;"/>
        </div>
        <div class="form-group">
            <label for="roomNumbers">Room Numbers:</label>
            <div class="input-group">
                <input type="number" class="form-control" id="roomNumberInput" placeholder="Enter room number"/>
                <div class="input-group-append">
                    <button type="button" class="btn btn-primary" id="addRoomNumberButton">Add</button>
                </div>
            </div>
        </div>

        <div class="chips-container" id="roomNumberChipsContainer"></div>
        <form:hidden path="roomNumbers" id="roomNumbersHidden" />

        <button type="submit" class="btn btn-primary signup-button">Add</button>
    </form:form>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const roomNumberInput = document.getElementById("roomNumberInput");
        const addRoomNumberButton = document.getElementById("addRoomNumberButton");
        const roomNumberChipsContainer = document.getElementById("roomNumberChipsContainer");
        const roomNumbersHiddenInput = document.getElementById("roomNumbersHidden");

        addRoomNumberButton.addEventListener("click", function () {
            const roomNumber = roomNumberInput.value.trim();
            if (roomNumber !== "") {
                addRoomNumberChip(roomNumber);
                roomNumberInput.value = "";
            }
        });

        function addRoomNumberChip(roomNumber) {
            const chip = document.createElement("div");
            chip.className = "chip";

            const chipText = document.createElement("span");
            chipText.className = "chip-text";
            chipText.textContent = roomNumber;

            const chipClose = document.createElement("span");
            chipClose.className = "chip-close";
            chipClose.textContent = "×";
            chipClose.addEventListener("click", function () {
                roomNumberChipsContainer.removeChild(chip);
                updateRoomNumbersHiddenInput();
            });

            chip.appendChild(chipText);
            chip.appendChild(chipClose);
            roomNumberChipsContainer.appendChild(chip);

            updateRoomNumbersHiddenInput();
        }

        function updateRoomNumbersHiddenInput() {
            const chips = roomNumberChipsContainer.querySelectorAll(".chip-text");
            const roomNumbers = Array.from(chips).map(chip => chip.textContent);
            roomNumbersHiddenInput.value = JSON.stringify(roomNumbers);
        }
    });
</script>

</body>
</html>