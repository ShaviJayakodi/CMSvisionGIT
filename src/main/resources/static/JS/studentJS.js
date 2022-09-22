
$(document).ready(function() {
        $("#rbRegistration").prop("checked", true);
        loadRegistrationPage();

});

function loadRegistrationPage()
{
        $("#mainContainerPage").load("loadStudentRegistration/");
}

function loadUpdatePage()
{
        $("#mainContainerPage").load("loadStudentUpdate/");
        $("#mainContainerPage").value=true;
}
function loadDeletePage()
{
        $("#mainContainerPage").load("loadStudentDelete/");
        $("#mainContainerPage").value=true;
}

function  inquiry()
{
        $("#mainContainerPage").load("loadStudentInquiry/");
        $("#mainContainerPage").value=true;
}
