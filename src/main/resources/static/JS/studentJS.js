
$(document).ready(function() {
        $("#rbRegistration").prop("checked", true);
        loadRegistrationPage();

});


function submit() {
        if ($("#rbRegistration").prop("checked"))
        {
                var firstName = document.getElementById("firstName").value;
                var lastName = document.getElementById("lastName").value;
                var birthDay = document.getElementById("birthDay").value;
                var attendDate = document.getElementById("attendDate").value;
                var address = document.getElementById("address").value;
                var school = document.getElementById("school").value;
                var gradeId = document.getElementById("gradeSelect").value;
                var gender = "";
                if ($("#male").prop("checked")) {
                        gender = document.getElementById("male").value;
                } else if ($("#female").prop("checked")) {
                        gender = document.getElementById("female").value;
                }
                var requestObj =
                    {
                            firstName: firstName,
                            lastName: lastName,
                            birthDay: birthDay,
                            attendDate: attendDate,
                            address: address,
                            school: school,
                            gradeId: gradeId,
                            gender: gender

                            }
                        console.log(requestObj);
                $.ajax({
                        url: "/studentController/saveStudent",
                        type: "POST",
                        headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json'
                        },
                        dataType:JSON,
                        data: JSON.stringify(requestObj),
                        success:function (data)
                        {
                                if(!data.success) {
                                        alert(data.statusList);
                                } else {
                                        alert("Successfully Registered.")
                                }
                        },
                        error: function(data){
                                console.log(data.success);

                        }
                });
                }


}

function loadRegistrationPage()
{       gradeLoad();
        $("#mainContainerPage").load("loadStudentRegistration/");
}

function loadUpdatePage()
{
        $("#mainContainerPage").load("loadStudentUpdate/");
        $("#mainContainerPage").value=true;
}
function loadDeletePage()
{
        $("#mainContainerPage").load("loadStudentDelete/");
        $("#mainContainerPage").value=true;
}

function  inquiry()
{
        $("#mainContainerPage").load("loadStudentInquiry/");
        $("#mainContainerPage").value=true;
}

function setGradeListToSelectBox(gradeList)
{
        $("#gradeSelect").append(
            "<option value=0>==SELECT==</option>"
        );
        $.each(gradeList,function (index,grade){
                $("#gradeSelect").append(
                         "<option value="+grade.gradeId+">"+grade.gradeCode+" || "+grade.gradeDescription.toUpperCase()+"</option>"
                );
        });
}



function gradeLoad()
{
        $.ajax({
        url: "/gradeController/getAll",
        type: "GET",
        data: {},
        success: function(data) {
                setGradeListToSelectBox(data);
                },
                error: function(xhr) {
                        alert("Error");
                }

        });
}

