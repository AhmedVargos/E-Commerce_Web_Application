/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function addToCart(val) {
    //get product object
    //send ajax request with product obj
    var productId = $(val).prev().prev().val();//$(val).find('#myProduct').val();
    var productQuantity =$(val).prev().val(); //$(val).find('#myQuantity').val();

    //var productId = $("#myProduct").val();
    //var productQuantity = $("#myQuantity").val();

    $.post("CartServlet",{"id": productId, "quantity":productQuantity},updateCartCounter);

}

function updateCartCounter() {
    //update cart counter
    console.log("Success");
}