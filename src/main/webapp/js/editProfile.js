function getUserData()
{
    $.ajax({
        url: 'UserProfileServlet',
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function (data) {
            $("#save").prop('disabled', true);
            $("#Uuser").val(data.userName);
            $("#Uuser").prop('disabled', true);
//            // Split timestamp into [ Y, M, D, h, m, s ]
//            var t = data.birthdate.split(/[- :]/);
//           // Apply each element to the Date function
//            var d = new Date(Date.UTC(t[0], t[1] - 1, t[2], t[3], t[4], t[5]));
            $("#Ubirth").val(data.birthdate+"");
            $("#Ubirth").prop('disabled', true);
            $("#Upass").val(data.passWord);
            $("#Upass").prop('disabled', true);
            $("#Uemail").val(data.email);
            $("#Uemail").prop('disabled', true);
            $("#Uaddres").val(data.address);
            $("#Uaddres").prop('disabled', true);
            $("#Ujob").val(data.job);
            $("#Ujob").prop('disabled', true);
            $("#UcreaditE").val(data.creditLimit);
            $("#UcreaditE").prop('disabled', true);
            $("#Uinterests").val(data.interests);
            $("#Uinterests").prop('disabled', true);

        }

    });
}
function EditAction()
{
    $("#save").prop('disabled', false);
    $("#Edit").prop('disabled', true);
    $("#Upass").prop('disabled', false);
    $("#Uaddres").prop('disabled', false);
    $("#Ujob").prop('disabled', false);
    $("#UcreaditE").prop('disabled', false);
    $("#Uinterests").prop('disabled', false);

}

