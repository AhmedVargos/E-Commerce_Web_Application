<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <% pageContext.setAttribute("currentPage", "cart"); %>
    <!-- Basic -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Shop | Cart</title>

    <meta name="keywords" content="HTML5 Template"/>
    <meta name="description" content="Porto - Responsive HTML5 Template">
    <meta name="author" content="okler.net">

    <!-- Favicon -->
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon"/>
    <link rel="apple-touch-icon" href="img/apple-touch-icon.png">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- Web Fonts  -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800%7CShadows+Into+Light"
          rel="stylesheet" type="text/css">

    <!-- Vendor CSS -->
    <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="vendor/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="vendor/simple-line-icons/css/simple-line-icons.css">
    <link rel="stylesheet" href="vendor/owl.carousel/assets/owl.carousel.min.css">
    <link rel="stylesheet" href="vendor/owl.carousel/assets/owl.theme.default.min.css">
    <link rel="stylesheet" href="vendor/magnific-popup/magnific-popup.css">

    <!-- Theme CSS -->
    <link rel="stylesheet" href="css/theme.css">
    <link rel="stylesheet" href="css/theme-elements.css">
    <link rel="stylesheet" href="css/theme-blog.css">
    <link rel="stylesheet" href="css/theme-shop.css">
    <link rel="stylesheet" href="css/theme-animate.css">

    <!-- Skin CSS -->
    <link rel="stylesheet" href="css/skins/default.css">

    <!-- Theme Custom CSS -->
    <link rel="stylesheet" href="css/custom.css">

    <!-- Head Libs -->
    <script src="vendor/modernizr/modernizr.js"></script>


    <!-- Vendor -->
    <script src="vendor/jquery/jquery.js"></script>
    <script src="vendor/jquery.appear/jquery.appear.js"></script>
    <script src="vendor/jquery.easing/jquery.easing.js"></script>
    <script src="vendor/jquery-cookie/jquery-cookie.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.js"></script>
    <script src="vendor/common/common.js"></script>
    <script src="vendor/jquery.validation/jquery.validation.js"></script>
    <script src="vendor/jquery.stellar/jquery.stellar.js"></script>
    <script src="vendor/jquery.easy-pie-chart/jquery.easy-pie-chart.js"></script>
    <script src="vendor/jquery.gmap/jquery.gmap.js"></script>
    <script src="vendor/jquery.lazyload/jquery.lazyload.js"></script>
    <script src="vendor/isotope/jquery.isotope.js"></script>
    <script src="vendor/owl.carousel/owl.carousel.js"></script>
    <script src="vendor/magnific-popup/jquery.magnific-popup.js"></script>
    <script src="vendor/vide/vide.js"></script>

    <!-- Theme Base, Components and Settings -->
    <script src="js/theme.js"></script>

    <!-- Theme Custom -->
    <script src="js/custom.js"></script>

    <!-- Theme Initialization Files -->
    <script src="js/theme.init.js"></script>

</head>
<body>

<div class="body">
    <script>
        var userCart;

        function getUserCart() {
            $.ajax({
                url: 'CartPageServlet',
                type: 'GET',
                contentType: 'application/json',
                dataType: 'json',
                success: function (val) {
                    userCart = val;
                }
            });
        }

        getUserCart();
    </script>

    <jsp:include page="header.jsp"></jsp:include>

    <div role="main" class="main shop">

        <div class="container">

            <div class="row">
                <div class="col-md-12">
                    <hr class="tall" style="margin: 100px;">
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">

                    <div class="featured-boxes">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="featured-box featured-box-primary align-left mt-sm">
                                    <div class="box-content">
                                        <form method="post" action="">
                                            <table class="shop_table cart">
                                                <thead>
                                                <tr>
                                                    <th class="product-remove">
                                                        &nbsp;
                                                    </th>
                                                    <th class="product-thumbnail">
                                                        &nbsp;
                                                    </th>
                                                    <th class="product-name">
                                                        Product
                                                    </th>
                                                    <th class="product-price">
                                                        Price
                                                    </th>
                                                    <th class="product-quantity" style="padding-left: 40px">
                                                        Quantity
                                                    </th>
                                                    <th class="product-subtotal">
                                                        Total
                                                    </th>
                                                </tr>
                                                </thead>
                                                <c:if test="${not empty sessionScope.cart.cartItems}">
                                                    <tbody>
                                                    <c:forEach items="${sessionScope.cart.cartItems}" var="cartItem">
                                                        <tr class="cart_table_item">
                                                            <input type="hidden" id="item"
                                                                   value="${cartItem.product.productId}">

                                                            <td class="product-remove">
                                                                <i class="fa fa-times" onclick="deleteAProduct(this)"
                                                                   style="cursor: pointer;"></i>

                                                            </td>
                                                            <td class="product-thumbnail">
                                                                <a href="HomeServlet?id=${cartItem.product.productId}">
                                                                    <img width="100" height="100" alt=""
                                                                         class="img-responsive"
                                                                         src="img/products/product-1.jpg">
                                                                </a>
                                                            </td>
                                                            <td class="product-name">
                                                                <a href="HomeServlet?id=${cartItem.product.productId}">${cartItem.product.productName}</a>
                                                            </td>
                                                            <td class="product-price">
                                                                <span class="amount">${cartItem.product.productPrice}</span>
                                                            </td>
                                                            <td class="product-quantity">
                                                                <div class="quantity">
                                                                    <input type="button" class="minus" value="-"
                                                                           onclick="decreaseQuantity(this)">
                                                                    <input type="text" class="input-text qty text"
                                                                           title="Qty" value="${cartItem.quantity}"
                                                                           name="quantity"
                                                                           min="1"
                                                                           step="1"
                                                                           disabled>
                                                                    <input type="button" class="plus" value="+"
                                                                           onclick="increaseQuantity(this)">
                                                                </div>
                                                            </td>
                                                            <td class="product-subtotal">
                                                                <span class="amount">${cartItem.product.productPrice * cartItem.quantity}</span>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    <tr>
                                                        <td class="actions" colspan="6">
                                                            <div class="actions-continue">
                                                                <input type="button" value="Update Cart"
                                                                       name="update_cart"
                                                                       class="btn btn-default"
                                                                       onclick="updateCart(this)">
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </c:if>
                                            </table>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="actions-continue">
                        <a href="shop-checkout.jsp" class="btn pull-right btn-primary btn-lg">Proceed to Checkout <i
                                class="fa fa-angle-right ml-xs"></i></a>
                    </div>
                </div>
            </div>

        </div>

    </div>

    <jsp:include page="footer.jsp"></jsp:include>

</div>

<!-- Cart Scripts -->
<script src="js/cart.js"></script>
<script src="js/cartPage.js"></script>

<!-- <script src="js/cartPage.js"></script> -->
<!-- Google Analytics: Change UA-XXXXX-X to be your site's ID. Go to http://www.google.com/analytics/ for more information.
<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
    (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
    m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-12345678-1', 'auto');
    ga('send', 'pageview');
</script>
 -->

</body>
</html>
