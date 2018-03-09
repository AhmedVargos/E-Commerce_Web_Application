<%--
  Created by IntelliJ IDEA.
  User: David Emil
  Date: 3/7/2018
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

</head>
<body>
<header id="header"
        data-plugin-options='{"stickyEnabled": true, "stickyEnableOnBoxed": true, "stickyEnableOnMobile": true, "stickyStartAt": 57, "stickySetTop": "-57px", "stickyChangeLogo": true}'>
    <div class="header-body">
        <div class="header-container container">
            <div class="header-row">
                <div class="header-column">
                    <div class="header-logo">
                        <a href="AllProductsServlet">
                            <img alt="Porto" width="111" height="54" data-sticky-width="82" data-sticky-height="40"
                                 data-sticky-top="33" src="img/logo.png">
                        </a>
                    </div>
                </div>
                <div class="header-column">
                    <div class="header-row">
                        <div class="header-search hidden-xs">
                            <form id="searchForm" action="#" method="get">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="q" id="q" placeholder="Search..."
                                           required>
                                    <span class="input-group-btn">
													<button class="btn btn-default" type="submit"><i
                                                            class="fa fa-search"></i></button>
												</span>
                                </div>
                            </form>
                        </div>
                        <nav class="header-nav-top">
                            <ul class="nav nav-pills">
                                <li class="hidden-xs">
                                    <a href="#"><i class="fa fa-angle-right"></i> About Us</a>
                                </li>
                                <li class="hidden-xs">
                                    <a href="#"><i class="fa fa-angle-right"></i> Contact Us</a>
                                </li>
                                <li>
                                    <span class="ws-nowrap"><i class="fa fa-phone"></i> (123) 456-789</span>
                                </li>
                            </ul>
                        </nav>
                    </div>
                    <div class="header-row">
                        <div class="header-nav">
                            <button class="btn header-btn-collapse-nav" data-toggle="collapse"
                                    data-target=".header-nav-main">
                                <i class="fa fa-bars"></i>
                            </button>
                            <div class="header-nav-main header-nav-main-effect-1 header-nav-main-sub-effect-1 collapse">
                                <nav>
                                    <ul class="nav nav-pills" id="mainNav">
                                        <li class="dropdown active">
                                            <a class="dropdown-toggle" href="AllProductsServlet">
                                                Shop Home
                                            </a>

                                        </li>
                                        <li class="dropdown dropdown-mega">
                                            <a class="dropdown-toggle" href="shop-login.jsp">
                                                Login
                                            </a>

                                        </li>
                                        //here we go...
                                        <li class="dropdown">
                                            <a class="dropdown-toggle" href="#">
                                                Categories
                                            </a>

                                            <ul class="dropdown-menu">
                                                <li>
                                                    <a href="CategoryServlet?category=1">
                                                        Women Clothes
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="CategoryServlet?category=2">
                                                        Men Clothes
                                                    </a>
                                                </li>

                                                <li>
                                                    <a href="CategoryServlet?category=3">
                                                        Casual Clothes
                                                    </a>
                                                </li>

                                                <li>
                                                    <a href="CategoryServlet?category=4">
                                                        Sport Clothes
                                                    </a>
                                                </li>

                                            </ul>
                                        </li>
                                        <li class="dropdown">
                                            <a class="dropdown-toggle" href="shop-cart.jsp">
                                                Cart
                                            </a>

                                        </li>
                                        <li class="dropdown">
                                            <a class="dropdown-toggle" href="shop-checkout.jsp">
                                                Checkout
                                            </a>

                                        </li>
                                        <li class="dropdown">
                                            <a class="dropdown-toggle" href="#">
                                                About Us
                                            </a>

                                        </li>
                                        <li class="dropdown">
                                            <a class="dropdown-toggle" href="#">
                                                Contact Us
                                            </a>
                                        </li>
                                        <%--class="dropdown dropdown-mega dropdown-mega-shop"--%>
                                        <li  id="headerShop">
                                            <%--class="dropdown-toggle"--%>
                                            <a  href="page-login.jsp">
                                                <i ></i> Cart (1)
                                            </a>
                                            <%--<ul class="dropdown-menu">--%>
                                                <%--<li>--%>
                                                    <%--<div class="dropdown-mega-content">--%>
                                                        <%--<table class="cart">--%>
                                                            <%--<tbody>--%>
                                                            <%--<tr>--%>
                                                                <%--<td class="product-thumbnail">--%>
                                                                    <%--<a href="shop-product-sidebar.jsp">--%>
                                                                        <%--<img width="100" height="100" alt=""--%>
                                                                             <%--class="img-responsive"--%>
                                                                             <%--src="img/products/product-1.jpg">--%>
                                                                    <%--</a>--%>
                                                                <%--</td>--%>
                                                                <%--<td class="product-name">--%>
                                                                    <%--<a href="shop-product-sidebar.jsp">Photo--%>
                                                                        <%--Camera<br><span--%>
                                                                                <%--class="amount"><strong>$299</strong></span></a>--%>
                                                                <%--</td>--%>
                                                                <%--<td class="product-actions">--%>
                                                                    <%--<a title="Remove this item" class="remove"--%>
                                                                       <%--href="#">--%>
                                                                        <%--<i class="fa fa-times"></i>--%>
                                                                    <%--</a>--%>
                                                                <%--</td>--%>
                                                            <%--</tr>--%>
                                                            <%--<tr>--%>
                                                                <%--<td class="actions" colspan="6">--%>
                                                                    <%--<div class="actions-continue">--%>
                                                                        <%--<button type="submit"--%>
                                                                                <%--class="btn btn-default">View All--%>
                                                                        <%--</button>--%>
                                                                        <%--<button type="submit"--%>
                                                                                <%--class="btn pull-right btn-primary">--%>
                                                                            <%--Proceed to Checkout <i--%>
                                                                                <%--class="fa fa-angle-right ml-xs"></i>--%>
                                                                        <%--</button>--%>
                                                                    <%--</div>--%>
                                                                <%--</td>--%>
                                                            <%--</tr>--%>
                                                            <%--</tbody>--%>
                                                        <%--</table>--%>
                                                    <%--</div>--%>
                                                <%--</li>--%>
                                            <%--</ul>--%>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br><br><br><br><br><br><br><br><br><br><br><br><br>
</header>


</body>
</html>
