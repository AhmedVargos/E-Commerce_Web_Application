function checkData()
{
    $.ajax({
        url: 'CheckoutServlet',
        type: 'POST',
        success: function (data) { 
            $("#responseE").append("<label>"+data+"</label>");
            $("#orderE").prop('disabled', true);
        }
        
    });  
}
function updateDataEdit()
{
    $.ajax({
        url: 'CheckoutServlet',
        type: 'GET',
        dataType: 'json',
        success: function (data) { 
           $("#totalUpdated").html(data.totalPrice+"");
           $("#totalUpdated2").html(data.totalPrice+"");
           $("#UserTotoal").val(data.userCurrent);
           $("#AddressTotal").val(data.userAddress);
           $("#UserTotoal").prop('disabled', true);
           $("#AddressTotal").prop('disabled', true);
        }     
    });         
}
