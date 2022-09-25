$(document).ready(function() {
    $("#rbRegistration").prop("checked", true);
    loadRegistrationPage();
});


function submit()
{
    if($("#rbRegistration").prop("checked"))
    {
        var hallCode = document.getElementById("hallCode").value.toUpperCase();
        var hallName = document.getElementById("hallName").value.charAt(0).toUpperCase() +
            document.getElementById("hallName").value.substring(1).toLowerCase();
        var floor = document.getElementById("floor").value;
        var requestObj=
            {
                hallCode:hallCode,
                hallName:hallName,
                floor:floor
            }
    console.log(requestObj);
            $.ajax({
                url:"hallController/saveHall",
                type:"POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                dataType:"json",
                data:JSON.stringify(requestObj),
                success:function (data)
                {
                    if (!data.success) {
                        alert(data.statusList);
                    } else {
                        alert("Successfully Registered.")
                    }
                },
                error: function (data) {
                    console.log(data.success);

                }
            });
    }
    if($("#rbUpdate").prop("checked"))
    {
        var hallId = document.getElementById("hallId").value;
        var hallCode = document.getElementById("hallCode").value;
        var hallName = document.getElementById("hallName").value;
        var floor = document.getElementById("floor").value;
        var requestObj =
            {
                hallId:hallId,
                hallCode:hallCode,
                hallName:hallName,
                floor:floor
            }
            $.ajax({
                url:"hallController/saveHall",
                type:"POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                dataType:"json",
                data:JSON.stringify(requestObj),
                success:function (data)
                {
                    if (!data.success) {
                        alert(data.statusList);
                    } else {
                        alert("Successfully Registered.")
                    }
                },
                error: function (data) {
                    console.log(data.success);

                }
            });
    }

    if($("#rbDelete").prop("checked"))
    {
        var hallId = document.getElementById("hallId").value;
        $.ajax({
            url:"/hallController/deleteHall?hallId="+hallId,
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
                gradeLoad();
            }
            },
            error: function (data) {
                console.log(data.success);
                loadDeletePage();

            },


        });


    }
}

function getAllHalls()
{
    $.ajax({
        url: "/hallController/getAllHalls",
        type: "GET",
        data: {},
        success:function (data)
        {
            setHallSelectBox(data);

        },
        error:function (xhr)
        {
            alert("Error");
        }


    });
}

function getDataById()
{
    var hallId = document.getElementById("selectHall").value;
    $.ajax({
        url:"/hallController/getByHallId?hallId="+hallId,
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

function setDataToFields(uniqueHall) {
    document.getElementById("hallId").value = uniqueHall.hallId;
    document.getElementById("hallCode").value = uniqueHall.hallCode;
    document.getElementById("hallName").value = uniqueHall.hallName;
    document.getElementById("floor").value = uniqueHall.floor;
}

function setHallSelectBox(hallList)
{
   $("#selectHall").append(
       "<option value='null'>==SELECT==</option>"
   );
   $.each(hallList,function (index,hall){

       $("#selectHall").append(

           "<option value="+hall.hallId+">"+hall.hallCode+" || "+hall.hallName+"</option>"

       );

   });

}




function loadRegistrationPage()
{
    $("#mainContainerPage").load("loadHallRegistration/");
    $("#mainContainerPage").value=true;
}

function loadUpdatePage()
{
    $("#mainContainerPage").load("loadHallUpdate/");
    $("#mainContainerPage").value=true;
    getAllHalls();
}

function loadDeletePage()
{
    $("#mainContainerPage").load("loadHallDelete/");
    $("#mainContainerPage").value=true;
    getAllHalls();
}

