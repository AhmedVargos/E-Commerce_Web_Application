/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function addToCart(product) {
    //get product object
    //sedd ajax request with product obj
    var productId = $("#myProduct").val();
    var objProduct = {"id": productId};
     $.ajax({
        url: 'CartServlet',
        type: 'POST',
        contentType: 'application/json',
        data: objProduct,
        dataType: 'json',
        success: updateCartCounter
    });


//    $.post("CartServlet", {"id": productId}, updateCartCounter);

//    alert(product);
//    alert("inaddcart");
}

function updateCartCounter() {
    //update cart counter
    console.log("Success");
}