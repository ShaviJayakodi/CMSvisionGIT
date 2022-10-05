function login()
{
    var userName = document.getElementById("userName").value;
    var passWord = document.getElementById("passWord").value;

    $.ajax({
        url: "/loginController/login?userName=" + userName + "&passWord=" + passWord,
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success: function (data) {
            console.log("success");
        },
        error: function (data) {
            console.log(data.success)
        }
    });
}