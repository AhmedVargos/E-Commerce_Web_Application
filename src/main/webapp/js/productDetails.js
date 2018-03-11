

function increaseQuantity() {
    var newQuantity =  parseInt($('#added_quantity').val());
    var tempQ = newQuantity + 1;
    if(tempQ <= productLimit){
        newQuantity++;
        $('#added_quantity').val(newQuantity);
    }
}

function decreaseQuantity() {
    var newQuantity =  $('#added_quantity').val() - 1;
    if(newQuantity > 0){
        $('#added_quantity').val(newQuantity);
    }
}

function updateCart(val) {
    var productQuantity = $('#added_quantity').val();
    $.post("CartServlet",{"id": val, "quantity":productQuantity},updateCartCounter);
    }


function updateCartCounter() {
    //update cart counter
    window.location.href = "shop-cart.jsp";
}
