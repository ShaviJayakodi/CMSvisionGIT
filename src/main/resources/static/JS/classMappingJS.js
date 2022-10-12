$(document).ready(function() {
    $("#rbRegistration").prop("checked", true);
        loadRegistrationPage();
});


function submit(tableObj)
{
    $("#addClssButton").hide();
    $("#rbRegistration").prop("checked")
    {
        var studentId =document.getElementById("studentId").value;
        //var classMappingData = getClassTableData();

        var requestObj =
                 {
                studentId:studentId,
                classInfoId:tableObj.classId,
                classYear:tableObj.classYear,
                //classCode:tableObj.classCode,
                paymentMethod: tableObj.paymentMetod,
                attendDate : Date.now()

                }

            }
            console.log(requestObj);
       $.ajax({
            url:"/classMappingController/saveClassMap",
            type:"POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType: "json",
            data: JSON.stringify(requestObj),
            success: function (data) {
                    Swal.fire(
                        'Class Added Successfully',
                        'Student Mapping to '+data.classCode,
                        'success'
                    );
                    getClassDetailsByStudentId();
            },
            error: function (data) {
                errorAlert();
                getClassDetailsByStudentId();

            },
        });


}


function getUniqueStudentByRegNo()
{
    var regNo = document.getElementById("regNo").value;
    $.ajax({
        url:"/studentController/getUniqueByRegNo?regNo="+regNo,
        type:"GET",
        data:{},
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success:function (data)
        {
            document.getElementById("studentId").value=data.studentId;
            document.getElementById("studentName").value=data.firstName+" "+data.lastName;
            setStudentDataToFields(data);

        },
        error:function (data)
        {
            errorAlert();
        }
    });
}
function setStudentDataToFields(uniqueStudent)
{
    console.log(uniqueStudent);
    document.getElementById("studentId").value=uniqueStudent.studentId;
    document.getElementById("grade").value= uniqueStudent.grade.gradeCode+" || "+uniqueStudent.grade.gradeDescription;
    document.getElementById("gradeIdForStudent").value = uniqueStudent.grade.gradeId;
    getAllClass();
}
function getUniqueStudentById()
{
    var studentId= document.getElementById("studentId").value;

    $.ajax({
        url:"/studentController/getUniqueById?studentId="+studentId,
        type:"GET",
        data:{},
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success:function (data)
        {
            setStudentDataToFields(data)
        }
    });
}

function getAllClass()
{
    var gradeId = document.getElementById("gradeIdForStudent").value;

    $.ajax({
        url:"/classController/getClassesByGradeId?gradeId="+gradeId,
        type:"GET",
        data: {},
        success:function (data)
        {
            setClassSelectBox(data);
        },
        error:function (xhr)
        {
            errorAlert();
        }
    });
}

function setClassSelectBox(classList)
{

    console.log(classList);
    $("#selectClass").empty();
    $("#selectClass").append(
        "<option value = null>==Select Class==</option>"
    );

    $.each(classList,function (index,uniqueClass){
    $("#selectClass").append(
        "<option value="+uniqueClass.classId+">"+uniqueClass.classCode+"</option>"
    );
});

}


var newWin;
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

}
function getClassDetailsByStudentId()
{
    getUniqueStudentByRegNo()
    $("#addBtn").show();
    $("#mappingInqTable").empty();
    $("#classTableBody").empty();

    var studentId= document.getElementById("studentId").value;

    $.ajax({
        url:"/classMappingController/getMappingsByStudentId?studentId="+studentId,
        type:"GET",
        data: {},
        success:function (data)
        {
            setDataToTable(data);
        },
        error:function (xhr)
        {
           errorAlert();
        }
    });
}
function setDataToTable(classDetails)
{   console.log(classDetails);
    $.each(classDetails, function (index,classMap){

        $("#mappingInqTable").append(
            "<tr>"
            +"<td>"+classMap.classCode+"</td>"
            +"<td>"+classMap.paymentMethod+"</td>"
            +"<td>"+formatDate(classMap.attendDate)+"</td>"
            +"</tr>"
        );
        $("#classTableBody").append(
            "<tr>" +
            "<td style=\"display:none;\">"+classMap.classMappingId+ "</td>"
            +"<td style=\"display:none;\">"+classMap.classInfoId+ "</td>"
            +"<td>" + classMap.classCode + "</td>"
            + "<td>" + classMap.paymentMethod + "</td>"
            + "<td>" + classMap.classYear + "</td>"
            + "<td><button type=\"button\" onclick=\"getDataToDelete(this)\" class=\"btn btn-danger btn-sm mr-2\"style=\"width:30px;\">-</button>"

            + "</tr>"
        );
    });

}

