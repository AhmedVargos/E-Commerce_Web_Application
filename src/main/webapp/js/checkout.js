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
}

