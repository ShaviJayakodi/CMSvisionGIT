function validatePhoneNumber(numb,validationDiv){
    var phoneno = /^\(?([0-9]{3})\)?[-. ]?([0-9]{7})$/;
    if(numb.match(phoneno)) {
        return true;
    }
    else{
        document.getElementById(validationDiv).innerHTML ="Invaliad Phone Number";
        return false;
    }

}
function codeValidation(objText){
    var res = objText.value.toUpperCase();
    objText.value = res	;
}
function formatPhoneNumber(numbtxtFild,validationDiv){
    var numb = document.getElementById(numbtxtFild).value;
    var phoneno1 = /^\(?([0-9]{3})\)?([-])?([0-9]{7})$/;
    var phoneno2 = /^\?([0-9]{10})$/;
    if(numb.match(phoneno1)){
        if(numb.length==10){
            var newnumb = numb.substring(0, 3)+"-"+ numb.substring(3, 11);
            document.getElementById(numbtxtFild).value =newnumb;
        }
        document.getElementById(validationDiv).innerHTML ="";
    }

    else{
        document.getElementById(validationDiv).innerHTML ="Invaliad Phone Number";
    }
}


function formatDate(objDate){
    var date = new Date(objDate);
    var month = date.getMonth() + 1;
    if (month<10){
        month ="0"+month;
    }
    var day = date.getDate();
    if (day<10){
        day ="0"+day;
    }
    var year = date.getFullYear();
    var dat = year + "-" + month + "-" + day;
    return dat;
}
function formatCurrancy(numb){
    var str1 ="";
    var str2 ="00.";
    var str3 ="";
    for(var i =  numb.length; i >=0; i--){
        str1=str1+numb.charAt(i);
    }

    for(var i = 0; i< str1.length; i++){
        if(i>0 && (i)%3 ==0){
            str2 = str2+","+str1.charAt(i);
        }
        else{
            str2 = str2+str1.charAt(i);
        }
    }
    for(var i =  str2.length; i >=0; i--){
        str3=str3+str2.charAt(i);
    }

    return str3;
}
function unformattingCurrancy(currncy){
    var str1="";
    for(var i = 0; i< currncy.length; i++){

        if(currncy.charAt(i)==","){

        }
        else{
            str1 = str1+currncy.charAt(i);
        }
    }
    return str1;
}
function getTableClickValue(tableName,cellNo,oButton){
    var intRow = oButton.parentNode.parentNode.rowIndex;
    var returnValue="";
    var intCell = Number(cellNo);
    var tbl = document.getElementById(tableName);
    if (tbl != null) {
        returnValue = tbl.rows[intRow].cells[intCell].innerHTML;
        tbl.deleteRow(intRow)
    }
    return  returnValue;
}

function getTableDataAsArray(tableName,cellNumbers){
    var arrayMain= [];
    var tbl = document.getElementById(tableName);
    var rows = tbl.getElementsByTagName("tr");

    for (var i = 1; i < rows.length; i++) {
        var arrayRowData = [];
        if (rows[i].getElementsByTagName("td").length > 0) {
            for(var y = 0; y < cellNumbers.length; y++){
                arrayRowData.push(tbl.rows[i].cells[cellNumbers[y]].innerHTML);
            }

        }
        arrayMain.push(arrayRowData);
    }

    return arrayMain;
}

function tableFilter(inputId,tableName,columnIndex) {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById(inputId);
    filter = input.value.toUpperCase();
    table = document.getElementById(tableName);
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[columnIndex];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}
function startTime() {
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    document.getElementById('txt').innerHTML =
        h + ":" + m + ":" + s;
    var t = setTimeout(startTime, 500);
}
function checkTime(i) {
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}
function getTime(){
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    var time =h + ":" + m + ":" + s;

    return time;
}

