
$(document).ready(function() {
        $("#rbRegistration").prop("checked", true);
        loadRegistrationPage();

});


function submit() {
        if ($("#rbRegistration").prop("checked"))
        {
                var firstName = document.getElementById("firstName").value;
                var lastName = document.getElementById("lastName").value;
                var birthDay = document.getElementById("birthDay").value;
                var attendDate = document.getElementById("attendDate").value;
                var address = document.getElementById("address").value;
                var school = document.getElementById("school").value;
                var gradeId = document.getElementById("gradeSelect").value;
                var mob1 = document.getElementById("mobileNumber1").value;
                var mob2 = document.getElementById("mobileNumber2").value;
                var parentName = document.getElementById("parentName").value;
                var relationship = document.getElementById("relationship").value;
                var gender = "";
                if ($("#male").prop("checked")) {
                        gender = document.getElementById("male").value;
                } else if ($("#female").prop("checked")) {
                        gender = document.getElementById("female").value;
                }

                var requestObj =
                    {
                            firstName: firstName,
                            lastName: lastName,
                            birthDay: birthDay,
                            attendDate: attendDate,
                            address: address,
                            school: school,
                            gradeId: gradeId,
                            gender: gender,
                            mob1:mob1,
                            mob2:mob2,
                            parentName:parentName,
                            relationship:relationship

                            }
                        console.log(requestObj);
                $.ajax({
                        url: "/studentController/saveStudent",
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
                                        'Student Name '+data.firstName+" "+data.lastName +' & Student Reg No is '+data.regNo,
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

        if($("#rbUpdate").prop("checked"))
        {
                var regNo = document.getElementById("studentRegNo").value;
                var studentId=document.getElementById("studentId").value;
                var firstName = document.getElementById("firstName").value;
                var lastName = document.getElementById("lastName").value;
                var birthDay = document.getElementById("birthDay").value;
                var attendDate = document.getElementById("attendDate").value;
                var address = document.getElementById("address").value;
                var school = document.getElementById("school").value;
                var gradeId = document.getElementById("gradeSelect").value;
                var mob1 = document.getElementById("mobileNumber1").value;
                var mob2 = document.getElementById("mobileNumber2").value;
                var parentName = document.getElementById("parentName").value;
                var relationship = document.getElementById("relationship").value;
                var gender = "";
                if ($("#male").prop("checked")) {
                        gender = document.getElementById("male").value;
                } else if ($("#female").prop("checked")) {
                        gender = document.getElementById("female").value;
                }
                var requestObj=
                    {
                            studentId:studentId,
                            regNo:regNo,
                            firstName: firstName,
                            lastName: lastName,
                            birthDay: birthDay,
                            attendDate: attendDate,
                            address: address,
                            school: school,
                            gradeId: gradeId,
                            gender: gender,
                            mob1:mob1,
                            mob2:mob2,
                            parentName:parentName,
                            relationship:relationship
                    }
                    $.ajax({
                        url: "/studentController/saveStudent",
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
                                'Student Name '+data.firstName+" "+data.lastName +' & Student Reg No is '+data.regNo,
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
                var studentId = document.getElementById("studentId").value;
                $.ajax({
                    url:"/studentController/deleteStudentById?studentId="+studentId,
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
                        }



                });
        }


}

function gradeLoad()
{
    $.ajax({
        url: "/gradeController/getAll",
        type: "GET",
        data: {},
        success: function(data) {
            setGradeListToSelectBox(data);
        },
        error: function(xhr) {
            alert("Error");
        }

    });
}

function setGradeListToSelectBox(gradeList)
{
    $("#gradeSelect").append(
        "<option value=null>==SELECT==</option>"
    );
    $.each(gradeList,function (index,grade){
        $("#gradeSelect").append(
            "<option value="+grade.gradeId+">"+grade.gradeCode+" || "+grade.gradeDescription+"</option>"
        );
    });
}

function getStudentByGradeId()
{
    var gradeId = document.getElementById("gradeSelect").value;

    $.ajax({
        url:"/studentController/studentGetByGradeId?gradeId="+gradeId,
        type:"GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success:function (data)
        {
            console.log(data);
            onKey(data);
        },

    });
}

function getUniqueStudentById()
{
    var studentId= document.getElementById("studentId").value;

    $.ajax({
        url:"/studentController/getUniqueById?studentId="+studentId,
        type:"GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success:function (data)
        {
            setStudentDataToFields(data)
        }
    });
}


function getUniqueStudentByRegNo()
{
    var regNo = document.getElementById("regNo").value;
    $.ajax({
        url:"/studentController/getUniqueByRegNo?regNo="+regNo,
        type:"GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success:function (data)
        {
            document.getElementById("studentId").value=data.studentId;
            document.getElementById("studentRegNo").value=data.regNo;
            document.getElementById("studentName").value=data.firstName+" "+data.lastName;
        }
    });
}


function setStudentDataToFields(studentList)
{
    console.log(studentList);
    gradeLoad();
    document.getElementById("studentId").value=studentList.studentId;
    document.getElementById("studentRegNo").value=studentList.regNo;
    document.getElementById("firstName").value=studentList.firstName;
    document.getElementById("lastName").value=studentList.lastName;
    document.getElementById("birthDay").value=formatDate(studentList.birthDay);
    document.getElementById("attendDate").value=formatDate(studentList.attendDate);
    document.getElementById("address").value=studentList.address;
    document.getElementById("school").value=studentList.school;
    document.getElementById("gradeSelect").value=studentList.gradeId;
    document.getElementById("parentName").value = studentList.parentName;
    document.getElementById("relationship").value=studentList.relationship;
    document.getElementById("mobileNumber1").value=studentList.mob1;
    document.getElementById("mobileNumber2").value=studentList.mob2;
    var gender = studentList.gender;
    if(gender=="Male")
    {
        $("#male").prop("checked",true);
    }
    else
    {
        $("#female").prop("checked",true);
    }
}

var newWin;
function popup()
{
    newWin = window.open('/loadPopupSearch','Student Inquiry', 'directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=800,height=500');

    document.onmousedown=focusPopup;
    document.onkeyup=focusPopup;
    document.onmousemove=focusPopup;

    function focusPopup(){
        if(!newWin.closed){
            newWin.focus();
        }
    }

}



function  inquiry()
{
    $("#mainContainerPage").load("loadPopupSearch/");
    $("#mainContainerPage").value=true;
    gradeLoad();
}




function loadRegistrationPage()
{
        $("#mainContainerPage").load("loadStudentRegistration/");
        $("#mainContainerPage").value=true;
        gradeLoad();
}

function loadUpdatePage()
{
        $("#mainContainerPage").load("loadStudentUpdate/");
        $("#mainContainerPage").value=true;
        gradeLoad();

}
function loadDeletePage()
{
        $("#mainContainerPage").load("loadStudentDelete/");
        $("#mainContainerPage").value=true;
        gradeLoad();
}






