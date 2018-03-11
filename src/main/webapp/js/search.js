
function searchProducts() {
    var name = $("#search_text").val();
    var objSearch = { "name": name,"category": catId};
    $.ajax({
        url: 'SearchServlet',
        type: 'GET',
        contentType: 'application/json',
        data: objSearch,
        dataType: 'json',
        success: listProducts
    });
}

//Check the returned list of products
function listProducts(val) {
    var products = val;

    //If empty remove the page counter and add text No reaults found
    $("#list_of_products li").remove();
    if(products.length == 0){
        $('#no_result_text').remove();
        $('#page_footer').remove();
        $("#list_of_products").css("height","auto")
        $('#products_container').append('<h2 id="no_result_text" class="mb-none">No Results Found.</h2>\n')
    }

    //Remove the old items and add new items
    for (var i = 0; i < val.length; i++) {
        $('#no_result_text').remove();
        $('#list_of_products').append('<li class="col-md-3 col-sm-6 col-xs-12 product">\n' +
            '\t\t\t\t\t\t\t\t<span class="product-thumb-info">\n' +
            '\t\t\t\t\t\t\t\t\t<a href="shop-cart.jsp" class="add-to-cart-product">\n' +
            '\t\t\t\t\t\t\t\t\t\t<span><i class="fa fa-shopping-cart"></i> Add to Cart</span>\n' +
            '\t\t\t\t\t\t\t\t\t</a>\n' +
            '\t\t\t\t\t\t\t\t\t<a href="HomeServlet?id='+ products[i].productId + '">\n' +
            '\t\t\t\t\t\t\t\t\t\t<span class="product-thumb-info-image">\n' +
            '\t\t\t\t\t\t\t\t\t\t\t<span class="product-thumb-info-act">\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t<span class="product-thumb-info-act-left"><em>View</em></span>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t<span class="product-thumb-info-act-right"><em><i\n' +
            '                                                        class="fa fa-plus"></i> Details</em></span>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t</span>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t<img alt="" class="img-responsive" src="img/products/product-2.jpg" style="width: 320px; height: 320px">\n' +
            '\t\t\t\t\t\t\t\t\t\t</span>\n' +
            '\t\t\t\t\t\t\t\t\t</a>\n' +
            '\t\t\t\t\t\t\t\t\t<span class="product-thumb-info-content">\n' +
            '\t\t\t\t\t\t\t\t\t\t<a href=""HomeServlet?id='+ products[i].productId +'">\n' +
            '\t\t\t\t\t\t\t\t\t\t\t<h4>'+ products[i].productName +'</h4>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t<span class="price">\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t<span class="amount">' + products[i].productPrice + '</span>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t</span>\n' +
            '\t\t\t\t\t\t\t\t\t\t</a>\n' +
            '\t\t\t\t\t\t\t\t\t</span>\n' +
            '\t\t\t\t\t\t\t\t</span>\n' +
            '                            </li>');
    }


}
