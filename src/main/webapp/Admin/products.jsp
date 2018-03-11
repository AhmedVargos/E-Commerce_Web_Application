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
     <style>
            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                margin: auto;
                align-items: center;
            }

            .pagination a.active {
                background-color: dodgerblue;
                color: white;
            }

            .pagination a:hover:not(.active) {background-color: #ddd;}
        </style>
        <script>
            function changeLayout(id) {
                var i;
                if (id !== null) {
                    for (i = 1; i <= 5; i++) {
                        if (document.getElementById("p"+i).classList.contains("active"))
                            document.getElementById("p"+i).classList.remove("active");
                    }
                    document.getElementById("p"+id).classList.add("active");
                } else {
                    document.getElementById("p1").classList.add("active");

                }
            }
        </script>
    <jsp:include page="/AdminProductsServlet?product=1&page=${ProductPageNo}"/>
</head>

<body class="nav-md" onload="changeLayout(${ProductPageNo})">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">


                <div class="clearfix"></div>

                <!-- menu profile quick info -->
                <!-- /menu profile quick info -->

                <br/>

                <!-- sidebar menu -->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                    <div class="menu_section">
                        <h3>Admin Panel</h3>
                        <ul class="nav side-menu">

                            <li style="width:230px">
                                <a href="users.jsp"><i class="fa fa-users"></i> Users </a>
                            </li>

                            <li style="width:230px">
                                <a href="products.jsp"><i class="fa fa-shopping-bag"></i> Products </a>
                            </li>

                            <li style="width:230px">
                                <a href="categories.jsp"><i class="fa fa-list"></i> Categories </a>
                            </li>

                            <li style="width:230px">
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
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown"
                               -                               aria-expanded="false">
                                <img src="images/img.jpg" alt="">System Admin
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu pull-right">
                                <li><a href="../LogoutServlet"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
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

                <div class="clearfix"></div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Products</h2>
                                <br/>
                                <button type="button" id="btnAdd" class="btn btn-success pull-right"
                                        onclick="addProduct();">Add
                                    Product
                                </button>
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

                                            <div class="profile_img pull-right">
                                                <div id="crop-avatar">
                                                    <!-- Current avatar -->
                                                    <img id="productImg" style="width: 320px; height: 320px"
                                                         class="img-responsive avatar-view"
                                                         src=""
                                                         alt=""
                                                         title="Change the avatar">
                                                </div>
                                            </div>

                                            <form class="form-horizontal form-label-left col-md-6"
                                                  action="/AdminEditProductServlet"
                                                  method="POST"
                                                  enctype="multipart/form-data">

                                                <div id="idDiv" class="form-group">
                                                    <label>Product
                                                        ID</label>
                                                    <div>
                                                        <input id="productId" name="productId" type="text"
                                                               class="form-control"
                                                               readonly="readonly"/>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label>Category</label>
                                                    <div>
                                                        <select id="productCategories" name="productCategory"
                                                                class="form-control">
                                                        </select>
                                                    </div>
                                                </div>

                                                <div>
                                                    <label>Product
                                                        Name</label>
                                                    <div>
                                                        <input id="productName" name="productName" type="text"
                                                               class="form-control"/>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label>
                                                        ProductDescription
                                                    </label>
                                                    <div>
                                                        <textarea id="productDescription" name="productDescription"
                                                                  class="form-control"
                                                                  rows="3"></textarea>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label>Product
                                                        Price</label>
                                                    <div>
                                                        <input id="productPrice" name="productPrice" type="number"
                                                               step="0.01"
                                                               min="1"
                                                               class="form-control"/>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label>Quantity</label>
                                                    <div>
                                                        <input id="productQuantity" name="productQuantity" type="number"
                                                               min="0"
                                                               class="form-control"/>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <br/>
                                                    <label id="lblImage" class="btn btn-info btn-file">
                                                        Choose image... <input type="file" name="imageFile"
                                                                               style="display: none;">
                                                    </label>

                                                    <label id="path"></label>
                                                    <br/>
                                                    <br/>
                                                </div>

                                                <button id="btnSubmit" type="submit" class="btn btn-primary">Update</button>
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
                            <div id="pagination" class="col-md-12">
                                <div class="pagination col-md-offset-6" >
                                    <a href="${pageContext.request.contextPath}/AdminPaginationServlet?product=1&page=1" id="p1" onclick="changeLayout(1)">1</a>
                                    <a href="${pageContext.request.contextPath}/AdminPaginationServlet?product=1&page=2" id="p2" onclick="changeLayout(2)">2</a>
                                    <a href="${pageContext.request.contextPath}/AdminPaginationServlet?product=1&page=3" id="p3" onclick="changeLayout(3)">3</a>
                                    <a href="${pageContext.request.contextPath}/AdminPaginationServlet?product=1&page=4" id="p4" onclick="changeLayout(4)">4</a>
                                    <a href="${pageContext.request.contextPath}/AdminPaginationServlet?product=1&page=5" id="p5" onclick="changeLayout(5)">5</a>
                                </div>
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

<script>
    $(document).on('change', ':file', function () {
        var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        input.trigger('fileselect', [numFiles, label]);
    });

    $(document).ready(function () {
        $(':file').on('fileselect', function (event, numFiles, label) {
            $("#path").text(label);

        });
    });
</script>
</body>
</html>