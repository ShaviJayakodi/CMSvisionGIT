$(document).ready(function() {
    $("#rbRegistration").prop("checked", true);
    loadRegistrationPage();
});

function submit()
{
    if ($("#rbRegistration").prop("checked"))
    {
     var firstName = document.getElementById("firstName").value;
     var lastName = document.getElementById("lastName").value;
     var birthDay = document.getElementById("birthDay").value;
     var attendDate = document.getElementById("attendDate").value;
     var address = document.getElementById("address").value;
     var gender ="";
        if ($("#male").prop("checked")) {
            gender = document.getElementById("male").value;
        } else if ($("#female").prop("checked")) {
            gender = document.getElementById("female").value;
        }
     var mobNum1 = document.getElementById("mobileNumber1").value;
     var mobNum2 = document.getElementById("mobileNumber2").value;
     var emailAddress = document.getElementById("emailAddress").value;

     var requestObj =
         {
             firstName:firstName,
             lastName:lastName,
             birthDay:birthDay,
             attendDate:attendDate,
             address:address,
             gender:gender,
             mobNum1:mobNum1,
             mobNum2:mobNum2,
             emailAddress:emailAddress
         }
         console.log(requestObj);
        $.ajax({
            url: "/teacherController/saveTeacher",
            type: "POST",
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
            error: function(data){
                console.log(data.success);

            },
        });
    }

    if($("#rbUpdate").prop("checked"))
    {
        var teacherId=document.getElementById("teacherId").value;
        var regNO=document.getElementById("teacherRNo").value;
        var firstName=document.getElementById("firstName").value;
        var lastName=document.getElementById("lastName").value;
        var address=document.getElementById("address").value;
        var birthDay=document.getElementById("birthDay").value;
        var attendDate=document.getElementById("attendDate").value;
        var gender = "";
        if($("#male").prop("checked"))
        {
            gender = document.getElementById("male").value;
        }
        else if ($("#female").prop("checked"))
        {
            gender = document.getElementById("female").value;
        }
        var mobNum1=document.getElementById("mobileNumber1").value;
        var mobNum2=document.getElementById("mobileNumber2").value;
        var emailAddress=document.getElementById("emailAddress").value;

        var requestObj = {
            teacherId:teacherId,
            regNO:regNO,
            firstName:firstName,
            lastName:lastName,
            birthDay:birthDay,
            address:address,
            attendDate:attendDate,
            gender:gender,
            mobNum1:mobNum1,
            mobNum2:mobNum2,
            emailAddress:emailAddress
        }

        $.ajax({
            url: "/teacherController/saveTeacher",
            type: "POST",
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
            error: function(data){
                console.log(data.success);

            }
        });

    }
    if($("#rbDelete").prop("checked"))
    {
        var teacherId = document.getElementById("teacherId").value;
    console.log(teacherId);
       $.ajax({
            url:"/teacherController/teacherDelete?teacherId="+teacherId,
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


function getUnique()
{
    var regNo = document.getElementById("regNo").value;

    $.ajax({
        url: "teacherController/getUnique?regNo="+regNo,
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success:function (data)
        {

           setName(data);
        }

    });
}

function setName(teacher)
{
    document.getElementById("teacherName").value=teacher.firstName+" "+teacher.lastName;
    document.getElementById("searchTeacherId").value= teacher.teacherId;
}


function getUniqueById()
{
    var tId = document.getElementById("searchTeacherId").value;
    $.ajax({
        url: "teacherController/getById?teacherId="+tId,
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success:function (data)
        {
            setDataToTeacherFields(data);
        }
    });

}


function setDataToTeacherFields(teacherUnique)
{

    document.getElementById("teacherId").value=teacherUnique.teacherId;
    document.getElementById("teacherRNo").value=teacherUnique.regNO;
    document.getElementById("firstName").value=teacherUnique.firstName;
    document.getElementById("lastName").value=teacherUnique.lastName;
    document.getElementById("birthDay").value=teacherUnique.birthDay;
    document.getElementById("attendDate").value=teacherUnique.attendDate;
    document.getElementById("address").value=teacherUnique.address;
    var gender = teacherUnique.gender;
    if (gender=="Male")
    {
        $("#male").prop("checked",true);
    }
    else
    {
        $("#female").prop("checked",true);
    }
    document.getElementById("mobileNumber1").value=teacherUnique.mobNum1;
    document.getElementById("mobileNumber2").value=teacherUnique.mobNum2;
    document.getElementById("emailAddress").value=teacherUnique.emailAddress;

}

function clear()
{
    document.getElementById("regNo").value="";
    document.getElementById("teacherName").value="";
}


function loadRegistrationPage()
{
    $("#mainContainerPage").load("loadTeacherRegistration/");
    $("#mainContainerPage").value=true;
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