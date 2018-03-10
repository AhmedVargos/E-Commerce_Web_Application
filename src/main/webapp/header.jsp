<%--
  Created by IntelliJ IDEA.
  User: David Emil
  Date: 3/7/2018
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
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

</head>
<body>
<header id="header"
        data-plugin-options='{"stickyEnabled": true, "stickyEnableOnBoxed": true, "stickyEnableOnMobile": true, "stickyStartAt": 57, "stickySetTop": "-57px", "stickyChangeLogo": true}'>
    <div class="header-body">
        <div class="header-container container">
            <div class="header-row">
                <div class="header-column" style="padding:30px">
                    <div class="header-logo">
                        <a href="shop-full-width.jsp">
                            <img alt="Porto" width="111" height="54" data-sticky-width="82" data-sticky-height="40"
                                 data-sticky-top="33" src="img/logo.png">
                        </a>
                    </div>
                </div>
                <div class="header-column">
                    <div class="header-row">

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
                                        <li>
                                            <a  href="shop-full-width.jsp">
                                                Shop Home
                                            </a>

                                        </li>

                                        <c:if test="${not  sessionScope.loggedin}">
                                            <li>
                                                <a href="shop-login.jsp">
                                                    Login
                                                </a>

                                            </li>
                                        </c:if>

                                        <li class="dropdown">
                                            <a class="dropdown-toggle" href="#">
                                                Categories
                                            </a>
                                            <c:if test="${not empty sessionScope.categoryList}">
                                            <ul class="dropdown-menu">
                                                <c:forEach items="${sessionScope.categoryList}" var="category">
                                                    <li>
                                                        <a href="CategoryServlet?category=${category.categoryId}">
                                                            ${category.categoryName}
                                                        </a>
                                                    </li>
                                                </c:forEach>

                                            </ul>
                                            </c:if>
                                        </li>

                                        <li>
                                            <a href="shop-checkout.jsp">
                                                Checkout
                                            </a>

                                        </li>

                                        <li  id="headerShop">
                                            <%--class="dropdown-toggle"--%>
                                            <a  href="shop-cart.jsp">
                                                <i ></i> Cart
                                            </a>
                                        </li>
                                        <%----%>
                                    <c:if test="${sessionScope.loggedin}">
                                        <li>
                                            <a href="shop-user-profile.jsp">
                                                Profile
                                            </a>
                                        </li>

                                        <li>
                                            <a href="/LogoutServlet">
                                                Log out
                                            </a>
                                        </li>
                                    </c:if>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</header>


</body>
</html>
