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
    <script src="js/changePagination.js"></script>

    <jsp:include page="/ProductsListServlet?catId=-1&page=${PageKey}"/>

</head>
<body onload="updatePagination(${PageKey})">

<div class="body">
<script>
    var catId = -1;
</script>
<jsp:include page="header.jsp"></jsp:include>
    <div role="main" class="main shop">

        <div class="container" id="products_container">

            <div class="row">
                <div class="col-md-12">
                    <hr class="tall">
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <h1 class="mb-none"><strong>${SHOP_TAG}</strong></h1>
                    <p>Showing results.</p>
                </div>
            </div>

            <div class="input-group input-group-lg">
                <input class="form-control" placeholder="Search..." name="s" id="search_text" type="text">
                <span class="input-group-btn">
											<button type="button" id="search_btn" onclick="searchProducts()" class="btn btn-primary btn-lg"><i
                                                    class="fa fa-search"></i></button>
										</span>
            </div>

            <div class="row">
                <c:if test="${not empty PRODUCTS_LIST}">
                    <ul class="products product-thumb-info-list" id="list_of_products" style="height: auto;" data-plugin-masonry>
                        <c:forEach items="${PRODUCTS_LIST}" var="product">
                            <li class="col-md-3 col-sm-6 col-xs-12 product">
								<span class="product-thumb-info">
                                    <input id="myProduct" type="hidden" value="${product.productId}"/>
                                    <input id="myQuantity" type="hidden" value="1"/>
									<a href="shop-cart.jsp" onclick="addToCart(this)" class="add-to-cart-product">
										<span><i class="fa fa-shopping-cart"></i> Add to Cart</span>
									</a>
									<a href="HomeServlet?id=${product.productId}">
										<span class="product-thumb-info-image">
											<span class="product-thumb-info-act">
												<span class="product-thumb-info-act-left"><em>View</em></span>
												<span class="product-thumb-info-act-right"><em><i
                                                        class="fa fa-plus"></i> Details</em></span>
											</span>
											<img alt="" class="img-responsive" src="/ImagesServlet?id=${product.productId}">
										</span>
									</a>
									<span class="product-thumb-info-content">
										<a href="HomeServlet?id=${product.productId}">
											<h4>${product.productName}</h4>
											<span class="price">
												<span class="amount">${product.productPrice}</span>
											</span>
										</a>
									</span>
								</span>
                            </li>

                        </c:forEach>


                    </ul>
                </c:if>

            </div>
         
            <div class="row" id="page_footer">
                <div class="col-md-12">
                    <ul class="pagination pull-right">
                       <!-- <li><a href="#"><i class="fa fa-chevron-left"></i></a></li> -->
                        <li class="active" id="pagePag1"><a href="UpdateIdServlet?idPage=1">1</a></li>
                        <li id="pagePag2"><a href="UpdateIdServlet?idPage=2" >2</a></li>
                        <li id="pagePag3"><a href="UpdateIdServlet?idPage=3" >3</a></li>
                       <!-- <li><a href="#"><i class="fa fa-chevron-right"></i></a></li> -->
                    </ul>
                </div>
            </div>
<!--            <c:if test="${ not empty PRODUCTS_LIST}">
             <script>
                 var s="pagePag${PageKey}";
                 var par1;
                 var par2=document.getElementById(s);
                 for (i = 1; i <= 3; i++) {
                      par1=document.getElementById("pagePag"+i);
                        if (par1!==par2)
                            par1.children[0].style.display = "none";
                        }
             </script>
             </c:if> -->
        </div>

    </div>

        <jsp:include page="footer.jsp"></jsp:include>


</div>

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

<!-- Search Scripts -->
<script src="js/search.js"></script>
<!-- Cart Scripts -->
<script src="js/cart.js"></script>
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
