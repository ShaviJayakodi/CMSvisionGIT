function submit()
{
    var userName = document.getElementById("userName").value;
    var birthDay = document.getElementById("birthDay").value;
    var pass = document.getElementById("pass").value;

    var reqestObj={
        userName:userName,
        birthDay:birthDay,
        pass:pass
    }
    $.ajax({
        url:"/UserSave",
        type:"POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType:"json",
        data:JSON.stringify(reqestObj),
        success:function (data)
        {
            alert("success");
        }
});

}

function inquiry()
{
    var userId = document.getElementById("userId").value;

    $.ajax({
        url:"/search?userId="+userId,
        type: "GET",
        headers: {
             'Accept': 'application/json',
             'Content-Type': 'application/json'
         },
        dataType: "json",
        success:function (data)
        {
            $('#details').append(
                "<table id=\"tableUser\">"+
                "<thead>" +
                "<tr>" +
                "<th>User Name</th>"+
                "<th>Password</th>"+
                "<th>Birth Day</th>"+
                "</tr>"+
                "</thead>"+
                "<tbody id=\"userTableBody\" name=\"userTableBody\">" +
                "<tr>" +
                "<td>"+data.userName+"</td>"+
                "<td>"+data.pass+"</td>"+
                "<td>"+data.birthDay+"</td>"+
                "</tr>"+
                "</tbody>"+
                "</table>"
            );
        }
    }

    );





}

function checkById()
{
    var userId1= document.getElementById("userId").value;
    $.ajax({
        url:"/search?userId="+userId1,
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success:function (data)
        {
                document.getElementById("getUserID").value=data.userId;
                document.getElementById("getUserName").value=data.userName;
                document.getElementById("getUserPass").value=data.pass;
        }
    });

}

function update()
{
    var userId = document.getElementById("getUserID").value;
    var userName = document.getElementById("getUserName").value;
    var birthDay = document.getElementById("gbday").value;
    var pass = document.getElementById("getUserPass").value;

    var reqestObj={
        userId:userId,
        userName:userName,
        birthDay:birthDay,
        pass:pass
    }
    $.ajax({
        url:"/UserSave",
        type:"POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType:"json",
        data:JSON.stringify(reqestObj),
        success:function (data)
        {
            alert("success");
        }
    });

}

function Delete(){
    var userId2 = document.getElementById("userId").value;

    /*$.ajax({
        url:"/UserDelete?userId="+userId2,
        type:"GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success:function (data)
        {
            alert("Deleted")
        }
    });*/
    $.ajax({
        url: "/UserDelete?userId="+userId2,
        type:"DELETE",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType:"json",

        success: function(data) {

                alert("Successfully Delete.")

        },
        error:function (data)
        {
          alert("Not Found")
            console.log(data.success)
        },

    });
}