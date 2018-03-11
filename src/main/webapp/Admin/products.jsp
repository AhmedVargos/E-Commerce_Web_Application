<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Gentelella Alela! | </title>

    <!-- Bootstrap -->
    <link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="build/css/custom.min.css" rel="stylesheet">
    <jsp:include page="/AdminProductsServlet"/>
</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="index.jsp" class="site_title"><i class="fa fa-paw"></i> <span>Gentelella Alela!</span></a>
                </div>

                <div class="clearfix"></div>

                <!-- menu profile quick info -->
                <div class="profile clearfix">
                    <div class="profile_pic">
                        <img src="images/img.jpg" alt="..." class="img-circle profile_img">
                    </div>
                    <div class="profile_info">
                        <span>Welcome,</span>
                        <h2>John Doe</h2>
                    </div>
                </div>
                <!-- /menu profile quick info -->

                <br/>

                <!-- sidebar menu -->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                    <div class="menu_section">
                        <h3>Admin Panel</h3>
                        <ul class="nav side-menu">

                            <li>
                                <a href="users.jsp"><i class="fa fa-users"></i> Users </a>
                            </li>

                            <li>
                                <a href="products.jsp"><i class="fa fa-shopping-bag"></i> Products </a>
                            </li>

                            <li>
                                <a href="categories.jsp"><i class="fa fa-list"></i> Categories </a>
                            </li>

                            <li>
                                <a href="orders.jsp"><i class="fa fa-info"></i> Orders </a>
                            </li>

                        </ul>
                    </div>

                </div>
                <!-- /sidebar menu -->

            </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
            <div class="nav_menu">
                <nav>
                    <div class="nav toggle">
                        <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                    </div>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="">
                            <a href="javascript:" class="user-profile dropdown-toggle" data-toggle="dropdown"
                               aria-expanded="false">
                                <img src="images/img.jpg" alt="">John Doe
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu pull-right">
                                <li><a href="javascript:"> Profile</a></li>
                                <li>
                                    <a href="javascript:">
                                        <span class="badge bg-red pull-right">50%</span>
                                        <span>Settings</span>
                                    </a>
                                </li>
                                <li><a href="javascript:">Help</a></li>
                                <li><a href="login.html"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                            </ul>
                        </li>

                    </ul>
                </nav>
            </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>Products
                            <small>Listing products</small>
                        </h3>
                    </div>

                    <div class="title_right">
                        <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search for...">
                                <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Go!</button>
                    </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="clearfix"></div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Products</h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">


                                <div id="viewForm" class="col-md-12 col-xs-12" style="display: none;">
                                    <div class="x_panel">
                                        <div class="x_title">
                                            <h2>Form Basic Elements
                                                <small>different form elements</small>
                                            </h2>
                                            <ul class="nav navbar-right panel_toolbox">
                                                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                                </li>
                                                <li><a onclick="closeForm();"><i
                                                        class="fa fa-close"></i></a>
                                                </li>
                                            </ul>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="x_content">
                                            <br/>

                                            <form class="form-horizontal form-label-left col-md-6"
                                                  action="/AdminEditProductServlet"
                                                  method="POST"
                                                  enctype="multipart/form-data">

                                                <div class="profile_img">
                                                    <div id="crop-avatar">
                                                        <!-- Current avatar -->
                                                        <img class="img-responsive avatar-view"
                                                             src="images/picture.jpg"
                                                             alt="Avatar" title="Change the avatar">
                                                    </div>
                                                    <br/>

                                                    <label class="btn btn-info btn-file">
                                                        Browse... <input type="file" name="imageFile"
                                                                         style="display: none;">
                                                    </label>

                                                    <label id="path"></label>
                                                    <br/>
                                                </div>

                                                <div class="form-group">
                                                    <label>Product
                                                        ID</label>
                                                    <div>
                                                        <input id="productId" type="text" class="form-control" readonly="readonly"/>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label>Category</label>
                                                    <div>
                                                        <select id="productCategories" name="productCategory" class="form-control">
                                                        </select>
                                                    </div>
                                                </div>

                                                <div>
                                                    <label>Product
                                                        Name</label>
                                                    <div>
                                                        <input id="productName" name="productName" type="text" class="form-control"/>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label>Product
                                                        Description <span class="required">*</span>
                                                    </label>
                                                    <div>
                                                        <textarea id="productDescription" name="productDescription" class="form-control"
                                                                  rows="3"></textarea>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label>Product
                                                        Price</label>
                                                    <div>
                                                        <input id="productPrice" name="productPrice" type="number" min="1"
                                                               class="form-control"/>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label>Quantity</label>
                                                    <div>
                                                        <input id="productQuantity" name="productQuantity" type="number" min="0"
                                                               class="form-control"/>
                                                    </div>
                                                </div>

                                                <button type="submit" class="btn btn-primary">Update</button>
                                            </form>

                                        </div>
                                    </div>
                                </div>


                                <!-- start project list -->
                                <table id="table" class="table table-striped projects">
                                    <thead>
                                    <tr>
                                        <th style="width: 10%">#Product ID</th>
                                        <th style="width: 25%">Product Name</th>
                                        <th>Product Price</th>
                                        <th>Quantity</th>
                                        <th style="width: 21%">#Edit</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${products}" var="product">
                                        <tr id="${product.productId}">
                                            <td>#${product.productId}</td>
                                            <td>
                                                <a>${product.productName}</a>
                                            </td>
                                            <td>
                                                <a>${product.productPrice} EG</a>
                                            </td>
                                            <td>
                                                <a>${product.quantity}</a>
                                            </td>
                                            <td>
                                                <button onclick="viewProduct(${product.productId})" type="button"
                                                        class="btn btn-info btn-xs"><i
                                                        class="fa fa-pencil"></i>
                                                    Edit
                                                </button>
                                                <button onclick="deleteProduct(${product.productId})" type="button"
                                                        class="btn btn-danger btn-xs"><i
                                                        class="fa fa-trash-o"></i>
                                                    Delete
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <!-- end project list -->

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
            <div class="pull-right">
                Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
            </div>
            <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
    </div>
</div>

<!-- jQuery -->
<script src="vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="vendors/nprogress/nprogress.js"></script>
<!-- bootstrap-progressbar -->
<script src="vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>

<!-- Custom Theme Scripts -->
<script src="build/js/custom.min.js"></script>
<script src="js/AdminJavaScript.js"></script>
<script src="js/jquery.form.js"></script>

<script>
    $(document).on('change', ':file', function () {
        var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        input.trigger('fileselect', [numFiles, label]);
    });

    // $(function () {
    //     $('#upload-form').ajaxForm({
    //         success: function (msg) {
    //             alert("File has been uploaded successfully");
    //         },
    //         error: function (msg) {
    //             $("#upload-error").text("Couldn't upload file");
    //         }
    //     });
    // });

    $(document).ready(function () {
        $(':file').on('fileselect', function (event, numFiles, label) {
            //console.log(numFiles);
            $("#path").text(label);

        });
    });
</script>
</body>
</html>