function getAllExpense()
{
    let  fDate = new Date(document.getElementById("fromDate").value);
    let tDate = new Date(document.getElementById("toDate").value);
    fromDate = formatDate(fDate);
    toDate = formatDate(tDate);
    $.ajax({
        url: "/incomeController/getAllIncomesBetween2Days?fromDate=" + fromDate + "&toDate=" + toDate,
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            popupAllIncome(data);
        },
        error: function (data) {
            console.log(data.success)
            notFoundData();
        },
    });
}

/*
var newWin;
function popupAllIncome() {
    $("#allIncomeShow").click(function () {
        newWin = window.open('/loadAllIncomePopup', 'Income Inquiry', 'directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=800,height=500');

        document.onmousedown = focusPopup;
        document.onkeyup = focusPopup;
        document.onmousemove = focusPopup;

        function focusPopup(data) {

            if (newWin != null && !newWin.closed) {
                newWin.focus();
                $("#incomeListTableBody").empty();
                $.each(data, function (index, incomes) {
                    $("#incomeListTableBody").append(
                        "<tr>"
                        +"<td>"+incomes.withdraw.teacher.firstName+" "+incomes.withdraw.teacher.lastName+"</td>"
                        +"<td>"+incomes.receivedDate+"</td>"
                        +"<td>"+incomes.amount+"</td>"
                        +"</tr>"
                    );

                });


            }

        }
});
}
*/






function getIncomeData()
{
    let  fDate = new Date(document.getElementById("fromDate").value);
    let tDate = new Date(document.getElementById("toDate").value);
    fromDate = formatDate(fDate);
    toDate = formatDate(tDate);
    $.ajax({
        url: "/incomeController/getIncomeData?fromDate=" + fromDate + "&toDate=" + toDate,
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success: function (data) {

            setData(data);
        },
        error: function (data) {
            console.log(data.success)
            Swal.fire({
                icon: 'error',
                title: 'ERROR',
                text: 'Not found any income or expense',
            })
        },
    });
}

function setData(incomeData)
{
    $("#incomeTableBody").empty();
    $("#expenseTableBody").empty();
    $("#profitTableBody").empty();

        $("#incomeTableBody").append(
        "<tr   style=\"background-color:#38f173 ; font-size: 20px; \">" +
           " <td style=\"width: 250px\">Total Income</td>"+
            " <td style=\"text-align: right\">"+incomeData[0]+".00</td>"+
            //"<td style=\"float:right;\"><button type=\"button\" id=\"allIncomeShow\" onclick=\"getAllExpense();\" class=\"btn btn-primary\">Show All</button></td>"+
        "</tr>"
        );
    $("#expenseTableBody").append(
        "<tr  style=\"background-color:#f5ea87;  font-size: 20px;  \">" +
        " <td style=\"width: 250px\">Total Expense</td>"+
        " <td style=\"text-align: right\">"+incomeData[1]+".00</td>"+
       // "<td style=\"float:right;\"><button type=\"button\"class=\"btn btn-primary\" >Show All</button></td>"+
        "</tr>"
    );
    if(incomeData[2] > 0)
    {
        $("#profitTableBody").append(
            "<tr style=\"font-size: 20px;\" class=\"table-success\">"
            +"<td>This time period company got  "+incomeData[2]+".00 Positive Income</td>"
           +"</tr>"
        );
    }
    else if(incomeData==0)
    {
            $("#profitTableBody").append(
                "<tr style=\"font-size: 20px;\" class=\"table-warning\">>"
                +"<td>This time period company didn't get any profit or lost</td>"
                +"</tr>"
            );
    }
    else
    {
        $("#profitTableBody").append(
            "<tr style=\"font-size: 20px;\"  class=\"table-danger\">"
            +"<td>This time period company got  "+incomeData[2]+".00 Negatvite Income</td>"
            +"</tr>"
        );
    }

}