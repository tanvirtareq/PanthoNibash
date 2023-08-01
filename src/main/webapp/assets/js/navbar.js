document.addEventListener("DOMContentLoaded", function () {

    addDropDown("dropdownSignupLink", "signUpDropDownMenu");
    addDropDown("dropdownLoginLink", "loginDropDownMenu")

    function addDropDown(dropdownLinkId, dropDownMenuId) {
        const dropdownLink = document.getElementById(dropdownLinkId);
        if (dropdownLink === null) {
            return;
        }
        const dropDownMenu = document.getElementById(dropDownMenuId);
        if(dropDownMenu === null) {
            return;
        }
        dropdownLink.addEventListener("mouseover", () => {
            dropDownMenu.style.display = "block";
        });

        dropdownLink.addEventListener("mouseout", () => {
            dropDownMenu.style.display = "none";
        });

        dropDownMenu.addEventListener("mouseover", () => {
            dropDownMenu.style.display = "block";
        });

        dropDownMenu.addEventListener("mouseout", () => {
            dropDownMenu.style.display = "none";
        });
    }

});