function getClassByClassId(classId)
{
    $.ajax({
        url:"/classController/getClassByClassId?classId="+classId,
        type:"GET",
        data: {},
        success:function (data)
        {
            setClassToTable(data);

        },
        error:function (xhr)
        {
          errorAlert();
        }
    });
}
function getDataFromRow()
{
    var table = document.getElementById("tableClass"),rIndex;
    for(var i=0; i<table.rows.length; i++)
    {
        table.rows[i].onclick=function ()
        {
            rIndex=this.rowIndex;
            var classMappingId = this.cells[0].innerHTML;
            var classId = this.cells[1].innerHTML;
            var classCode = this.cells[2].innerHTML;
            var paymentMetod = this.cells[3].innerHTML;
            var classYear = this.cells[4].innerHTML;
            var requestObj=
                {
                    classId:classId,
                    classCode:classCode,
                    paymentMetod:paymentMetod,
                    classYear:classYear
                }
            submit(requestObj);

        }
    }
}
function deleteByMappingId(mappingId)
{
    $.ajax({
      url:"/classMappingController/deleteMappingByMappingId?mappingId="+mappingId,
      type:"DELETE",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType:"json",
        success:function (data) {
                Swal.fire(
                    'Deleted!',
                    'Your file has been deleted.',
                    'success'
                );
                getClassDetailsByStudentId();
        },

        error: function (data) {
           errorAlert();
            getClassDetailsByStudentId();
        },

    });
}
function getDataToDelete()
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
}


function setClassToTable(classInfo) {
    var getArray = getTableDataAsArray('tableClass', [0, 1, 2, 3,4]);
    var rowArray = [];
    for (var x = 0; x < getArray.length; x++) {
        var subarray = getArray[x];
        var jsonObject = {
            classMappingId:subarray[0],
            classInId: subarray[1],
            classCode: subarray[2],
            paymentType: subarray[3],
            classYear: subarray[4]
        }
        rowArray.push(jsonObject);
    }

        console.log(jsonObject);
        console.log(rowArray);
        var errorMessage = "";
        if (document.getElementById("paymentMethod").selectedIndex == 0) {
            Swal.fire({
                icon: 'error',
                title: 'ERROR',
                text: 'Please Select Payment Method',
            });
        } else if (document.getElementById("classYear").selectedIndex == 0) {
            Swal.fire({
                icon: 'error',
                title: 'ERROR',
                text: 'Please Select classYear',
            });
        } else {
            var paymentMethod = document.getElementById("paymentMethod").value;
            var classYear = document.getElementById("classYear").value;
            var mappingId =0;
            var mainArray = getTableDataAsArray("tableClass", [1]);
            for (var x = 0; x < mainArray.length; x++) {
                var subArray = mainArray[x][0];
                if (mainArray[x][0] == classInfo.classId) {
                    errorMessage = "Class Is Already Added";
                }
            }
            if (errorMessage == "") {
                $("#classTableBody").append(

                    "<tr>" +
                    "<td style=\"display:none;\">"+mappingId+"</td>"
                    +"<td style=\"display:none;\">" + classInfo.classId + "</td>"
                    + "<td>" + classInfo.classCode + "</td>"
                    + "<td>" + paymentMethod + "</td>"
                    + "<td>" + classYear + "</td>"
                    + "<td><button type=\"button\" onclick=\"getDataToDelete(this,removeClass(this))\" class=\"btn btn-danger btn-sm\"style=\"width:30px; margin-right: 20px;\">-</button>" +
                    "<button type=\"button\" id=\"addClssButton\" onclick=\"getDataFromRow(this)\" class=\"btn btn-primary btn-sm\"style=\"width:30px;\">+</button></td>"
                    + "</tr>"
                );
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'ERROR',
                    text: errorMessage,
                });
            }
        }


}

function getClassTableData() {
        var mainarry = getTableDataAsArray('tableClass', [0, 1, 2, 3]);
        var rowArray = [];
        for (var x = 0; x < mainarry.length; x++) {
            var subarry = mainarry[x];
            var jsonObject = {
                classInId: subarry[0],
                classCode: subarry[1],
                paymentType: subarry[2],
                classYear: subarry[3]
            }
            rowArray.push(jsonObject);
        }
        console.log(rowArray);
        setD(rowArray);

    }

function removeClass(oButton) {
        var tableName = 'tableClass';
        var cellNo = '1';
        var clssId = getTableClickValue(tableName, cellNo, oButton);

    }

function getClassIn() {
        var classId = document.getElementById("selectClass").value;
        var studentId = document.getElementById("studentId").value;
        if(studentId>0) {
            getClassByClassId(classId);
        }
        else
        {
            Swal.fire({
                icon: 'error',
                title: 'ERROR',
                text: 'Please Select A Student',
            });

        }
    }

function inquiry() {
        $("#mainContainerPage").load("loadClassMappingDetails/");
        $("#mainContainerPage").value = true;
    }

function loadRegistrationPage() {
        $("#mainContainerPage").load("loadAddMapping/");
        $("#mainContainerPage").value = true;



}