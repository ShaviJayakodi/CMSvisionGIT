


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
        },
        error: function (data) {
            console.log(data.success)
            notFoundStudent();
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
    fDate =new Date(document.getElementById("fromDate").value);
    //let date = Date;
    tDate =new Date(document.getElementById("toDate").value);
    var classId = document.getElementById("selectClass").value;
    fromDate=formatDate(fDate);
    toDate=formatDate(tDate);
    console.log(fromDate+" "+toDate);
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
            setToAttendanceTable(data);
        },
        error: function (data) {
            console.log(data.success)
            notFoundData();
        },

    });


}

function setToAttendanceTable(attendanceList)
{
    $("#openedClassTableBody").empty();
    $.each(attendanceList, function (index,attendance){
        $("#openedClassTableBody").append(
            "<tr class=\"table-info\">"
            +"<td>"+attendance.classInfo.classCode+"</td>"
            +"<td>"+attendance.attendanceDate+"</td>"
            +"<td>"+attendance.classInfo.grade.gradeDescription+"</td>"
            +"</tr>"
        );
    });
}