/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function checkData()
{
    $.ajax({
        url: 'CheckoutServlet',
        type: 'POST',
        contentType: 'application/json',
        success: function (data) { 
            $("#response").append("<label>"+data+"</label>");
            $("#order").prop('disabled', true);
        }
        
    });  

//    $.ajax({ 
//    type: 'GET', 
//    url: 'http://example/functions.php', 
//    data: { get_param: 'value' }, 
//    dataType: 'json',
//    success: function (data) { 
//        $.each(data, function(index, element) {
//            $('body').append($('<div>', {
//                text: element.name
//            }));
//        });
//    }
//});
}

