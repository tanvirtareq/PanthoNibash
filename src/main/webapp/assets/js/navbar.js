document.addEventListener("DOMContentLoaded", function () {

    addDropDown("dropdownSignupLink", "signUpDropDownMenu");
    addDropDown("dropdownLoginLink", "loginDropDownMenu");
    addDropDown("dropdownLanguageOptions", "languageOptionsDropDownMenu");

    function addDropDown(dropdownLinkId, dropDownMenuId) {
        const dropdownLink = document.getElementById(dropdownLinkId);
        if (dropdownLink === null) {
            return;
        }
        const dropDownMenu = document.getElementById(dropDownMenuId);
        if (dropDownMenu === null) {
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

    const englishLanguageOption = document.getElementById("englishLanguageOption");
    const banglaLanguageOption = document.getElementById("banglaLanguageOption");

    englishLanguageOption.addEventListener("click", function (event) {
        updateLanguageParameter("en", event);
    });

    banglaLanguageOption.addEventListener("click", function (event) {
        updateLanguageParameter("bn", event);
    });

    function updateLanguageParameter(languageCode, event) {
        event.preventDefault();

        const currentUrl = window.location.href;

        if (currentUrl.includes("?")) {

            const updatedUrl = currentUrl.replace(/([?&])language=[^&]*(&|$)/, '');

            window.location.href = updatedUrl + (updatedUrl.includes("?") ? "&" : "?") + "language=" + languageCode;
        } else {
            window.location.href = currentUrl + "?language=" + languageCode;
        }
    }

});
