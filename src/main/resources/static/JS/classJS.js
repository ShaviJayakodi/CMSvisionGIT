$(document).ready(function() {
    $("#rbRegistration").prop("checked", true);
    loadRegistrationPage();
});

function submit()
{
    if($("#rbRegistration").prop("checked")){
        var teacherId=document.getElementById("selectTeacher").value;
        var gradeId=document.getElementById("selectGrade").value;
        var subjectId=document.getElementById("selectSubject").value;
        var classType=document.getElementById("classType").value;
        var fullFee=document.getElementById("fullPrice").value;
        var halfFree = document.getElementById("halfPrice").value;
        var freeFee = document.getElementById("freePrice").value;
        var commission = document.getElementById("commissionPercentage").value;
        var requestObj={
            teacherId:teacherId,
            gradeId:gradeId,
            subjectId:subjectId,
            classType:classType,
            fullFee:fullFee,
            halfFree:halfFree,
            freeFee:freeFee,
            commission:commission
        }
        console.log(requestObj);
        $.ajax({
            url:"/classController/saveClass",
            type:"POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType: JSON,
            data: JSON.stringify(requestObj),
            success: function (data) {
                if (!data.success) {
                    alert(data.statusList);
                } else {
                    alert("Successfully Registered.")
                }
            },
            error: function (data) {
                console.log(data.success);

            },
        });

    }
}



function getAllTeachers()
{
    $.ajax({
        url:"/teacherController/getAllTeachers",
        type:"GET",
        data: {},
        success:function (data)
        {
            setToTeacherSelectBox(data);
        },
        error:function (xhr)
        {
            alert("Error");
        }
    });
}

function getAllSubjects()
{
    $.ajax({
        url:"/subjectController/getAll",
        type:"GET",
        data: {},
        success:function (data)
        {
            setToSubjectSelectBox(data);
        },
        error:function (xhr)
        {
            alert("Error");
        }
    });
}

function getAllGrades()
{
    $.ajax({
        url:"/gradeController/getAll",
        type:"GET",
        data: {},
        success:function (data)
        {
            setToGradeSelectBox(data);
        },
        error:function (xhr)
        {
            alert("Error");
        }
    });
}

function setToTeacherSelectBox(teacherList)
{
    $("#selectTeacher").append(
        "<option value=null>==SELECT==</option>"
    );
    $.each(teacherList,function (index,teacher){

        $("#selectTeacher").append(
            "<option value ="+teacher.teacherId+">"+teacher.firstName+" "+teacher.lastName+"</option>"
        );
    });
}
function setToSubjectSelectBox(subjectList)
{
    $("#selectSubject").append(
        "<option value=null>==SELECT==</option>"
    );
    $.each(subjectList,function (index,subject){

        $("#selectSubject").append(
            "<option value ="+subject.subjectId+">"+subject.subjectCode+" || "+subject.subjectName+"</option>"
        );
    });
}

function setToGradeSelectBox(gradeList)
{
    $("#selectGrade").append(
        "<option value=null>==SELECT==</option>"
    );
    $.each(gradeList,function (index,grade){

        $("#selectGrade").append(
            "<option value ="+grade.gradeId+">"+grade.gradeCode+" || "+grade.gradeDescription+"</option>"
        );
    });
}


function loadRegistrationPage()
{
    $("#mainContainerPage").load("loadClassRegistration/");
    $("#mainContainerPage").value=true;
    getAllTeachers();
    getAllGrades();
    getAllSubjects();

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
