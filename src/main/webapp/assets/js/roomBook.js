function updateTotalCost() {
    const checkInElement = document.getElementById("checkInDate");
    const checkOutElement = document.getElementById("checkOutDate");
    const roomPriceElement = document.getElementById("roomPrice");
    let roomPrice = parseInt(roomPriceElement.textContent);
    let totalCostElement = document.getElementById("totalCost");
    const checkInDate = new Date(checkInElement.value);
    const checkOutDate = new Date(checkOutElement.value);
    let dayDifference = ((checkOutDate - checkInDate) / (1000 * 60 * 60 * 24));
    console.log(dayDifference);
    if (dayDifference > 0) {
        totalCostElement.innerHTML = dayDifference * roomPrice;
    } else {
        totalCostElement.innerHTML = "Invalid";
    }
}