$(document).ready(function()
{
     $("#rbRegistration").prop("checked",true);
     loadRegistrationPage();
});

function loadRegistrationPage()
{
    $("#mainContainerPage").load("loadSubjectRegistration/");
    $("#mainContainerPage").value=true;
}

function loadUpdatePage()
{
    $("#mainContainerPage").load("loadSubjectUpdate/");
    $("#mainContainerPage").value=true;
}

function loadDeletePage()
{
    $("#mainContainerPage").load("loadSubjectDelete/");
    $("#mainContainerPage").value=true;
}
