$(document).ready(function() {
    loadAllTeachers();
    getALlOpenedClass()
});

function setTeacherToTable(teacherList)
{
    $("#selectTeacher").append(
       " <option value=null>==Select Teacher==</option>"
    );
    $.each(teacherList,function (index,teacher){
       $("#selectTeacher").append(
           "<option value = "+teacher.teacherId+">"+teacher.firstName+" "+teacher.lastName+"</option>"
       );
    });


}

function  loadAllTeachers()
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
           errorAlert();
        }
    });
}
function setToSelectClass(classList)
{
   $("#selectClass").empty();
   $("#selectClass").append(
       "<option value=null>==Select Class==</option>"
   );
   $.each(classList,function (index,uniqueClass){
       $("#selectClass").append(
       "<option value ="+uniqueClass.classId+">"+uniqueClass.classCode+"</option>"
       );
});
}

function  loadAllClassByTeacherId()
{

    var teacherId=document.getElementById("selectTeacher").value;
    $.ajax({
        url:"/classController/getAllClassByTeacher?teacherId="+teacherId,
        type:"GET",
        data: {},
        success:function (data)
        {
           setToSelectClass(data);
        },
        error:function (xhr)
        {
            errorAlert();
        }
    });
}

function openClass()
{
    var classId = document.getElementById("selectClass").value;
    var openDate = formatDate(Date.now());

    var reqestObj =
        {
            classInfoId:classId,
            openDate:openDate
        }
        console.log(reqestObj);
    $.ajax({
       url:"/openClassController/openClass",
       type:"POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType:"json",
        data: JSON.stringify(reqestObj),
        success:function (data)
        {
            Swal.fire(
                'Class Opened Successfully',
                data.classCode,
                'success'
            );
            getALlOpenedClass();

        },
        error:function (data)
        {
            console.log(data.success);
            errorAlert();
            getALlOpenedClass();

        },
    });
}
function checkOpened(rowList)
{

        var errorMg = "";
        if (document.getElementById("selectTeacher").value == "" || document.getElementById("selectTeacher").value == "null") {
            var m1 = document.getElementById("selectTeacher").value;
            console.log(m1);
            errorMg = "Teacher Not Selected";
        } else if (document.getElementById("selectClass").value == "" || document.getElementById("selectClass").value == "null") {
            errorMg = "Class Not Selected";
        }
        if (errorMg == "") {
            var message =1;
            if(rowList.length == 0)
            {
                message=message;
            }

            $.each(rowList, function (index, row) {
                var cId = document.getElementById("selectClass").value;
                if (row.classInfoId != cId) {
                   message=message;
                }
                else
                {
                    message= message +1;
                    message="Class Is Already Opened";


                }


            }

            );
            if(message==1)
            {
                openClass();
            }
            else
            {

                Swal.fire({
                    icon: 'error',
                    title: 'ERROR',
                    text: message,
                });
            }
        } else {
            alert(errorMg);
            Swal.fire({
                icon: 'error',
                title: 'ERROR',
                text: errorMg,
            });

        }


}
function getClassTableData() {
    var mainarry = getTableDataAsArray('openedClassTable', [0, 1, 2, 3]);
    var rowArray = [];
    for (var x = 0; x < mainarry.length; x++) {
        var subarry = mainarry[x];
        var jsonObject = {
            openClassId: subarry[0],
            openDate: subarry[1],
            classInfoId: subarry[2],
            classCode: subarry[3]
        }
        rowArray.push(jsonObject);
    }
    console.log(rowArray);
    checkOpened(rowArray);


}



function setOpenedClassTable(classList)
{
    console.log(classList);
    $("#openedClassTableBody").empty();
    $.each(classList,function (index,classes){
       $("#openedClassTableBody").append(
           "<tr>"+
           "<td style=\"display:none;\">"+classes.openClassId+"</td>"
           +"<td style=\"display:none;\">"+classes.openDate+"</td>"
           +"<td style=\"display:none;\">"+classes.classInfo.classId+"</td>"
           +"<td>"+classes.classInfo.classCode+"</td>"
           +"<td><button type=\"button\" onclick=\"getDataToClose()\" class=\"btn btn-danger btn-sm mr-2\"style=\"width:fit-content;align-content: center; justify-content: center; \">Close</button>"
           +"</tr>"
       );
    });



}

function getALlOpenedClass()
{
    $.ajax({
        url:"/openClassController/getAllOpenedClass",
        type:"GET",
        data: {},
        success:function (data)
        {
            setOpenedClassTable(data);
        },
        error:function (xhr)
        {
          errorAlert();
        }
    });
}

/*
* function getDataToDelete()
{
    var table = document.getElementById("tableClass"),rIndex;
    for (var  i =0; i<table.rows.length; i++) {
        table.rows[i].onclick = function () {
            rIndex = this.rowIndex;
            var classMappingId = this.cells[0].innerHTML;
            var classId = this.cells[1].innerHTML;
            if(classMappingId>0) {
                deleteByMappingId(classMappingId);
            }
        }
    }
}*/

function closeClass(classData)
{
    var reqestObj =
        {
            openClassId:classData.openClassId,
            classInfoId:classData.classInfoId,
            openDate:classData.openDate
        }
        console.log(reqestObj);

    $.ajax({
        url:"/openClassController/openClass",
        type:"POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType:"json",
        data: JSON.stringify(reqestObj),
        success:function (data)
        {
            Swal.fire(
                'Class Closed Successfully',
                data.classCode,
                'success'
            );
            getALlOpenedClass();
        },
        error:function (data)
        {
            console.log(data.success);
            errorAlert();
            getALlOpenedClass();

        },
    });
}



function getDataToClose()
{
    var table =document.getElementById("openedClassTable"),rIndex;
    for(var i = 0; i<table.rows.length; i++)
    {
        table.rows[i].onclick=function ()
        {
            rIndex = this.rowIndex;
            var openClassId = this.cells[0].innerHTML;
            var openDate =this.cells[1].innerHTML;
            var classInfoId =this.cells[2].innerHTML;
            var classCode =this.cells[3].innerHTML;
            var requestObj =
                {
                    openClassId:openClassId,
                    openDate:openDate,
                    classInfoId:classInfoId,
                    classCode:classCode
                }
            closeClass(requestObj);
        }
    }
}