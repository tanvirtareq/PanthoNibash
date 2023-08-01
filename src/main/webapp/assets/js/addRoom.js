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
        console.log(roomNumbers);
        let temp = "";
        for (const x of roomNumbers) {
            console.log(x);
            temp += x + ", ";
        }
        temp = temp.slice(0, -2);
        console.log(temp);
        roomNumbersHiddenInput.value = temp;
    }
});
