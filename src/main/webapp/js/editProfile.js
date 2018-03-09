function getUserData()
{
    $.ajax({
        url: 'UserProfileServlet',
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data) {
            $("#save").prop('disabled', true);
            $("#Uuser").prop('disabled', true);
            $("#Uuser").val(data.userName);
            $("#Ubirth").prop('disabled',true);
//           var now = data.birthdate;
//          var day = ("0" + now.getDate()).slice(-2);
//           var month = ("0" + (now.getMonth() + 1)).slice(-2);
//           var today = now.getFullYear() + "-" + (month) + "-" + (day);
// document.getElementById("Ubirth").valueAsDate =new Date();
            $("#Ubirth").val(data.birthdate);
            $("#Upass").prop('disabled', true);
            $("#Upass").val(data.passWord);
            $("#Uemail").prop('disabled', true);
            $("#Uemail").val(data.email);
            $("#Uaddres").prop('disabled', true);
            $("#Uaddres").val(data.address);
            $("#Ujob").prop('disabled', true);
            $("#Ujob").val(data.job);
            $("#Ucreadit").prop('disabled', true);
            $("#Ucreadit").val(data.creaditLimit);
            $("#Uinterests").prop('disabled', true);
            $("#Uinterests").val(data.interests);
        }

    });
}
function EditAction()
{
    $("#save").prop('disabled', false);
    $("#Edit").prop('disabled', true);
    $("#Ubirth").prop('disabled', false);
    $("#Upass").prop('disabled', false);
    $("#Uaddres").prop('disabled', false);
    $("#Ujob").prop('disabled', false);
    $("#Ucreadit").prop('disabled', false);
    $("#Uinterests").prop('disabled', false);

}

