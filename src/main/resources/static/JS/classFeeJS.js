$(document).ready(function (){
    $("#selectAuthor").hide();
    $("#lblCheck").hide();
    $("#selectAuthor").empty();
    loadAllTeachers();
    $("#collectButton").hide();

});
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
function setToSelectClassByTeacherId(clasList)
{

    $("#selectClass").empty();
    $("#selectClass").append(
        "<option value=null>==Select Class==</option>"
    );
    $.each(clasList,function (index,uClass){
        $("#selectClass").append(
            "<option value="+uClass.classId+">"+uClass.classCode+"</option>"
        );
    });
}

function setToSelectClass(clasList)
{
    $("#selectClass").empty();
    $("#selectClass").append(
        "<option value=null>==Select Class==</option>"
    );
    $.each(clasList,function (index,uClass){
        $("#selectClass").append(
            "<option value="+uClass.classInfoId+">"+uClass.classCode+"</option>"
        );
    });
}

function getAllMappedClassByStudentId()
{
    var studentId = document.getElementById("studentId").value;
    $.ajax({
        url:"/classMappingController/getMappingsByStudentId?studentId="+studentId,
        type:"GET",
        data: {},
        success:function (data)
        {
           setToSelectClass(data);
        },
        error:function (xhr)
        {
            alert("Error");
        }
    });
}
function setDataToFields(data)
{
    document.getElementById("teacher").value=data.classInfo.teacher.firstName+" "+data.classInfo.teacher.lastName;
    document.getElementById("subject").value=data.classInfo.subject.subjectCode+" || "+data.classInfo.subject.subjectName;
    document.getElementById("payMethod").value=data.paymentMethod;
    var amount="";
    if(data.paymentMethod=="Full")
    {
        amount=data.classInfo.fullFee+".00";
    }
    else if(data.paymentMethod=="Half")
    {
        amount=data.classInfo.halfFee+".00";
    }
    else if(data.paymentMethod=="Free")
    {
        amount="FREE OF CHARGE";
    }
    else
    {
        alert("Student Payment Method Not Found")
    }
    document.getElementById("amount").value=amount;
    document.getElementById("classYear").value=data.classYear;
}
function  getPayMethod()
{
    var studentId= document.getElementById("studentId").value;
    var classId = document.getElementById("selectClass").value;
    $.ajax({
        url:"/classFeeController/getPayMethod?studentId="+studentId+"&classId="+classId,
        type:"GET",
        data: {},
        success:function (data)
        {
            setDataToFields(data);
        },
        error:function (xhr)
        {
            alert("Error");
        }

    });

}
function release() {
    if ($("#forRelease").prop("checked")) {
        $("#lblCheck").show();
        $("#selectAuthor").show();
        getAllTeachers();

    }
    else
    {
        $("#lblCheck").hide();
        $("#selectAuthor").hide();
    }
}


function setAlert()
{
    Swal.fire(
        'Good job!',
        'You clicked the button!',
        'success'
    )
}
function  setToTeacherSelectBox(data)
{
    $("#selectAuthor").empty();
    $("#selectAuthor").append(
        "<option value=null>==Select Author By==</option>"
    );
    $.each(data,function (index,teacher){
        $("#selectAuthor").append(
          "<option value="+teacher.teacherId+">"+teacher.firstName+" "+teacher.lastName+"</option>"
        );
    });



}

function  setToSelectTeacher(teacherList)
{
    $("#selectTeacher").empty();
    $("#selectTeacher").append(
        "<option value= null>==Select Teacher==</option>"
    );
    $.each(teacherList,function (index,teacher){
        $("#selectTeacher").append(
            "<option value="+teacher.teacherId+">"+teacher.firstName+" "+teacher.lastName+"</option>"
        );
    });
}

function getAllTeachers()
{
    $.ajax({
        url:"/teacherController/getAllTeachers",
        type:"GET",
        data: {},
        success:function (data)
        {
            setToTeacherSelectBox(data);

        },
        error:function (xhr)
        {
            alert("Error");
        }
    });
}

