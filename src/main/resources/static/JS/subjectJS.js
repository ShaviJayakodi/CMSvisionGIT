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


    if($("#rbUpdate").prop("checked"))
    {
        var subjectId = document.getElementById("subjectId").value;
        var subjectCode = document.getElementById("subjectCode").value;
        var subjectName = document.getElementById("subjectName").value;

        var requestObj =
            {
                subjectId:subjectId,
                subjectCode:subjectCode,
                subjectName:subjectName
            }
            $.ajax({
                url:"/subjectController/saveSubject",
                type:"POST",
                headers : {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                dataType: "json",
                data:JSON.stringify(requestObj),
                success:function (data)
                {
                    alert("Success");
                    loadUpdatePage();
                }

            });
    }

    if($("#rbDelete").prop("checked"))
    {
        var subjectId = document.getElementById("subjectId").value;

        $.ajax({
            url:"/subjectController/deleteSubject?subjectId="+subjectId,
            type:"DELETE",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType:"json",
            success:function (data)
            { if (!data.success) {
                alert(data.statusList);
            } else {
                alert("Successfully Deleted.")
                loadUpdatePage();
                console.log(data.status);
            }
            },
            error: function (data) {
                console.log(data.success);
                alert("ERROR")
                console.log(data.status);

            },
        });
    }
}

function genarateCode()
{
    var subjectName=document.getElementById("subjectName").value;
    var code = subjectName.charAt(0).toUpperCase()+subjectName.charAt(1).toUpperCase()+subjectName.charAt(2).toUpperCase();
    document.getElementById("subjectCode").value=code;
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
           setSubjectsToTable(data);
       },
        error: function(xhr) {
            alert("Error");
        }

    });
}
function setSubjectsToTable(subjectList)
{
    $.each(subjectList,function (index,subject){
        $("#subjectInqTable").append(
            "<tr>"+
                "<td>"+subject.subjectCode+"</td>"+
                "<td>"+subject.subjectName+"</td>"+
            "</tr>"
        );
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

function inquiry()
{
    $("#mainContainerPage").load("loadSubjectInquiry/");
    $("#mainContainerPage").value=true;
    loadSubjects();
}

