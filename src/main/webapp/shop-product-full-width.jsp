<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <!-- Basic -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Shop | Porto - Responsive HTML5 Template 4.3.1</title>

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



</head>
<body>

<div class="body">
    <jsp:include page="header.jsp"></jsp:include>
    <div role="main" class="main shop">

        <div class="container">

            <div class="row">
                <div class="col-md-12">
                    <hr class="tall">
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">

                    <div class="owl-carousel owl-theme" data-plugin-options='{"items": 1}'>
                        <div>
                            <div class="thumbnail">
                                <img alt="" class="img-responsive img-rounded" src="img/products/product-7.jpg">
                            </div>
                        </div>

                    </div>

                </div>

                <div class="col-md-6">

                    <div class="summary entry-summary">

                        <h1 class="mb-none"><strong>${sessionScope.PRODUCT_DETAIL.productName}</strong></h1>



                        <p class="price">
                            <span class="amount">${sessionScope.PRODUCT_DETAIL.productPrice}</span>
                        </p>

                        <p class="taller"></p>

                        <form enctype="multipart/form-data" method="post" class="cart">
                            <div class="quantity">
                                <input type="button" class="minus" value="-" onclick="decreaseQuantity()">
                                <input id="added_quantity" type="text" class="input-text qty text" title="Qty" value="1" name="quantity"
                                       min="1" step="1" disabled>
                                <input type="button" class="plus" value="+" onclick="increaseQuantity()">
                            </div>
                            <button href="#" class="btn btn-primary btn-icon" onclick="updateCart(${sessionScope.PRODUCT_DETAIL.productId})">Add to cart</button>
                        </form>

                        <div class="product_meta">
                            <span class="posted_in">Category: ${sessionScope.PRODUCT_DETAIL.catagory_catogeryId} .</span>
                        </div>

                    </div>


                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="tabs tabs-product">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#productDescription" data-toggle="tab">Description</a></li>
                            <!--<li><a href="#productInfo" data-toggle="tab">Additional Information</a></li>
                            <li><a href="#productReviews" data-toggle="tab">Reviews (2)</a></li>-->
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="productDescription">
                                <p>${sessionScope.PRODUCT_DETAIL.description}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <hr class="tall">


                </div>
            </div>
        </div>

    </div>

    <footer id="footer">
        <div class="container">
            <div class="row">
                <div class="footer-ribbon">
                    <span>Get in Touch</span>
                </div>

            </div>
        </div>
        <div class="footer-copyright">
            <div class="container">
                <div class="row">
                    <div class="col-md-1">
                        <a href="index.html" class="logo">
                            <img alt="Porto Website Template" class="img-responsive" src="img/logo-footer.png">
                        </a>
                    </div>
                    <!--   <div class="col-md-7">
                           <p>© Copyright 2015. All Rights Reserved.</p>
                       </div>-->
                    <!--      <div class="col-md-4">
                           <nav id="sub-menu">
                               <ul>
                                   <li><a href="#">FAQ's</a></li>
                                   <li><a href="#">Sitemap</a></li>
                                   <li><a href="#">Contact</a></li>
                               </ul>
                           </nav>
                       </div>-->
                </div>
            </div>
        </div>
    </footer>
</div>
<!-- Theme Base, Components and Settings -->
<script src="js/theme.js"></script>

<!-- Theme Custom -->
<script src="js/custom.js"></script>

<!-- Theme Initialization Files -->
<script src="js/theme.init.js"></script>

<!-- Product Details Scripts -->
<script src="js/productDetails.js"></script>


</body>
</html>