function getAllClassFeeData()
{
    var studentId = document.getElementById("studentId").value;
    var amount = document.getElementById("amount").value;
    var classInfoId = document.getElementById("selectClass").value;
    var payDate = new Date();
    var month = document.getElementById("selectMonth").value;
    var payMethod = document.getElementById("payMethod").value;
    var authorizedBy = document.getElementById("selectAuthor").value;
    var classYear = document.getElementById("classYear").value;
    var isRelease="";
    if ($("#forRelease").prop("checked")) {
        isRelease = document.getElementById("forRelease").value;
    }
       var requestObj =
        {
            studentId:studentId,
            classId:classInfoId,
            amount:amount,
            isRelease:isRelease,
            payDate:formatDate(payDate),
            month:month,
            payDate:payDate,
            payMethod:payMethod,
            year:classYear,
            authorizedBy:authorizedBy,
        }
    $.ajax({
        url:"/classFeeController/addPayment",
        type:"POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: JSON,
        data: JSON.stringify(requestObj),
        success: function (data) {
            alert("success");
            setAlert();

        },
        error: function (data) {
            console.log(data.success);
            alert("error");

        },
    });

}
function setDateToTable(activeClassFeeList)
{
    $("#activeClassFeeTableBody").empty();
    $.each(activeClassFeeList,function (index,activeFee)
    {
        console.log(activeFee);
        var m = activeFee.month;
        var month="";
        if(m == 1){month = "January";
        }else if(m == 2){month  = "February";
        }else if(m == 3){month  = "March";
        }else if(m == 4){month  = "April";
        }else if(m == 5){month  = "May";
        }else if(m == 6){month  = "June";
        }else if(m == 7){month  = "July";
        }else if(m == 8){month  = "August";
        }else if(m == 9){month  = "September";
        }else if(m == 10){month  = "October";
        }else if(m == 11){month  = "November";}
        else if(m == 12){month  = "December";}

        if(activeFee.isRelease==1||activeFee.payMethod=="Free")
        {
            $("#activeClassFeeTableBody").append(
            "<tr class=\"table-warning\">"
                +"<td style=\"display: none\">"+activeFee.classFeeId+"</td>"
                +"<td><input type=\"checkbox\" style=\"cursor: pointer\" class=\"checkItem form-check-input\" id=\"uniqueRow\" ></td>"
                +"<td>"+activeFee.student.firstName+" "+activeFee.student.lastName+"</td>"
                +"<td>"+formatDate(activeFee.payDate)+"</td>"
                +"<td>"+month+"</td>"
                +"<td style=\"text-align: right;\" >"+activeFee.amount+".00</td>"

            +"</tr>"
            );

        }
        else {
            $("#activeClassFeeTableBody").append(
                "<tr class=\"table-info\">"
                +"<td style=\"display: none\">"+activeFee.classFeeId+"</td>"
                + "<td><input type=\"checkbox\"style=\"cursor: pointer\" class=\"checkItem form-check-input\" id=\"uniqueRow\" ></td>"
                +"<td>" + activeFee.student.firstName + " " + activeFee.student.lastName + "</td>"
                + "<td  >" + formatDate(activeFee.payDate) + "</td>"
                +"<td>"+month+"</td>"
                + "<td style=\"text-align: right;\" >" + activeFee.amount + ".00</td>"

                + "</tr>"
            );
        }


    });
    checkAll();
    /*
    $("#activeClassFeeTableBody").append(
        "<tr>"
        +"<td><input type=\"checkbox\" class=\"form-check-input\" id=\"uniqueRow\" onclick=\"uncheck()\"></td>"+

        +"</tr>"

    );
*/
}
function pay(classFeeIdList)
{ $("#collectButton").click(function ()
    {
        var requestObj =
            {
                classFeeIdList:classFeeIdList
            }
        $.ajax({
            url: "/classFeeController/claimCash",
            type: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType: JSON,
            data: JSON.stringify(requestObj),
            success: function (data) {
                alert("Successfully Registered.")


            },
            error: function (data) {
                console.log(data.success);
                alert("Not Success Please ReTry.")


            }
        });
    });

}
function checkAll()
{
    $("#selectAll").change(function (){
        $(".checkItem").prop("checked",$(this).prop("checked"))
    })
    $(".checkItem").change(function (){
       if($(this).prop("checked")==false){
           $("#selectAll").prop("checked",false)
       }
       if ($(".checkItem:checked").length ==$(".checkItem").length)
       {
           $("#selectAll").prop("checked",true);
       }


    });

}
function getDataFromRow()
{

    var mainArray=[];
        $("#activeClassFeeTable input[type=checkbox]:checked").each(function (){
           var row = $(this).closest("tr")[0];
           var classFeeId=row.cells[0].innerHTML;
           var amount = row.cells[5].innerHTML;
           var object=
               {
                   classFeeId:classFeeId,
                   amount:amount,
               }
               mainArray.push(object);
        });
        claim(mainArray);
}
function claim(list)
{
    $("#totalAmount").empty();
    $("#commission").empty();
    $("#payAmount").value="";
    $("#collectButton").hide();

    if(list.length!=0)
    {
        var fAmount =0;
        var classFeeIdArray=[];
        $.each(list,function (index,ary){

            if(ary.classFeeId=="classFeeId"&& ary.amount=="Amount"){
                console.log("matched")
            }
            else {
              fAmount= fAmount+parseInt(ary.amount);
              classFeeIdArray.push(ary.classFeeId);
            }
        });
        let com = document.getElementById("comPercentage").value;
        let commission = (fAmount * com) / 100;
        let payAmount = fAmount - commission;
        $("#collectButton").show();
        document.getElementById("totalAmount").value=fAmount+".00";
        document.getElementById("commission").value=commission+".00";
        document.getElementById("payAmount").value=payAmount+".00";
        pay(classFeeIdArray);
    }

    else
    {
        alert("Please Select Class Fee To Claim")
    }
}


function getActiveClassFeesForClassId()
{
    var classId = document.getElementById("selectClass").value;
    $.ajax({
        url:"/classFeeController/getActiveClassFeeByClassId?classId="+classId,
        type:"GET",
        data: {},
        success:function (data)
        {
            setDateToTable(data);

        },
        error:function (xhr)
        {
            alert("Error");
        }
    });
}

