
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

function loadAllTeachers()
{

    $.ajax({
        url:"teacherController/getAllTeachers",
        type:"GET",
        data: {},
        success:function (data)
        {
           setToSelectTeacher(data);
        },
        error:function (xhr)
        {
            alert("Error");
        }
    });
}
function loadAllClassByTeacherId()
{
    var teacherId=document.getElementById("selectTeacher").value;

    $.ajax({
        url:"/classController/getAllClassByTeacher?teacherId="+teacherId,
            type:"GET",
            data: {},
            success:function (data)
            {
                setToSelectClassByTeacherId(data);
            },
            error:function (xhr)
            {
                alert("Error");
            },


    });
}