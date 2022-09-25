$(document).ready(function() {
    $("#rbRegistration").prop("checked", true);
    loadRegistrationPage();
});


function getAllOfficers()
{
    $.ajax({
        url:"/officerController/getAllOfficers",
        type:"GET",
        data:{},
        success:function (data)
        {
            console.log(data);
        },
        error:function (xhr)
        {
            alert("Error Loading");
        }
    });
}

function getUniqueOfficerByRegNo()
{
    var regNo = document.getElementById("searchRegNo").value;
    $.ajax({
        url: "/officerController/getUniqueByRegNo?officerRegNo="+regNo,
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success:function (data)
        {
            document.getElementById("officerName").value=data.firstName+" "+data.lastName;
            document.getElementById("officerId").value=data.officerId;
        },
        error:function (xhr)
        {
            alert("Error");
        }
    });
}
function getUniqueOfficerById()
{
    var officerId = document.getElementById("officerId").value;
    $.ajax({
        url:"officerController/getUniqueByOfficerId?officerId="+officerId,
        type:"GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success:function (data)
        {
            setOfficerDataToFields(data);
        },
        error:function (xhr)
        {
            alert("Error");
        }
    });

}

function setOfficerDataToFields(officer){
    document.getElementById("officerId").value=officer.officerId;
    document.getElementById("regNo").value=officer.regNo;
    document.getElementById("firstName").value=officer.firstName;
    document.getElementById("lastName").value=officer.lastName;
    document.getElementById("birthDay").value=formatDate(officer.birthDay);
    document.getElementById("attendDate").value=formatDate(officer.attendDate);
    document.getElementById("address").value=officer.address;
    document.getElementById("mobileNumber1").value=officer.mobNum1;
    document.getElementById("mobileNumber2").value=officer.mobNum2;
    document.getElementById("emailAddress").value=officer.emailAddress;
    document.getElementById("dutyType").value=officer.dutyType;
    var gender = officer.gender;
    if (gender=="Male")
    {
        $("#male").prop("checked",true);
    }
    else
    {
        $("#female").prop("checked",true);
    }
}


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