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

function updateDatab()
{
    $.ajax({
        url: 'CheckoutServlet',
        type: 'GET',
        contentType: 'application/json',
        success: function (data) { 
           $("#totalUpdated").html(data.totalPrice);
           $("#UserTotoal").html(data.userCurrent);
           $("#AddressTotal").html(data.userAddress);
           
        }     
    });         
}

