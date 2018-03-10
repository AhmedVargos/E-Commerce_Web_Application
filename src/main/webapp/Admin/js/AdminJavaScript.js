function viewDetails(id) {
    $.get(
        "/AdminUserDetailsServlet",
        {userId: id},
        function (user) {
            $("#table").toggle("slow");
            $("#userId").val(user.userId);
            $("#userName").val(user.userName);
            $("#userEmail").val(user.email);
            $("#userBDate").val(user.birthdate);
            $("#userJob").val(user.job);
            $("#userAddress").val(user.address);
            $("#userCredit").val(user.creditLimit);
            $("#viewForm").fadeIn("slow");
        });
}

function closeForm() {
    $("#viewForm").toggle("slow");
    $("#table").fadeIn("slow");
}

function deleteProduct(productId) {
    $.ajax({
        url: '/AdminProductsServlet' + '?' + $.param({"productId": productId}),
        type: 'DELETE',
        success: function () {
            $('#' + productId).remove()
        }
    });
}

function viewProduct(productId) {
    $.get(
        "/AdminEditProductServlet",
        {id: productId},
        function (data) {
            $("#table").toggle("slow");
            $("#productId").val(data.product.productId);
            $("#productCategories").empty();
            for (var i = 0; i < data.categories.length; i++) {
                $("#productCategories").append('<option value="' + data.categories[i].categoryId + '">' + data.categories[i].categoryName + '</option>');
            }
            $("#productName").val(data.product.productName);
            $("#productDescription").val(data.product.description);
            $("#productPrice").val(data.product.productPrice);
            $("#productQuantity").val(data.product.quantity);
            $("#viewForm").fadeIn("slow");
        });
}