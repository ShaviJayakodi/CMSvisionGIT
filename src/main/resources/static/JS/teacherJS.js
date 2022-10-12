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
     var commission = document.getElementById("commissionPercentage").value;

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
             emailAddress:emailAddress,
             commission:commission
         }
         console.log(requestObj);
        $.ajax({
            url: "/teacherController/saveTeacher",
            type: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType:"json",
            data: JSON.stringify(requestObj),
            success:function (data)
            {
                Swal.fire(
                    'Registered Successfully',
                    'Teacher Name '+data.firstName+" "+data.lastName +' & Teacher Reg No is '+data.regNO,
                    'success'
                );
            },
            error: function(data){
                Swal.fire({
                    icon: 'error',
                    title: 'ERROR',
                    text: 'Something went wrong!',

                });

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
        var commission = document.getElementById("commissionPercentage").value;

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
            emailAddress:emailAddress,
            commission:commission
        }

        $.ajax({
            url: "/teacherController/saveTeacher",
            type: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType:"json",
            data: JSON.stringify(requestObj),
            success:function (data)
            {
                Swal.fire(
                    'Updated Successfully',
                    'Teacher Name '+data.firstName+" "+data.lastName +' & Teacher Reg No is '+data.regNO,
                    'success'
                );
            },
            error: function(data){
                Swal.fire({
                    icon: 'error',
                    title: 'ERROR',
                    text: 'Something went wrong!',

                });


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
                    Swal.fire(
                        'Deleted!',
                        'Your file has been deleted.',
                        'success'
                    );
                        loadDeletePage();

                },

                error: function (data) {
                    Swal.fire({
                        icon: 'error',
                        title: 'ERROR',
                        text: 'Something went wrong!',
                    });
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
        },
        error:function (data)
        {
            Swal.fire({
                icon: 'error',
                title: 'ERROR',
                text: 'Something went wrong!',
            });
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
    //document.getElementById("commissionPercentage").value=teacherUnique.commision;
   $("#commissionPercentage").append(
        "<option value="+teacherUnique.commision+" selected=\"selected\" >"+teacherUnique.commision+"</option>"
    );


}

function clear()
{
    document.getElementById("regNo").value="";
    document.getElementById("teacherName").value="";
}


/*

function setTeacherToTable(teacherList)
{
    $.each(teacherList,function (index,teacher)
    {
        $("#teacherInqTable").append(

            "<tr>" +
            "<td>"+teacher.regNO+"</td>"+
            "<td>"+teacher.firstName+" "+teacher.lastName+"</td>"+
            "<td>"+teacher.mobNum1+"</td>"+
            "<td>"+teacher.mobNum2+"</td>"+
            "<td>"+teacher.address+"</td>"+
            "</tr>"
        );

    });

}
*/

var newWin;
function popup()
{
    newWin = window.open('/loadPopupTeacher','Teacher Inquiry', 'directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=800,height=500');

    document.onmousedown=focusPopup;
    document.onkeyup=focusPopup;
    document.onmousemove=focusPopup;

    function focusPopup(){
        if(!newWin.closed){
            newWin.focus();
        }
    }

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

function inquiry()
{
    $("#mainContainerPage").load("loadPopupTeacher/");
    $("#mainContainerPage").value=true;


}