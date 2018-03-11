<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <script>
            function changeLayout(id) {
                var i;
                if (id !== null) {
                    for (i = 1; i <= 5; i++) {
                        if (document.getElementById("p"+i).classList.contains("active"))
                            document.getElementById("p"+i).classList.remove("active");
                    }
                    document.getElementById("p"+id).classList.add("active");
                }else {
                    document.getElementById("p1").classList.add("active");
                }
            }
        </script>
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
        <jsp:include page="/AdminOrderServlet?order=1&page=${OrderPageNo}"/>
    </head>

    <body class="nav-md" onload="changeLayout(${OrderPageNo})">
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

                                    <li  style="width:230px">
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
                        <div class="page-title">
                            <div class="title_left">
                                <h3>Orders
                                    <small>Listing orders</small>
                                </h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2>Orders</h2>
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>
                                            <li class="dropdown">
                                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                                   aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">

                                        <!-- start project list -->
                                        <table class="table table-striped projects">
                                            <thead>
                                                <tr>
                                                    <th style="width: 25%">#Order ID</th>
                                                    <th style="width: 35%">Customer Name</th>
                                                    <th style="width: 15%">Order Date</th>
                                                    <th style="width: 15%">Total Price</th>

                                                </tr>
                                            </thead>
                                            <tbody>

                                                <c:forEach items="${Orders}" var="order"> 
                                                    <tr>
                                                        <td>${order.order_id}</td>
                                                        <td>${order.user.userName}</td>
                                                        <td>${order.order_Date}</td>
                                                        <td>${order.totalPrice}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <!-- end project list -->

                                    </div>
                                    <div class="col-md-12">

                                        <div class="pagination col-md-offset-6" >
                                            <a href="${pageContext.request.contextPath}/AdminPaginationServlet?order=1&page=1" id="p1" onclick="changeLayout(1)">1</a>
                                            <a href="${pageContext.request.contextPath}/AdminPaginationServlet?order=1&page=2" id="p2" onclick="changeLayout(2)">2</a>
                                            <a href="${pageContext.request.contextPath}/AdminPaginationServlet?order=1&page=3" id="p3" onclick="changeLayout(3)">3</a>
                                            <a href="${pageContext.request.contextPath}/AdminPaginationServlet?order=1&page=4" id="p4" onclick="changeLayout(4)">4</a>
                                            <a href="${pageContext.request.contextPath}/AdminPaginationServlet?order=1&page=5" id="p5" onclick="changeLayout(5)">5</a>
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
                    </div>
                    <div class="clearfix"></div>
                </footer>
                <!-- /footer content -->
            </div>
        </div>

        <!-- jQuery -->
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
    </body>
</html>