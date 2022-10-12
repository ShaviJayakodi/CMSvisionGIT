$(document).ready(function() {
    getAllOfficers();

});
function getAllOfficers()
{
    $.ajax({
        url:"/officerController/getAllOfficers",
        type:"GET",
        data:{},
        success:function (data)
        {
            setOfficerToTable(data);
        },
        error:function (xhr)
        {
            errorAlert();
        }
    });
}

function setOfficerToTable(officerList)
{
    $.each(officerList,function (index,officer){
        $("#officerInqTable").append(
            "<tr>"+
            "<td style=\"display:none;\">"+officer.officerId+"</td>"+
            "<td>"+officer.regNo+"</td>"+
            "<td>"+officer.firstName+" "+officer.lastName+"</td>"+
            "<td>"+officer.mobNum1+"</td>"+
            "<td>"+officer.mobNum2+"</td>"+
            "<td>"+officer.gender+"</td>"+
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
    var table = document.getElementById("officerInqTable"),rIndex;
    for(var i=0;i<table.rows.length; i++)
    {
        table.rows[i].onclick=function ()
        {
            rIndex=this.rowIndex;
            console.log(rIndex);
            console.log(this.cells[1].innerHTML);
            var toPassRegNo = opener.document.getElementById("searchRegNo");
            var toPassStudentId = opener.document.getElementById("officerId");
            var toPassStudentName = opener.document.getElementById("officerName");
            toPassRegNo.value = this.cells[1].innerHTML;
            toPassStudentId.value= this.cells[0].innerHTML;
            toPassStudentName.value=this.cells[2].innerHTML;
            self.close();
        }

    }
}
