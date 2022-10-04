$(document).ready(function() {
    $("#rbRegistration").prop("checked", true);
    loadRegistrationPage();
});

function submit()
{

    if ($("#rbRegistration").prop("checked")) {

            var expenseTitle = document.getElementById("expenseTitle").value;
            var description = document.getElementById("expenseDescription").value;


            var requestObj =
                {
                    expenseTitle: expenseTitle,
                    description: description
                }
            $.ajax({
                url: "/expenseController/saveExpense",
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
        }

    if($("#rbUpdate").prop("checked"))
    {
        var expenseTitle = document.getElementById("expenseTitle").value;
        var description = document.getElementById("expenseDescription").value;
        var expenseInfoId = document.getElementById("expenseId").value;
        var code = document.getElementById("code").value;

        var requestObj =
            {
                expenseTitle: expenseTitle,
                description: description,
                expenseInfoId:expenseInfoId,
                code:code
            }
        $.ajax({
            url: "/expenseController/saveExpense",
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
    }

    if($("#rbDelete").prop("checked"))
    {
        var expenseInfoId = document.getElementById("expenseId").value;
        $.ajax({
            url:"/expenseController/deleteExpense?expenseInfoId="+expenseInfoId,
            type:"DELETE",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType:"json",
            success:function (data)
            { if (!data.success) {
                alert(data.statusList);
            } else {
                alert("Successfully Deleted!");
                loadDeletePage();
            }
            },
            error: function (data) {
                console.log(data.success);
                loadDeletePage();

            },


        });

    }







}
function setToSelectExpense(list)
{
    $("#selectExpense").empty();
    $("#selectExpense").append(
        "<option value=null>==Select Expense==</option>"
    );
    $.each(list,function (index,expense){
        $("#selectExpense").append(

        "<option value = "+expense.expenseInfoId+">"+expense.code+" || "+expense.expenseTitle+"</option>"
        );
    });

}

function loadAllExpense()
{

        $.ajax({
            url: "/expenseController/getAllExpense",
            type: "GET",
            data: {},
            success: function(data) {
               setToSelectExpense(data);
               setTable(data);
            },
            error: function(xhr) {
                alert("Error");
            }

        });
}

function  setDataToFields(data)
{

    document.getElementById("expenseTitle").value=data.expenseTitle;
    document.getElementById("expenseDescription").value=data.description;
    document.getElementById("expenseId").value=data.expenseInfoId;
    document.getElementById("code").value=data.code;
}


function getByExpenseId()
{

        var expenseInfoId = document.getElementById("selectExpense").value;
        $.ajax({
            url:"/expenseController/getByExpenseId?expenseInfoId="+expenseInfoId,
            type:"GET",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType: "json",
            success:function (data)
            {
                setDataToFields(data);
            }
        });


}
function setTable(ExList) {
    $("#expInqTableBody").empty();

    $.each(ExList, function (index, ex) {
        $("#expInqTableBody").append(
            "<tr>" +
            "<td>" + ex.code + "</td>" +
            "<td>" + ex.expenseTitle + "</td>" +
            "</tr>"
        );
    });
}


function loadRegistrationPage()
{
    $("#mainContainerPage").load("loadExpenseRegistration/");
    $("#mainContainerPage").value=true;

}

function loadUpdatePage()
{
    $("#mainContainerPage").load("loadExpenseUpdate/");
    $("#mainContainerPage").value=true;
    loadAllExpense()
}
function loadDeletePage()
{
    $("#mainContainerPage").load("loadExpenseDelete/");
    $("#mainContainerPage").value=true;
    loadAllExpense()
}

function inquiry()
{
    $("#mainContainerPage").load("loadExpenseInquiry/");
    $("#mainContainerPage").value=true;
    loadAllExpense();
}