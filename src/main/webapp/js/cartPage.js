// var userCart;

//userCart.date = userCart.date.toISOString();

function deleteAProduct(val) {
    var id = $(val).parent().parent().parent().find('input:hidden:first').attr('value');
    var items = userCart.cartItems;
    var itemIndex = items.findIndex(item => item.product.productId == id);

    userCart.cartItems.splice(itemIndex, 1);
    $(val).parent().parent().remove();

}

function increaseQuantity(val) {

    var id = $(val).parent().parent().parent().find('input:hidden:first').attr('value');

    //var quantity = $(val).find('input:text:first').val();
        var items = userCart.cartItems;
        var itemIndex = items.findIndex(item => item.product.productId == id);
        //var newQuantity = userCart.cartItems[itemIndex].quantity + quantity;
        var newQuantity =  userCart.cartItems[itemIndex].quantity + 1;
        if( userCart.cartItems[itemIndex].product.quantity >= newQuantity){

            userCart.cartItems[itemIndex].quantity += 1;

            //set the value of the price in UI
            //var oldPrice = $(val).parent().parent().parent().find('input:text .product-subtotal .amount').text();
            var totalPrice = userCart.cartItems[itemIndex].product.productPrice * userCart.cartItems[itemIndex].quantity;
            $(val).parent().parent().parent().find('.product-subtotal .amount').text(totalPrice);
            $(val).prev('input').val(userCart.cartItems[itemIndex].quantity)
        }else {
            alert("Product " + userCart.cartItems[itemIndex].product.productName + " is out off stock")
        }
            //$(val).find('input:text:first').text(userCart.cartItems[itemIndex].quantity);

    /*    var id = $(this).parent().find('input:hidden:first').attr('id');
    var items = userCart.cartItems;
    var itemIndex = items.findIndex(item = > item.product.productId == id);

    userCart.cartItems[itemIndex].quantity += 1;
    */
}

function decreaseQuantity(val) {

    var id = $(val).parent().parent().parent().find('input:hidden:first').attr('value');

    //var quantity = $(val).find('input:text:first').val();
    var items = userCart.cartItems;
    var itemIndex = items.findIndex(item => item.product.productId == id);
    //var newQuantity = userCart.cartItems[itemIndex].quantity + quantity;
    var newQuantity = userCart.cartItems[itemIndex].quantity - 1;
    if(newQuantity > 0){

        userCart.cartItems[itemIndex].quantity -= 1;
        //set the value of the price in UI
        //var oldPrice = $(val).parent().parent().parent().find('input:text .product-subtotal .amount').text();
        var totalPrice = userCart.cartItems[itemIndex].product.productPrice * userCart.cartItems[itemIndex].quantity;
        $(val).parent().parent().parent().find('.product-subtotal .amount').text(totalPrice);
        $(val).next('input').val(userCart.cartItems[itemIndex].quantity)

    }
    //$(val).find('input:text:first').text(userCart.cartItems[itemIndex].quantity);

    /*    var id = $(this).parent().find('input:hidden:first').attr('id');
    var items = userCart.cartItems;
    var itemIndex = items.findIndex(item = > item.product.productId == id);

    userCart.cartItems[itemIndex].quantity += 1;
    */

    /*var id = $(this).parent().find('input:hidden:first').attr('id');
    var quantity = $(this).find('input:text:first').val();
    if(quantity > 0){
        var items = userCart.cartItems;
        var itemIndex = items.findIndex(item => item.product.productId == id);
        var newQuantity = userCart.cartItems[itemIndex].quantity - quantity;
        if( newQuantity > 0){
            userCart.cartItems[itemIndex].quantity = newQuantity;

            //set the value of the price in UI
            var totalPrice = userCart.cartItems[itemIndex].product.productPrice * newQuantity;
            $(this).parent().find('.product-subtotal span').text(totalPrice);
        }
    }*/
}

function updateCart(val) {
    //FIXING the date format
   /* var testDate = new Date(userCart.date);
    var month = testDate.getMonth() + 1;
    var day = userCart.date.substring(4,5);
    var year = userCart.date.substring(7,11);

    if(day > 10){
        day = "0" + day;

    }    userCart.date = year + "-" + month +  "-" + day;*/
    $.ajax({
        url: 'CartPageServlet',
        type: 'POST',
        data: {
            cart: JSON.stringify(userCart) // look here!
        },
        dataType: 'json',
        success: function (val) {
            if(val.status == 1){
                //window.location.href = "shop-cart.jsp";
                window.location.reload(true);
            }else{
                alert("Cart failed to be updated");
            }
        }
    });
}

