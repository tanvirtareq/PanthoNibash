function searchHotel() {
    const hotelName = document.getElementById("hotelName").value;
    const hotelSelect = document.getElementById("hotelSelect");

    hotelSelect.innerHTML = "";

    let baseUrl = window.location.origin + window.location.pathname;

    fetch(`${baseUrl}/hotels?name=` + hotelName)
        .then((response) => response.json())
        .then((hotels) => {
            if (hotels.length > 0) {

                hotelSelect.style.display = "block";

                hotels.forEach(hotel => {
                    const option = document.createElement("option");
                    option.value = hotel;
                    option.textContent = hotel;
                    hotelSelect.appendChild(option);
                    option.addEventListener("click", () => {
                        let hotelNameElement = document.getElementById("hotelName");
                        hotelNameElement.value = hotel;
                    })
                });
            } else {

                hotelSelect.style.display = "none";
            }
        })
        .catch((error) => console.error(error));
}

window.addEventListener("click", (event) => {
    if (!event.target.matches("#hotelSelect")) {
        document.getElementById("hotelSelect").style.display = "none";
    }
});