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
{
    $.ajax({
        url:"/gradeController/getAll",
        type:"GET",
        data:{},
        success:function (data)
        {
            console.log("ok");
        },
        error: function(xhr) {
            alert("Error");
        }
    });
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
function getGrade(){


        $.ajax({
            url: "/gradeController/getAll",
            type: "GET",
            data: {},
            success: function(data) {
              //  $.each(data,function (index,value){
                   // console.log(`${index}:${value.gradeId}:${value.gradeCode}:${value.gradeDescription}`);
                 /*   var row = $('<tr><td>' + value.gradeId + '</td><td>' + value.gradeCode+ '</td><td>' + value.gradeDescription + '</td></tr>');
                    $("#table-content").append(row);

                    $("#gradeSelect").append(
                        "<option value="+value.gradeId+">"+value.gradeCode+" " +
                        " || "+value.gradeDescription+"</option>"
                    );
                })
              // setGradeListToTable(data.response);
                for (var i=0; i<data.length; i++)
                {
                    var row = $('<tr><td>' + data[i].gradeId + '</td><td>' + data[i].gradeCode+ '</td><td>' + data[i].gradeDescription + '</td></tr>');
                    $("#table-content").append(row);
                }
                  */

                  setGradeListToTable(data);
                 // setGradeSelectToSelectBox(this.data)
                    },
            error: function(xhr) {
                alert("Error");
            }
        });

}
function setGradeSelectToSelectBox(){
    $('#gradeCodeSelect').append(
        "<option value=0>==SELECT==</option>");
    $.each(gradeList, function (index, grade){
        $('#gradeCodeSelect').append(
            "<option value="+grade.gradeId+">"+grade.gradeCode+" " +
            " || "+grade.gradeDescription+"</option>"
        );
    });
}
function setGradeListToTable(gradeList)
{
    //var data = gradeList;
    $('#mainContainer').empty();
    $('#mainContainer').append(
        "<table class=\"table table-striped\" id=\"details\" style=\"background-color:#ffffff; width: 80%;\" align=\"center\">"+
        "<th>Grade Code</th>"+
        "<th>Discription</th>"
    );
    $('#mainContainer').append(
        "</table>"
    );

   /* for (var i=0; i<data.length; i++)
    {
        var row = $('<tr><td>' + data[i].gradeId + '</td><td>' + data[i].gradeCode+ '</td><td>' + data[i].gradeDescription + '</td></tr>');
        $("#table-content").append(row);
    }*/
    $.each(gradeList,function (index,grade){
        $("#gradeSelect").append(
            "<option value="+grade.gradeId+">"+grade.gradeCode+" " +
            " || "+grade.gradeDescription+"</option>"
        );
    });


   /* $.each(gradeList, function (index, grade){
        $('#details').append(
            "<tr>" +
            "<td>"+grade.gradeCode+"</td>"+
            "<td>"+grade.gradeDescription+"</td>"+
            //"<td><button onclick='userEdit("+user.userId+")'>Edit</button><button onclick='userDelete("+user.userId+")'>Delete</button></td>"+
            "</tr>"
        );
    });*/

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