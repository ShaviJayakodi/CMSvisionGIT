$(document).ready(function() {
    $("#rbRegistration").prop("checked", true);
    loadRegistrationPage();
});

function loadRegistrationPage()
{
    $("#mainContainerPage").load("loadGradeRegistration/");
    $("#mainContainerPage").value=true;
}

function loadUpdatePage()
{
    $("#mainContainerPage").load("loadGradeUpdate/");
    $("#mainContainerPage").value=true;
}

function loadDeletePage()
{
    $("#mainContainerPage").load("loadGradeDelete/");
    $("#mainContainerPage").value=true;
}