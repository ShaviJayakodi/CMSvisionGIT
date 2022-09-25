$(document).ready(function() {
    $("#rbRegistration").prop("checked", true);
    loadRegistrationPage();
});

function loadRegistrationPage()
{
    $("#mainContainerPage").load("loadClassRegistration/");
    $("#mainContainerPage").value=true;
}

function loadUpdatePage()
{
    $("#mainContainerPage").load("loadClassUpdate/");
    $("#mainContainerPage").value=true;
}
function loadDeletePage()
{
    $("#mainContainerPage").load("loadClassDelete/");
    $("#mainContainerPage").value=true;
}
