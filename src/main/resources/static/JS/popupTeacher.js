$(document).ready(function() {
    getAllTeachers();

});

function getAllTeachers()
{
    $.ajax({
        url:"/teacherController/getAllTeachers",
        type:"GET",
        data: {},
        success:function (data)
        {
            setTeacherToTable(data);

        },
        error:function (xhr)
        {
            Swal.fire({
                icon: 'error',
                title: 'ERROR',
                text: 'Something went wrong!',
            });
        }
    });
}
function setTeacherToTable(teacherList)
{
    $.each(teacherList,function (index,teacher)
    {
        $("#teacherInqTable").append(

            "<tr>" +
            "<td style=\"display:none;\" >"+teacher.teacherId+"</td>"+
            "<td>"+teacher.regNO+"</td>"+
            "<td>"+teacher.firstName+" "+teacher.lastName+"</td>"+
            "<td>"+teacher.mobNum1+"</td>"+
            "<td>"+teacher.mobNum2+"</td>"+
            "<td>"+teacher.address+"</td>"+
            "</tr>"
        );

    });
    searchTeacherFromPopup();
    getUniqueRowValue();

}

function searchTeacherFromPopup()
{
    const searchInput = document.getElementById("name");
    const searchRegInput = document.getElementById("regNo");
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
            var toPassStudentId = opener.document.getElementById("officerId");
            var toPassStudentName = opener.document.getElementById("teacherName");
            toPassRegNo.value = this.cells[1].innerHTML;
            toPassStudentId.value= this.cells[0].innerHTML;
            toPassStudentName.value=this.cells[2].innerHTML;
            self.close();
        }

    }
}
