document.addEventListener("DOMContentLoaded", function () {
    const roomNumberInput = document.getElementById("roomNumberInput");
    const addRoomNumberButton = document.getElementById("addRoomNumberButton");
    const roomNumberChipsContainer = document.getElementById("roomNumberChipsContainer");
    const roomNumbersHiddenInput = document.getElementById("roomNumbersHidden");
    const readOnly = document.getElementById("readOnly").value;
    const roomNumberList = roomNumbersHiddenInput.value.split(',');

    if(roomNumberList[0]) {
        roomNumberList.forEach(val => addRoomNumberChip(val, readOnly));
    }
    if (readOnly === 'false') {
        addRoomNumberButton.addEventListener("click", function () {
            const roomNumber = roomNumberInput.value.trim();
            if (roomNumber !== "") {
                addRoomNumberChip(roomNumber, readOnly);
                roomNumberInput.value = "";
            }
        });
    }

    function addRoomNumberChip(roomNumber, readOnly) {
        const chips = roomNumberChipsContainer.querySelectorAll(".chip-text");
        const roomNumbers = Array.from(chips).map(chip => chip.textContent);

        if (roomNumbers.indexOf(roomNumber) !== -1) {
            return;
        }

        const chip = document.createElement("div");
        chip.className = "chip";

        const chipText = document.createElement("span");
        chipText.className = "chip-text";
        chipText.textContent = roomNumber;

        if (readOnly === 'false') {
            const chipClose = document.createElement("span");
            chipClose.className = "chip-close";
            chipClose.textContent = "×";
            chipClose.addEventListener("click", function () {
                roomNumberChipsContainer.removeChild(chip);
                updateRoomNumbersHiddenInput();
            });
            chip.appendChild(chipText);
            chip.appendChild(chipClose);
        }
        else {
            chip.appendChild(chipText);
        }


        roomNumberChipsContainer.appendChild(chip);

        updateRoomNumbersHiddenInput();
    }

    function updateRoomNumbersHiddenInput() {
        const chips = roomNumberChipsContainer.querySelectorAll(".chip-text");
        const roomNumbers = Array.from(chips).map(chip => chip.textContent);

        let temp = "";
        for (const x of roomNumbers) {
            temp += x + ", ";
        }
        temp = temp.slice(0, -2);
        roomNumbersHiddenInput.value = temp;
    }
});
