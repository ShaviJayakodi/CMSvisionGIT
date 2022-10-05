$(document).ready(function (){
    loadRegistrationPage();
   loadAllExpense();
});



function addExpense() {
    var errorMsg = "";
    if (document.getElementById("selectExpense").value == "null" || document.getElementById("selectExpense").value == "") {
        errorMsg += "Expense Type Not Selected!";
    } else if (document.getElementById("expenseValue").value == null || document.getElementById("expenseValue").value == "") {
        errorMsg += "Expense Value Not Valid!";
    }

    if (errorMsg == "") {
        var expenseInfoId = document.getElementById("selectExpense").value;
        var description = document.getElementById("expenseDescription").value;
        var value = document.getElementById("expenseValue").value;
        var requestObj= {
            expenseInfoId:expenseInfoId,
            description:description,
            value:value,
            date:formatDate(new Date())

        }
        $.ajax({
            url: "/addExpenseController/addExpense",
            type: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType: JSON,
            data: JSON.stringify(requestObj),
            success: function (data) {
                alert("Successfully Registered.")
                loadRegistrationPage();

            },
            error: function (data) {
                console.log(data.success);
                alert("Not Success Please ReTry.")
                loadRegistrationPage();

            }
        });

    } else {
        alert(errorMsg);
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

        },
        error: function(xhr) {
            alert("Error");
        }

    });
}

function setToTable(list)
{
    $("#expenseInqTableBody").empty();
    $.each(list,function (index,expense){
        $("#expenseInqTableBody").append(
            "<tr>" +
            "<td>"+expense.expenseInfo.code+"</td>" +
            "<td>"+expense.expenseInfo.expenseTitle+"</td>" +
            "<td>"+expense.date+"</td>" +
            "<td>"+expense.value+".00</td>" +
            "</tr>"
        );
    });

}

function loadAllExpenseList()
{

    $.ajax({
        url: "/addExpenseController/getAllExpense",
        type: "GET",
        data: {},
        success: function(data) {
           setToTable(data);

        },
        error: function(xhr) {
            alert("Error");
        }

    });
}
function loadRegistrationPage()
{
    $("#mainContainerPage").load("loadAddExpense/");
    $("#mainContainerPage").value=true;
}

function inquiry()
{
    $("#mainContainerPage").load("loadAddExpenseDetails");
    $("#mainContainerPage").value=true;
    loadAllExpenseList();
}