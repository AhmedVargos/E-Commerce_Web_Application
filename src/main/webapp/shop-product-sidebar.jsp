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
                <div class="col-md-9">

                    <div class="row">
                        <div class="col-md-6">

                            <div class="owl-carousel owl-theme" data-plugin-options='{"items": 1, "margin": 10}'>
                                <div>
                                    <img alt="" height="300" class="img-responsive" src="img/products/product-7.jpg">
                                </div>
                                <div>
                                    <img alt="" height="300" class="img-responsive" src="img/products/product-7-2.jpg">
                                </div>
                                <div>
                                    <img alt="" height="300" class="img-responsive" src="img/products/product-7-3.jpg">
                                </div>
                            </div>

                        </div>

                        <div class="col-md-6">

                            <div class="summary entry-summary">

                                <h1 class="mb-none"><strong>Blue Ladies Handbag</strong></h1>

                                <div class="review_num">
                                    <span class="count" itemprop="ratingCount">2</span> reviews
                                </div>

                                <div title="Rated 5.00 out of 5" class="star-rating">
                                    <span style="width:100%"><strong class="rating">5.00</strong> out of 5</span>
                                </div>

                                <p class="price">
                                    <span class="amount">$22</span>
                                </p>

                                <p class="taller">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed tempus
                                    nibh sed elimttis adipiscing. Fusce in hendrerit purus. Lorem ipsum dolor sit amet,
                                    consectetur adipiscing elit. Sed tempus nibh sed elimttis adipiscing. Fusce in
                                    hendrerit purus. </p>

                                <form enctype="multipart/form-data" method="post" class="cart">
                                    <div class="quantity">
                                        <input type="button" class="minus" value="-">
                                        <input type="text" class="input-text qty text" title="Qty" value="1"
                                               name="quantity" min="1" step="1">
                                        <input type="button" class="plus" value="+">
                                    </div>
                                    <button href="#" class="btn btn-primary btn-icon">Add to cart</button>
                                </form>

                                <div class="product_meta">
                                    <span class="posted_in">Categories: <a rel="tag" href="#">Accessories</a>, <a
                                            rel="tag" href="#">Bags</a>.</span>
                                </div>

                            </div>


                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="tabs tabs-product">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a href="#productDescription" data-toggle="tab">Description</a>
                                    </li>
                                    <li><a href="#productInfo" data-toggle="tab">Aditional Information</a></li>
                                    <li><a href="#productReviews" data-toggle="tab">Reviews (2)</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active" id="productDescription">
                                        <p>Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere
                                            cubilia Curae; Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                                            Fusce sagittis, massa fringilla consequat blandit, mauris ligula porta nisi,
                                            non tristique enim sapien vel nisl. Suspendisse vestibulum lobortis dapibus.
                                            Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere
                                            cubilia Curae; Praesent nec tempus nibh. Donec mollis commodo metus et
                                            fringilla. Etiam venenatis, diam id adipiscing convallis, nisi eros lobortis
                                            tellus, feugiat adipiscing ante ante sit amet dolor. Vestibulum vehicula
                                            scelerisque facilisis. Sed faucibus placerat bibendum. Maecenas sollicitudin
                                            commodo justo, quis hendrerit leo consequat ac. Proin sit amet risus sapien,
                                            eget interdum dui. Proin justo sapien, varius sit amet hendrerit id, egestas
                                            quis mauris.</p>
                                    </div>
                                    <div class="tab-pane" id="productInfo">
                                        <table class="table table-striped mt-xl">
                                            <tbody>
                                            <tr>
                                                <th>
                                                    Size:
                                                </th>
                                                <td>
                                                    Unique
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    Colors
                                                </th>
                                                <td>
                                                    Red, Blue
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>
                                                    Material
                                                </th>
                                                <td>
                                                    100% Leather
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="tab-pane" id="productReviews">
                                        <ul class="comments">
                                            <li>
                                                <div class="comment">
                                                    <div class="img-thumbnail">
                                                        <img class="avatar" alt="" src="img/avatar-2.jpg">
                                                    </div>
                                                    <div class="comment-block">
                                                        <div class="comment-arrow"></div>
                                                        <span class="comment-by">
																	<strong>John Doe</strong>
																	<span class="pull-right">
																		<div title="Rated 5.00 out of 5"
                                                                             class="star-rating">
																			<span style="width:100%"><strong
                                                                                    class="rating">5.00</strong> out of 5</span>
																		</div>
																	</span>
																</span>
                                                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam
                                                            viverra euismod odio, gravida pellentesque urna varius
                                                            vitae, gravida pellentesque urna varius vitae. Lorem ipsum
                                                            dolor sit amet, consectetur adipiscing elit. Nam viverra
                                                            euismod odio, gravida pellentesque urna varius vitae. Sed
                                                            dui lorem, adipiscing in adipiscing et, interdum nec metus.
                                                            Mauris ultricies, justo eu convallis placerat, felis enim
                                                            ornare nisi, vitae mattis nulla ante id dui.</p>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                        <hr class="tall">
                                        <h4 class="heading-primary">Add a review</h4>
                                        <div class="row">
                                            <div class="col-md-12">

                                                <form action="" id="submitReview" method="post">
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <div class="col-md-6">
                                                                <label>Your name *</label>
                                                                <input type="text" value=""
                                                                       data-msg-required="Please enter your name."
                                                                       maxlength="100" class="form-control" name="name"
                                                                       id="name">
                                                            </div>
                                                            <div class="col-md-6">
                                                                <label>Your email address *</label>
                                                                <input type="email" value=""
                                                                       data-msg-required="Please enter your email address."
                                                                       data-msg-email="Please enter a valid email address."
                                                                       maxlength="100" class="form-control" name="email"
                                                                       id="email">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group">
                                                            <div class="col-md-12">
                                                                <label>Review *</label>
                                                                <textarea maxlength="5000"
                                                                          data-msg-required="Please enter your message."
                                                                          rows="10" class="form-control" name="message"
                                                                          id="message"></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <input type="submit" value="Submit Review"
                                                                   class="btn btn-primary"
                                                                   data-loading-text="Loading...">
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <hr class="tall">

                    <h4 class="mb-md text-uppercase">Related <strong>Products</strong></h4>

                    <div class="row">

                        <ul class="products product-thumb-info-list">
                            <li class="col-sm-3 col-xs-12 product">
                                <a href="shop-product-sidebar.jsp">
                                    <span class="onsale">Sale!</span>
                                </a>
                                <span class="product-thumb-info">
											<a href="shop-cart.jsp" class="add-to-cart-product">
												<span><i class="fa fa-shopping-cart"></i> Add to Cart</span>
											</a>
											<a href="shop-product-sidebar.jsp">
												<span class="product-thumb-info-image">
													<span class="product-thumb-info-act">
														<span class="product-thumb-info-act-left"><em>View</em></span>
														<span class="product-thumb-info-act-right"><em><i
                                                                class="fa fa-plus"></i> Details</em></span>
													</span>
													<img alt="" class="img-responsive" src="img/products/product-1.jpg">
												</span>
											</a>
											<span class="product-thumb-info-content">
												<a href="shop-product-sidebar.jsp">
													<h4>Photo Camera</h4>
													<span class="price">
														<del><span class="amount">$325</span></del>
														<ins><span class="amount">$299</span></ins>
													</span>
												</a>
											</span>
										</span>
                            </li>
                            <li class="col-sm-3 col-xs-12 product">
										<span class="product-thumb-info">
											<a href="shop-cart.jsp" class="add-to-cart-product">
												<span><i class="fa fa-shopping-cart"></i> Add to Cart</span>
											</a>
											<a href="shop-product-sidebar.jsp">
												<span class="product-thumb-info-image">
													<span class="product-thumb-info-act">
														<span class="product-thumb-info-act-left"><em>View</em></span>
														<span class="product-thumb-info-act-right"><em><i
                                                                class="fa fa-plus"></i> Details</em></span>
													</span>
													<img alt="" class="img-responsive" src="img/products/product-2.jpg">
												</span>
											</a>
											<span class="product-thumb-info-content">
												<a href="shop-product-sidebar.jsp">
													<h4>Golf Bag</h4>
													<span class="price">
														<span class="amount">$72</span>
													</span>
												</a>
											</span>
										</span>
                            </li>
                            <li class="col-sm-3 col-xs-12 product">
										<span class="product-thumb-info">
											<a href="shop-cart.jsp" class="add-to-cart-product">
												<span><i class="fa fa-shopping-cart"></i> Add to Cart</span>
											</a>
											<a href="shop-product-sidebar.jsp">
												<span class="product-thumb-info-image">
													<span class="product-thumb-info-act">
														<span class="product-thumb-info-act-left"><em>View</em></span>
														<span class="product-thumb-info-act-right"><em><i
                                                                class="fa fa-plus"></i> Details</em></span>
													</span>
													<img alt="" class="img-responsive" src="img/products/product-3.jpg">
												</span>
											</a>
											<span class="product-thumb-info-content">
												<a href="shop-product-sidebar.jsp">
													<h4>Workout</h4>
													<span class="price">
														<span class="amount">$60</span>
													</span>
												</a>
											</span>
										</span>
                            </li>
                            <li class="col-sm-3 col-xs-12 product">
										<span class="product-thumb-info">
											<a href="shop-cart.jsp" class="add-to-cart-product">
												<span><i class="fa fa-shopping-cart"></i> Add to Cart</span>
											</a>
											<a href="shop-product-sidebar.jsp">
												<span class="product-thumb-info-image">
													<span class="product-thumb-info-act">
														<span class="product-thumb-info-act-left"><em>View</em></span>
														<span class="product-thumb-info-act-right"><em><i
                                                                class="fa fa-plus"></i> Details</em></span>
													</span>
													<img alt="" class="img-responsive" src="img/products/product-4.jpg">
												</span>
											</a>
											<span class="product-thumb-info-content">
												<a href="shop-product-sidebar.jsp">
													<h4>Luxury bag</h4>
													<span class="price">
														<span class="amount">$199</span>
													</span>
												</a>
											</span>
										</span>
                            </li>
                        </ul>

                    </div>

                </div>
                <div class="col-md-3">
                    <aside class="sidebar">

                        <form>
                            <div class="input-group input-group-lg">
                                <input class="form-control" placeholder="Search..." name="s" id="s" type="text">
                                <span class="input-group-btn">
											<button type="submit" class="btn btn-primary btn-lg"><i
                                                    class="fa fa-search"></i></button>
										</span>
                            </div>
                        </form>

                        <hr>

                        <h5 class="heading-primary">Tags</h5>

                        <a href="#"><span class="label label-dark">Nike</span></a>
                        <a href="#"><span class="label label-dark">Travel</span></a>
                        <a href="#"><span class="label label-dark">Sport</span></a>
                        <a href="#"><span class="label label-dark">TV</span></a>
                        <a href="#"><span class="label label-dark">Books</span></a>
                        <a href="#"><span class="label label-dark">Tech</span></a>
                        <a href="#"><span class="label label-dark">Adidas</span></a>
                        <a href="#"><span class="label label-dark">Promo</span></a>
                        <a href="#"><span class="label label-dark">Reading</span></a>
                        <a href="#"><span class="label label-dark">Social</span></a>
                        <a href="#"><span class="label label-dark">Books</span></a>
                        <a href="#"><span class="label label-dark">Tech</span></a>
                        <a href="#"><span class="label label-dark">New</span></a>

                        <hr>

                        <h5 class="heading-primary">Top Rated Products</h5>
                        <ul class="simple-post-list">
                            <li>
                                <div class="post-image">
                                    <div class="img-thumbnail">
                                        <a href="shop-product-sidebar.jsp">
                                            <img alt="" width="60" height="60" class="img-responsive"
                                                 src="img/products/product-1.jpg">
                                        </a>
                                    </div>
                                </div>
                                <div class="post-info">
                                    <a href="shop-product-sidebar.jsp">Photo Camera</a>
                                    <div class="post-meta">
                                        $299
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="post-image">
                                    <div class="img-thumbnail">
                                        <a href="shop-product-sidebar.jsp">
                                            <img alt="" width="60" height="60" class="img-responsive"
                                                 src="img/products/product-2.jpg">
                                        </a>
                                    </div>
                                </div>
                                <div class="post-info">
                                    <a href="shop-product-sidebar.jsp">Golf Bag</a>
                                    <div class="post-meta">
                                        $72
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="post-image">
                                    <div class="img-thumbnail">
                                        <a href="shop-product-sidebar.jsp">
                                            <img alt="" width="60" height="60" class="img-responsive"
                                                 src="img/products/product-3.jpg">
                                        </a>
                                    </div>
                                </div>
                                <div class="post-info">
                                    <a href="shop-product-sidebar.jsp">Workout</a>
                                    <div class="post-meta">
                                        $60
                                    </div>
                                </div>
                            </li>
                        </ul>

                    </aside>
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
                <div class="col-md-3">
                    <div class="newsletter">
                        <h4>Newsletter</h4>
                        <p>Keep up on our always evolving product features and technology. Enter your e-mail and
                            subscribe to our newsletter.</p>

                        <div class="alert alert-success hidden" id="newsletterSuccess">
                            <strong>Success!</strong> You've been added to our email list.
                        </div>

                        <div class="alert alert-danger hidden" id="newsletterError"></div>

                        <form id="newsletterForm" action="php/newsletter-subscribe.php" method="POST">
                            <div class="input-group">
                                <input class="form-control" placeholder="Email Address" name="newsletterEmail"
                                       id="newsletterEmail" type="text">
                                <span class="input-group-btn">
											<button class="btn btn-default" type="submit">Go!</button>
										</span>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-3">
                    <h4>Latest Tweets</h4>
                    <div id="tweet" class="twitter" data-plugin-tweets
                         data-plugin-options='{"username": "", "count": 2}'>
                        <p>Please wait...</p>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="contact-details">
                        <h4>Contact Us</h4>
                        <ul class="contact">
                            <li><p><i class="fa fa-map-marker"></i> <strong>Address:</strong> 1234 Street Name, City
                                Name, United States</p></li>
                            <li><p><i class="fa fa-phone"></i> <strong>Phone:</strong> (123) 456-789</p></li>
                            <li><p><i class="fa fa-envelope"></i> <strong>Email:</strong> <a
                                    href="mailto:mail@example.com">mail@example.com</a></p></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-2">
                    <h4>Follow Us</h4>
                    <ul class="social-icons">
                        <li class="social-icons-facebook"><a href="http://www.facebook.com/" target="_blank"
                                                             title="Facebook"><i class="fa fa-facebook"></i></a></li>
                        <li class="social-icons-twitter"><a href="http://www.twitter.com/" target="_blank"
                                                            title="Twitter"><i class="fa fa-twitter"></i></a></li>
                        <li class="social-icons-linkedin"><a href="http://www.linkedin.com/" target="_blank"
                                                             title="Linkedin"><i class="fa fa-linkedin"></i></a></li>
                    </ul>
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
                    <div class="col-md-7">
                        <p>© Copyright 2015. All Rights Reserved.</p>
                    </div>
                    <div class="col-md-4">
                        <nav id="sub-menu">
                            <ul>
                                <li><a href="#">FAQ's</a></li>
                                <li><a href="#">Sitemap</a></li>
                                <li><a href="#">Contact</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </footer>
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
