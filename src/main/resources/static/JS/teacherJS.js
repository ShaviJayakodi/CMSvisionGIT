$(document).ready(function() {
    $("#rbRegistration").prop("checked", true);
    loadRegistrationPage();
});

function loadRegistrationPage()
{
    $("#mainContainerPage").load("loadTeacherRegistration/");
}

function loadUpdatePage()
{
    $("#mainContainerPage").load("loadTeacherUpdate/");
    $("#mainContainerPage").value=true;
}
function loadDeletePage()
{
    $("#mainContainerPage").load("loadTeacherDelete/");
    $("#mainContainerPage").value=true;
}