$(document).ready(function() {
    loadAllGradeList();


});


function loadAllGradeList()
{
    $.ajax({
        url: "/gradeController/getAll",
        type: "GET",
        data: {},
        success: function(data) {
            console.log(data)
            setGradeList(data);
        },
        error: function(xhr) {
            alert("Error");
        }

    });
}

function setGradeList(gradeList)
{
    $("#gradeSelect").empty();
    $("#gradeSelect").append(
        "<option value=null>==Select Grade==</option>"
    );
    $.each(gradeList,function (index,grade){
        $("#gradeSelect").append(
            "<option value="+grade.gradeId+">"+grade.gradeCode+"||"+grade.gradeDescription+"</option>"
        );
    });
}

function setToTable(studentList)
{
    console.log(studentList);
    $("#studentList").empty();
    $.each(studentList,function (index,student){
        $("#studentList").append(
            "<tr>"
            +"<td style=\"display:none;\" >"+student.studentId+"</td>"
            +"<td>"+student.regNo+"</td>"
            +"<td>"+student.firstName+" "+student.lastName+"</td>"
            +"<td>"+student.school+"</td>"
            +"<td>"+student.mob1+"</td>"
            + "<td><button type=\"button\" onclick=\"getDataToDelete(this,removeClass(this))\" class=\"btn btn-danger btn-sm\"style=\"width:30px; margin-right: 20px;\">...</button>"
            +"</tr>"
        );

    });
    searchFromPopup();
    getUniqueRowValue();
}

function getStudentListByGradeId()
{
    var gradeId=document.getElementById("gradeSelect").value;
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
            setToTable(data);
        },

    });
}
function searchFromPopup()
{
    const searchInput = document.getElementById("searchName");
    const searchRegInput = document.getElementById("searchRegNo");
    const searchSchoolInput = document.getElementById("searchSchool");
    const rows = document.querySelectorAll("tbody tr");
    searchInput.addEventListener("keyup" , function (event) {
        const q = event.target.value.toLowerCase();
        rows.forEach((row) => {

             row.querySelector("td:nth-child(3)").textContent.toLowerCase().startsWith(q)
             ? row.style.display = ''
             : (row.style.display = "none");
        });
    });
    searchRegInput.addEventListener("keyup" , function (event){
        const q= event.target.value.toLowerCase();
        rows.forEach((row) => {
            row.querySelector("td:nth-child(2)").textContent.toLowerCase().startsWith(q)
                ? row.style.display=''
                :(row.style.display="none");
        });
    });
    searchSchoolInput.addEventListener("keyup" , function (event){
       const q = event.target.value.toLowerCase();
        rows.forEach((row) => {
            row.querySelector("td:nth-child(4)").textContent.toLowerCase().startsWith(q)
                ? row.style.display = ''
                : (row.style.display = "none");
        });
    });

}

function getUniqueRowValue()
{
    var table = document.getElementById("inquiryTable"),rIndex;
    for(var i=0;i<table.rows.length; i++)
    {
        table.rows[i].onclick=function ()
        {
            rIndex=this.rowIndex;
            console.log(rIndex);
            console.log(this.cells[1].innerHTML);
            var toPassRegNo = opener.document.getElementById("regNo");
            var toPassStudentId = opener.document.getElementById("studentId");
            var toPassStudentName = opener.document.getElementById("studentName");
               toPassRegNo.value = this.cells[1].innerHTML;
               toPassStudentId.value= this.cells[0].innerHTML;
               toPassStudentName.value=this.cells[2].innerHTML;
            self.close();
        }

    }
}