function getUserData()
{
    $.ajax({
        url: 'UserProfileServlet',
        type: 'GET',
        contentType: 'application/json',
        success: function (data) { 
            $("#save").prop('disabled', true);
            $("#Uuser").prop('disabled', true);
            $("#Ubirth").prop('disabled', true);
            $("#Upass").prop('disabled', true);
            $("#Uemail").prop('disabled', true);
            $("#Uaddres").prop('disabled', true);
            $("#Ujob").prop('disabled', true);
            $("#Ucreadit").prop('disabled', true);
            $("#Uinterests").prop('disabled', true);
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

