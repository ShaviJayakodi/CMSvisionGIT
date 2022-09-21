$(document).ready(function() {
    $("#rbRegistration").prop("checked", true);
    loadRegistrationPage();
});

function loadRegistrationPage()
{
    $("#mainContainerPage").load("loadOfficerRegistration/");
    $("#mainContainerPage").valie=true;
}

function loadUpdatePage()
{
    $("#mainContainerPage").load("loadOfficerUpdate/");
    $("#mainContainerPage").value=true;
}
function loadDeletePage()
{
    $("#mainContainerPage").load("loadOfficerDelete/");
    $("#mainContainerPage").value=true;
}