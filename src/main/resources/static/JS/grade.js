function loadPages()
{

    if($('#rbAdd').prop('checked'))
    {
        location.href='loadGrade';
    }
    else if($('#rbUpdate').prop('checked'))
    {
    location.href='loadGradeUpdate';
    loadGradeSelectBox();
    }
}

function setGradeSelectToSelectBox(gradeList)
{
    $('#gradeValueSelect').append(
        "<option value=0>==select==</option>"
    );
    var text="";
    $.each(gradeList,function (index, grade){
        $('#gradeValueSelect').append(
            text += index +" "+grade+"\n",
        );
        console.log(text);
    });

}
function loadGradeSelectBox()
{var text ="";
    $.ajax({
        url:"/gradeController/getAll",
        type:"GET",
        data:{},

        success:function (data)
        {
            console.log("ok");
            console.log(data.gradeDescription[0]);
        },
        error: function(xhr) {
            alert("Error");
        }
    })
}


function submit()
{
if(checkEmpty()) {
    var gradeCode = document.getElementById("gradeCode").value.toUpperCase();
    var gradeDescription = document.getElementById("gradeDescription").value;

    var requestObj = {
        gradeCode: gradeCode,
        gradeDescription: gradeDescription
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
            alert("success");
        }
    });

}
}
function searchByCode()
{
    var searchCode = document.getElementById("searchGradeCode").value;
    $.ajax({
        url: "/gradeController/getByGradeCode?gradeCode="+searchCode,
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success:function (data){
           alert("success"+data.gradeCode+" "+data.gradeDescription+" "+data.gradeId)
        }

    })
}

function checkEmpty()
{
    var result = true;
    var gradeCode = document.getElementById("gradeCode").value;
    var gradeDes = document.getElementById("gradeDescription").value;

    if(gradeCode=="" || gradeCode==null)
    {
        document.getElementById("divGradeCode").innerHTML="Grade Code is Required";
        result =false;
    }
    if(gradeDes=="" || gradeDes==null)
    {
        document.getElementById("divGradeDescription").innerHTML="Grade Description is Required";
        result = false;
    }
    return result;
}


function clearErrors()
{
    document.getElementById("divGradeCode").innerHTML="";
    document.getElementById("divGradeDescription").innerHTML="";
}