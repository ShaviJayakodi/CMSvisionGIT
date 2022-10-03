


function popup()
{
    newWin = window.open('/loadPopupSearch','Student Inquiry', 'directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=800,height=500');

    document.onmousedown=focusPopup;
    document.onkeyup=focusPopup;
    document.onmousemove=focusPopup;

    function focusPopup(){
        if(!newWin.closed){
            newWin.focus();
        }
    }
    //getStudentDataByRegNo();
}
function setToSelectClass(classList)
{
    console.log(classList);
    $("#selectClass").empty();
    $("#selectClass").append(

        "<option value=null>==Select Class==</option>"
    );
    $.each(classList,function (index,classes){
        $("#selectClass").append(
            "<option value="+classes.classInfoId+">"+classes.classCode+"</option>"
        );
    });
}

function getClassList()
{
  var sId = document.getElementById("studentId").value;
    $.ajax({
        url: "/classMappingController/getMappingsByStudentId?studentId=" + sId,
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success: function (data) {
            setToSelectClass(data);
        },
        error: function (data) {
            alert("Not Class Found For This Student");
        }
    });

}

function getStudentDataByRegNo()
{
    var regNo = document.getElementById("regNo").value;
    $.ajax({
        url:"/studentController/getUniqueByRegNo?regNo="+regNo,
        type:"GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success:function (data)
        {
            document.getElementById("studentId").value=data.studentId;
            document.getElementById("studentName").value=data.firstName+" "+data.lastName;
            alert("Student Found")
        },
        error: function (data) {
            console.log(data.success)
            alert("Invalid Student Register No");
            document.getElementById("regNo").value="";

            setTimeout(function ()
            {
                document.getElementById("regNo").focus();
            });
        },
    });
}

function getStudentAttendance()
{
    var studentId = document.getElementById("studentId").value;
    fromDate =new Date(document.getElementById("fromDate").value);
    //let date = Date;
    toDate =new Date(document.getElementById("toDate").value);
    var classId = document.getElementById("selectClass").value;

    $.ajax({
        url:"/markAttendanceController/getAttendanceReviewForStudentId?studentId="+studentId+"&classId="+classId+"&fromDate="+fromDate+"&toDate="+toDate,
        type:"GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success:function (data)
        {
            console.log("success");
        },
        error: function (data) {
            console.log(data.success)
        },

    });


}