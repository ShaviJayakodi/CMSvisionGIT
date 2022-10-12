$(document).ready(function() {
    $("#rbRegistration").prop("checked", true);
    loadRegistrationPage();
});

function submit()
{
    if ($("#rbRegistration").prop("checked")) {
        if (isEmpty()) {
            var gradeCode = document.getElementById("gradeCode").value.toLocaleUpperCase();
            var gradeDescription = document.getElementById("gradeDescription").value;
            var gradeDes = gradeDescription.charAt(0).toUpperCase() + gradeDescription.substring(1).toLowerCase();

            var requestObj =
                {
                    gradeCode: gradeCode,
                    gradeDescription: gradeDes
                }
            $.ajax({
                url: "/gradeController/saveGrade",
                type: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                dataType: "json",
                data: JSON.stringify(requestObj),
                success: function (data) {
                    Swal.fire(
                        'Registered Successfully',
                        'Registered Grade is '+data.gradeDescription,
                        'success'
                    )

                },
                error: function (data) {
                    console.log(data.success);
                    Swal.fire({
                        icon: 'error',
                        title: 'ERROR',
                        text: 'Something went wrong!',
                    });

                }
            });
        }
    }

    if ($("#rbUpdate").prop("checked"))
    {
        var gradeId = document.getElementById("gradeId").value;
        var gradeCode= document.getElementById("gradeCode").value;
        var gradeDescription = document.getElementById("gradeDescription").value;

        var requestObject =
            {
                gradeId:gradeId,
                gradeCode: gradeCode,
                gradeDescription : gradeDescription
            }

        $.ajax({
            url:"/gradeController/saveGrade",
            type:"POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType:"json",
            data:JSON.stringify(requestObject),
            success:function (data)
            {
                Swal.fire(
                    'Updated Successfully',
                    'Update '+data.gradeDescription,
                    'success'
                )
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

    if($("#rbDelete").prop("checked"))
    {
        var gradeId = document.getElementById("gradeId").value;

        $.ajax({
            url:"/gradeController/deleteGrade?gradeId="+gradeId,
            type:"DELETE",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType:"json",
            success:function (data)
            {Swal.fire(
                'Deleted!',
                'Your file has been deleted.',
                'success'
                        );
                gradeLoad();

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


function setGradeListToSelectBox(gradeList)
{
    $("#selectGradeCode").append(
        "<option value=null>==SELECT==</option>"
    );
    $.each(gradeList,function (index,grade){
        $("#selectGradeCode").append(
            "<option value="+grade.gradeId+">"+grade.gradeCode+" || "+grade.gradeDescription.toUpperCase()+"</option>"
        );
    });
}

function setDataToGradeInquiryTable(gradeList)
{
    $("#gradeInqTableBody").empty();
    $.each(gradeList,function (index,grade){
        $("#gradeInqTableBody").append(
            "<tr>" +
            "<td>"+grade.gradeCode+"</td>"
            +"<td>"+grade.gradeDescription+"</td>"
            +"</tr>"
        );
    });


}


function gradeLoad()
{
    $.ajax({
        url: "/gradeController/getAll",
        type: "GET",
        data: {},
        success: function(data) {
            setGradeListToSelectBox(data);
            setDataToGradeInquiryTable(data)
        },
        error: function(xhr) {
            alert("Error");
        }

    });
}

function setGradeDetailsToFields(uniqueGrade)
{
    document.getElementById("gradeId").value=uniqueGrade.gradeId;
    document.getElementById("gradeCode").value = uniqueGrade.gradeCode;
    document.getElementById("gradeDescription").value=uniqueGrade.gradeDescription;
}

function getUniqueDetailById()
{
    var getId = document.getElementById("selectGradeCode").value;

    $.ajax({
        url:"/gradeController/getUniqueDetailsById?gradeId="+getId,
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success:function (data)
        {
            setGradeDetailsToFields(data);
        }
    });
}

function isEmpty()
{   var result = true;

    var gradeCode = document.getElementById("gradeCode").value;
    var gradeDescription = document.getElementById("gradeDescription").value;


    if(gradeCode == null || gradeCode=="")
    {
        document.getElementById("divGradeCode").innerHTML="Grade Code is Required!";
        result =false;
    }

    if (gradeDescription ==null || gradeDescription =="")
    {
        document.getElementById("divGradeDescription").innerHTML="Grade Description is Required!"
        result=false;
    }


    return result;
}

function clearFields()
{
    document.getElementById("gradeCode").value="";
    document.getElementById("gradeDescription").value="";
}


function loadRegistrationPage()
{
    $("#mainContainerPage").load("loadGradeRegistration/");
    $("#mainContainerPage").value=true;
}

function loadUpdatePage()
{
    $("#mainContainerPage").load("loadGradeUpdate/");
    $("#mainContainerPage").value=true;
    gradeLoad();
}

function loadDeletePage()
{
    $("#mainContainerPage").load("loadGradeDelete/");
    $("#mainContainerPage").value=true;
    gradeLoad();
}

function inquiry()
{
    $("#mainContainerPage").load("loadGradeInquiry/");
    $("#mainContainerPage").value=true;
    gradeLoad();

}