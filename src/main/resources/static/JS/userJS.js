$(document).ready(function (){
    $("#rbRegistration").prop("checked", true);
   loadRegistrationPage();
    getAllOfficers();
});

function submit()
{
    if($("#rbRegistration").prop("checked")){
       if(isEmpty())
       {
           var passWord = document.getElementById("passWord1").value;
           var userName = document.getElementById("userName").value;
           var officerId = document.getElementById("selectOfficer").value;

           var requestObj =
               {
                   userName: userName,
                   passWord:passWord,
                   officerId:officerId
               }
               $.ajax({
                   url: "/userController/UserSave",
                   type:"POST",
                   eaders: {
                       'Accept': 'application/json',
                       'Content-Type': 'application/json'
                   },
                   dataType:"json",
                   data:JSON.stringify(requestObject),
                   success:function (data)
                   {
                     alert("ok");
                   },

               });


       }




    }


}

function isEmpty()
{
    var result = true;
    var pass1 = document.getElementById("passWord1").value;
    var pass2 = document.getElementById("passWord2").value;
    if(pass1!==pass2)
    {
        document.getElementById("divPassWord1").innerHTML="Password Not Match";
        document.getElementById("divPassWord2").innerHTML="Password Not Match";
        result=false;
    }

return result;
}



function clearErr()
{
    document.getElementById("divPassWord1").innerHTML="";
    document.getElementById("divPassWord2").innerHTML="";
}




function getAllOfficers()
{
$.ajax({
    url:"/officerController/getAllOfficers",
    type:"GET",
    data:{},
    success:function (data)
    {
        setOfficerToSelect(data);
    },
    error:function (xhr)
    {
        alert("Error Loading");
    }
});
}
function  setOfficerToSelect(officers)
{
    $("#selectOfficer").empty();
    $("#selectOfficer").append(
        "<option value = null>==Select Officer==</option>"
    );
    $.each(officers,function (index,officer){
        $("#selectOfficer").append(
            "<option value="+officer.officerId+">"+officer.firstName+" "+officer.lastName+"</option>"
        );
    });

}




function loadRegistrationPage()
{
    $("#mainContainerPage").load("loadUserRegistration/");
    $("#mainContainerPage").value=true;

}