$(document).ready(function() {
    $("#rbRegistration").prop("checked", true);
    loadRegistrationPage();
});

function loadRegistrationPage()
{
    $("#mainContainerPage").load("loadHallRegistration/");
    $("#mainContainerPage").value=true;
}

function loadUpdatePage()
{
    $("#mainContainerPage").load("loadHallUpdate/");
    $("#mainContainerPage").value=true;
}

function loadDeletePage()
{
    $("#mainContainerPage").load("loadHallDelete/");
    $("#mainContainerPage").value=true;
}

