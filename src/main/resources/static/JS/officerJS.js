$(document).ready(function() {
    $("#rbRegistration").prop("checked", true);
    loadRegistrationPage();
});

function submit()
{
    if($("#rbRegistration").prop("checked"))
    {
        var firstName = document.getElementById("firstName").value;
        var lastName = document.getElementById("lastName").value;
        var birthDay = document.getElementById("birthDay").value;
        var attendDate = document.getElementById("attendDate").value;
        var address = document.getElementById("address").value;
        var dutyType = document.getElementById("dutyType").value;
        var mobNum1 = document.getElementById("mobileNumber1").value;
        var mobNum2 = document.getElementById("mobileNumber2").value;
        var emailAddress = document.getElementById("emailAddress").value;
        var gender;
        if($("#male").prop("checked"))
        {
            gender=document.getElementById("male").value;
        }
        else
        {
            gender=document.getElementById("female").value;
        }
        var requestObj=
            {
                firstName:firstName,
                lastName:lastName,
                birthDay:birthDay,
                attendDate:attendDate,
                dutyType:dutyType,
                address:address,
                mobNum1:mobNum1,
                mobNum2:mobNum2,
                emailAddress:emailAddress,
                gender:gender
            }
        $.ajax({
            url:"/officerController/saveOfficer",
            type:"POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType:JSON,
            data: JSON.stringify(requestObj),
            success:function (data)
            {
                if(!data.success) {
                    alert(data.statusList);
                } else {
                    alert("Successfully Registered.")
                }
            },
            error:function (data)
            {
                console.log(data.success)
            },
        });

    }

    if($("#rbUpdate").prop("checked"))
    {
        var officerId = document.getElementById("officerId").value;
        var regNo = document.getElementById("regNo").value;
        var firstName = document.getElementById("firstName").value;
        var lastName = document.getElementById("lastName").value;
        var birthDay = document.getElementById("birthDay").value;
        var attendDate = document.getElementById("attendDate").value;
        var address = document.getElementById("address").value;
        var dutyType = document.getElementById("dutyType").value;
        var mobNum1 = document.getElementById("mobileNumber1").value;
        var mobNum2 = document.getElementById("mobileNumber2").value;
        var emailAddress = document.getElementById("emailAddress").value;
        var gender;
        if($("#male").prop("checked"))
        {
            gender=document.getElementById("male").value;
        }
        else
        {
            gender=document.getElementById("female").value;
        }
        var requestObj=
            {
                officerId:officerId,
                regNo:regNo,
                firstName:firstName,
                lastName:lastName,
                birthDay:birthDay,
                attendDate:attendDate,
                dutyType:dutyType,
                address:address,
                mobNum1:mobNum1,
                mobNum2:mobNum2,
                emailAddress:emailAddress,
                gender:gender
            }
        $.ajax({
            url:"/officerController/saveOfficer",
            type:"POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType:JSON,
            data: JSON.stringify(requestObj),
            success:function (data)
            {
                if(!data.success) {
                    alert(data.statusList);
                } else {
                    alert("Successfully Updated.")
                }
            },
            error:function (data)
            {
                console.log(data.success)
            },
        });

    }

    if($("#rbDelete").prop("checked"))
    {
        var officerId = document.getElementById("officerId").value;

        $.ajax({
            url:"/officerController/deleteOfficerById?officerId="+officerId,
            type:"DELETE",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType:"json",
            success:function (data) {
                if (!data.success) {
                    alert(data.statusList);
                } else {
                    alert("Successfully Deleted.")
                    loadDeletePage();
                }
            },

            error: function (data) {
                console.log(data.success);
                loadDeletePage();

            },

        });
    }

}

function setOfficerToTable(officerList)
{
    $.each(officerList,function (index,officer){
       $("#officerInqTable").append(
         "<tr>"+
             "<td>"+officer.regNo+"</td>"+
           "<td>"+officer.firstName+" "+officer.lastName+"</td>"+
           "<td>"+officer.mobNum1+"</td>"+
           "<td>"+officer.mobNum2+"</td>"+
           "<td>"+officer.gender+"</td>"+
         "</tr>"
       );
    });
}

function getAllOfficers()
{
    $.ajax({
        url:"/officerController/getAllOfficers",
        type:"GET",
        data:{},
        success:function (data)
        {
            setOfficerToTable(data);
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
 function inquiry()
 {
     $("#mainContainerPage").load("loadOfficerInquiry/");
     $("#mainContainerPage").value=true;
     getAllOfficers();
 }