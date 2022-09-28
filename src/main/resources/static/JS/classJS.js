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
        var halfFee = document.getElementById("halfPrice").value;
        var freeFee = document.getElementById("freePrice").value;
        var commission = document.getElementById("commissionPercentage").value;
        var requestObj={
            teacherId:teacherId,
            gradeId:gradeId,
            subjectId:subjectId,
            classType:classType,
            fullFee:fullFee,
            halfFee:halfFee,
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

function getAllClass()
{
    $.ajax({
        url:"/classController/getAllClass",
        type:"GET",
        data: {},
        success:function (data)
        {
            console.log(data);
          setClassTable(data);
        },
        error:function (xhr)
        {
            alert("Error");
        }
    });
}
function clear()
{
    document.getElementById("selectClass").innerHTML=null;
}
function getAllClassByTeacherId()
{

    var teacherId1=document.getElementById("selectUpdateTeacher").value;
    $.ajax({
        url:"/classController/getAllClassByTeacher?teacherId="+teacherId1,
        type:"GET",
        data: {},
        success:function (data)
        {
            setToSelectClass(data);
        },
        error:function (xhr)
        {
            alert("Error");
        }
    });
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
            setToSearchTeacher(data);
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

function getClassByClassId()
{
    var classId = document.getElementById("selectClass").value;
    $.ajax({
        url:"/classController/getClassByClassId?classId="+classId,
        type:"GET",
        data: {},
        success:function (data)
        {
            console.log(data);
            setDataToFields(data);

        },
        error:function (xhr)
        {
            alert("Error");
        }
    });


}


function setToSelectClass(classList)
{ document.getElementById("selectClass").innerHTML=null;

    $("#selectClass").append(
        "<option value=null>==SELECT==</option>"
    );
    $.each(classList,function (index,classInfo){
        $("#selectClass").append(
            "<option value="+classInfo.classId+">"+classInfo.classCode+"</option>"
        );
    });
}

function setToSearchTeacher(teacherList)
{

    $("#selectUpdateTeacher").append(
        "<option value=null>==SELECT==</option>"
    );
    $.each(teacherList,function (index,teacher){

        $("#selectUpdateTeacher").append(
            "<option value ="+teacher.teacherId+">"+teacher.firstName+" "+teacher.lastName+"</option>"
        );
    });
}

function setToTeacherSelectBox(teacherList)
{
    document.getElementById("selectTeacher").innerHTML=null;
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
    document.getElementById("selectSubject").innerHTML=null;
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

function setDataToFields(uniqueClass)
{
    console.log(uniqueClass);
    document.getElementById("classId").value=uniqueClass.classId;
    document.getElementById("classCode").value=uniqueClass.classCode;
    $('#selectGrade').append(
        "<option value="+uniqueClass.grade.gradeId+" selected=\"selected\" >"+uniqueClass.grade.gradeCode+" || "+uniqueClass.grade.gradeDescription +"</option>"
    );
    $('#selectSubject').append(
    "<option value="+uniqueClass.subject.subjectId+" selected=\"selected\">"+uniqueClass.subject.subjectCode+" || " +uniqueClass.subject.subjectName+"</option>"
    );

    $('#selectTeacher').append("" +
        "<option value="+uniqueClass.teacherId+" selected=\"selected\">"+uniqueClass.subject.subjectCode+" || " +uniqueClass.subject.subjectName+"</option>"

    );


    document.getElementById("classType").value=uniqueClass.classType;
    document.getElementById("fullPrice").value=uniqueClass.fullFee;
    document.getElementById("halfPrice").value=uniqueClass.halfFee;
    document.getElementById("freePrice").value=uniqueClass.freeFee;
    document.getElementById("commissionPercentage").value=uniqueClass.commission;
}
function setClassTable(classList)
{
    $.each(classList,function (index,uniqueClass){
        $("#classInqTable").append(
            "<tr>" +
            "<td>"+uniqueClass.classCode+"</td>"+
            "<td>"+uniqueClass.grade.gradeCode+" || "+ uniqueClass.grade.gradeDescription+"</td>"+
            "<td>"+uniqueClass.subject.subjectName+"</td>"+

            "</tr>"
        )
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
    getAllClass();
    getAllTeachers();
    getAllGrades();
    getAllSubjects();



}
function loadDeletePage()
{
    $("#mainContainerPage").load("loadClassDelete/");
    $("#mainContainerPage").value=true;
    getAllClass();
    getAllTeachers();
    getAllGrades();
    getAllSubjects();

}

function inquiry()
{
    $("#mainContainerPage").load("loadClassInquiry/");
    $("#mainContainerPage").value=true;
    getAllClass();
}
