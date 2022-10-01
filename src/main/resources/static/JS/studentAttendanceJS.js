$(document).ready(function() {

});
/*
function getClassDetailsByStudentId()
{
    var studentId = document.getElementById("studentId").value;
    $.ajax({
        url:"/classMappingController/getMappingsByStudentId?studentId="+studentId,
        type:"GET",
        data: {},
        success:function (data)
        {
            setMappingTable(data);
        },

        error:function (xhr)
        {
            alert("Error");
        },

    });
}
function getClassTableData() {
    var mainarry = getTableDataAsArray('openedClassTable', [0, 1]);
    var rowArray = [];
    for (var x = 0; x < mainarry.length; x++) {
        var subarry = mainarry[x];
        var jsonObject = {
            classId: subarry[0],
            openClassId: subarry[1],
        }
        rowArray.push(jsonObject);
    }
    console.log(rowArray);
    check(rowArray);

}
function setClassToTable(classList)
{
    $.each(classList,function (index,clss){
        console.log(clss);
        $("#openedClassTableBody").append(
            "<tr>" +
            "<td>"+clss.classInfo.classId+"</td>" +
            "<td>"+clss.openClassId+"</td>" +
            "</tr>"
        );
    });
}
function setMappingTable(studentMappingList)
{
    $("#studentMappingTableBody").empty();
    $.each(studentMappingList, function (index,student){
       $("#studentMappingTableBody").append(
         "<tr>" +
           "<td>"+student.classInfoId+"</td>" +
           "<td>"+student.classCode+"</td>" +
           "<td>"+student.paymentMethod+"</td>" +
           "</tr>"
       );
    });
    getClassTableData();
}
function getAllOpenedClass()
{
    $.ajax({
        url:"/openClassController/getAllOpenedClass",
        type:"GET",
        data: {},
        success:function (data)
        {
            setClassToTable(data);
        },
        error:function (xhr)
        {
            alert("Error");
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

        }
    });
}

function getDetailsByOpenClassID(classArray)
{
    var setArray =[];
   for(i=0; i < classArray.length; i++)
   {
        $.ajax({
            url:"/openClassController/openClassGetById?openClassId="+classArray[i],
            type:"GET",
            data: {},
            success:function (data)
            {
               setArray.push(data);
            },

            error:function (xhr)
            {
                alert("Error");
            },


        });

    }
    console.log(setArray);
}

function setToSelectTable(list)
{
    console.log(list);
    var classId = list.classInfo.classId;
    var classCode = list.classInfo.classCode;

    var studentId = document.getElementById("studentId").value;
    var attendaceDate= Date.now();
    $.each(list ,function (index,OpenClassData) {
        $("getMappedClassTableBody").append(

           "<tr>" +
            "<td><"+classId+"/td>"
            +"<td>"+OpenClassData.openClassId+"</td>"
            +"<td>"+classCode+"</td>"
            +"<td>"+attendaceDate+"</td>"
            +"<td>"+studentId+"</td>"
            +"</tr>"
        );
});




    }


    function check(classList)
        {
            var mainarry = getTableDataAsArray('studentMappingTable', [0]);
            var rowArray = [];
            for (var x = 0; x < mainarry.length; x++) {
                var subarry = mainarry[x];
                var jsonObject = {
                    classId: subarry[0],

                }
                rowArray.push(jsonObject);
            }
            var newArray=[];
            $.each(classList,function (index,classUnique)
            {
                $.each(rowArray,function (index, mappedClass) {
                    if (classUnique.classId ==mappedClass.classId)
                    {
                        newArray.push(classUnique.openClassId);
                    }

                });
            });
            getDetailsByOpenClassID(newArray);

        }
*/

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
            alert("Invalid Student Register No");
            //document.getElementById("regNo").value.innerHTML="";
            document.getElementById("regNo").value="";

            setTimeout(function ()
            {
                document.getElementById("regNo").focus();
            });
        },
    });
}

function setToTableToSelect(openedClassList)
{
    $.each(openedClassList,function (index,list){
        $("#openedClassTableBody").append(
            "<tr>" +
            "<td>"+list.openClassId+"</td>" +
            "<td>"+list.classInfo.classId+"</td>" +
            "<td>"+list.classInfo.classCode+"</td>" +
            "<td>"+list.openDate+"</td>" +
            "<td>SELECT</td>" +
            "</tr>"
        );
    });
}


function getSelectedMappedClassOpenForStudent()
{
    var regNo = document.getElementById("regNo").value;
    $.ajax({
        url: "/markAttendanceController/getByRegNo?regNo=" + regNo,
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success: function (data) {
            setToTableToSelect(data);
        },
        error: function (data) {
            console.log(data.success)
        },
    });

}

function popup()
{
    newWin = window.open('/loadPopupSearch','Student Inquiry', 'width=800,height=500');
}
