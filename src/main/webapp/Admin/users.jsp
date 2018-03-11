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
        <jsp:include page="/AdminUsersServlet?user=1&page=${UserPageNo}"/>
    </head>

    <body class="nav-md" onload="changeLayout(${UserPageNo})">
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
                                <h3>Customers
                                    <small>Listing users</small>
                                </h3>
                            </div>

                            <div class="title_right">
                                <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">

                                </div>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2>Customers</h2>
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>
                                            <li class="dropdown">
                                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                                   aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li><a href="#">Settings 1</a>
                                                    </li>
                                                    <li><a href="#">Settings 2</a>
                                                    </li>
                                                </ul>
                                            </li>
                                            </li>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">

                                        <%--View User Form--%>
                                        <div id="viewForm" style="display: none;" class="col-md-12 col-xs-12">
                                            <div class="x_panel">
                                                <div class="x_title">
                                                    <h2>Form Basic Elements
                                                        <small>different form elements</small>
                                                    </h2>
                                                    <ul class="nav navbar-right panel_toolbox">
                                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                                        </li>
                                                        <li class="dropdown">
                                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                                                               role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                                            <ul class="dropdown-menu" role="menu">
                                                                <li><a href="#">Settings 1</a>
                                                                </li>
                                                                <li><a href="#">Settings 2</a>
                                                                </li>
                                                            </ul>
                                                        </li>
                                                        <li><a class="close-link" onclick="closeForm();"><i
                                                                    class="fa fa-close"></i></a>
                                                        </li>
                                                    </ul>
                                                </li>
                                                <li><a onclick="closeForm();"><i
                                                        class="fa fa-close"></i></a>
                                                </li>
                                            </ul>
                                            <div class="clearfix"></div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="x_content">
                                                    <br/>
                                                    <form class="form-horizontal form-label-left">
                                                        <form class="form-horizontal form-label-left">

                                                            <div class="form-group">
                                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Customer
                                                                    ID</label>
                                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                                    <input id="userId" type="text" class="form-control"
                                                                           readonly="readonly">
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Customer
                                                                    Name</label>
                                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                                    <input id="userName" type="text" class="form-control"
                                                                           readonly="readonly">
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Email</label>
                                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                                    <input id="userEmail" type="text" class="form-control"
                                                                           readonly="readonly">
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Birthdate</label>
                                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                                    <input id="userBDate" type="text" class="form-control"
                                                                           readonly="readonly">
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Job</label>
                                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                                    <input id="userJob" type="text" class="form-control"
                                                                           readonly="readonly">
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Address</label>
                                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                                    <input id="userAddress" type="text" class="form-control"
                                                                           readonly="readonly">
                                                                </div>
                                                            </div>

                                                            <div class="form-group">
                                                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Credit
                                                                    Limit</label>
                                                                <div class="col-md-6 col-sm-6 col-xs-12">
                                                                    <input id="userCredit" type="text" class="form-control"
                                                                           readonly="readonly">
                                                                </div>
                                                            </div>
                                                        </form>

                                                    </form>
                                                </div>
                                            </div>
                                        </div>


                                        <!-- start project list -->
                                        <table class="table table-striped projects" id="userTable">
                                            <thead>
                                                <tr>
                                                    <th style="width: 10%">#User ID</th>
                                                    <th>Image</th>
                                                    <th style="width: 25%">Customer Name</th>
                                                    <th>Email</th>
                                                    <th>Credit Limit</th>
                                                    <th style="width: 11%">#Edit</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${users}" var="user">
                                                    <tr>
                                                        <td>#${user.userId}</td>
                                                        <td>
                                                            <ul class="list-inline">
                                                                <li>
                                                                    <img src="images/user.png" class="avatar" alt="Avatar">
                                                                </li>
                                                            </ul>
                                                        </td>
                                                        <td>
                                                            <a>${user.userName}</a>
                                                        </td>
                                                        <td>
                                                            <a>${user.email}</a>
                                                        </td>
                                                        <td>
                                                            <a>${user.creditLimit}</a>
                                                        </td>
                                                        <td>
                                                            <button type="button" onclick="viewDetails(${user.userId})"
                                                                    class="btn btn-primary btn-xs"><i class="fa fa-folder"></i>
                                                                View
                                                            </button>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <!-- end project list -->

                                    </div>
                                    <div class="col-md-12">

                                        <div class="pagination col-md-offset-6" >
                                            <a href="${pageContext.request.contextPath}/AdminPaginationServlet?user=1&page=1" id="p1" onclick="changeLayout(1)">1</a>
                                            <a href="${pageContext.request.contextPath}/AdminPaginationServlet?user=1&page=2" id="p2" onclick="changeLayout(2)">2</a>
                                            <a href="${pageContext.request.contextPath}/AdminPaginationServlet?user=1&page=3" id="p3" onclick="changeLayout(3)">3</a>
                                            <a href="${pageContext.request.contextPath}/AdminPaginationServlet?user=1&page=4" id="p4" onclick="changeLayout(4)">4</a>
                                            <a href="${pageContext.request.contextPath}/AdminPaginationServlet?user=1&page=5" id="p5" onclick="changeLayout(5)">5</a>
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
</body>
</html>
