$(document).ready(function()
{
     $("#rbRegistration").prop("checked",true);
     loadRegistrationPage();
});
function submit() {
    if ($("#rbRegistration").prop("checked")) {
        var subjectCode = document.getElementById("subjectCode").value;
        var subjectName = document.getElementById("subjectName").value;

        var requestObj =
            {
                subjectCode:subjectCode,
                subjectName:subjectName
            }
            $.ajax({
               url:"/subjectController/saveSubject",
               type:"POST",
               headers :
                   {
                       'Accept':'application/json',
                       'Content-Type':'application/json'
                   },
                dataType:"json",
                data:JSON.stringify(requestObj),
                success:function (data)
                {
                    alert("success");
                }
            });

    }
}




function loadSubjects()
{
    $.ajax({
       url: "/subjectController/getAll",
       type: "GET",
       data:{},
       success:function (data)
       {
           setSubjectToSelectBox(data);
       },
        error: function(xhr) {
            alert("Error");
        }

    });
}
function setSubjectToSelectBox(subjectList)
{
    $("#selectSubjectBox").append(
        "<option value=0>==SELECT==</option>"
    );
    $.each(subjectList,function (index,subject) {
        $("#selectSubjectBox").append(
            "<option value="+subject.subjectId+">"+subject.subjectCode+" || "+subject.subjectName+"</option>"
        );
    });

}
function loadUpdatePage()
{
    $("#mainContainerPage").load("loadSubjectUpdate/");
    $("#mainContainerPage").value=true;
    loadSubjects();
}

function loadRegistrationPage()
{
    $("#mainContainerPage").load("loadSubjectRegistration/");
    $("#mainContainerPage").value=true;
}

function getUniqueSubDetailsById()
{
    var subjectId = document.getElementById("selectSubjectBox").value;

    $.ajax({
        url:"/subjectController/getDetailsById?subjectId="+subjectId,
        type:"GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success:function (data) {
            setSubjectDetailsToFields(data);

        }
    });
}

function setSubjectDetailsToFields(uniqueSubject)
{
    console.log(uniqueSubject);
    document.getElementById("subjectId").value = uniqueSubject.subjectId;
    document.getElementById("subjectCode").value=uniqueSubject.subjectCode;
    document.getElementById("subjectName").value=uniqueSubject.subjectName;
}



function loadDeletePage()
{
    $("#mainContainerPage").load("loadSubjectDelete/");
    $("#mainContainerPage").value=true;
    loadSubjects();
}
