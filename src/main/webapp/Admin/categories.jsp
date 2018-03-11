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

        <title> Categories Management</title>

        <!-- Bootstrap -->
        <link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <!-- NProgress -->
        <link href="vendors/nprogress/nprogress.css" rel="stylesheet">
        <!-- iCheck -->
        <link href="vendors/iCheck/skins/flat/green.css" rel="stylesheet">
        <Link href="css/style.css" rel="stylesheet">
        <!-- Custom Theme Style -->
        <link href="build/css/custom.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">     

        <script>
            function addNew() {
                document.getElementById("addBtn").style.display = "none";
                document.getElementById("catTable").style.display = "none";
                document.getElementById("newCatDiv").style.display = "block";
            }

            function backToTable() {
                document.getElementById("addBtn").style.display = "block";
                document.getElementById("catTable").style.display = "block";
                document.getElementById("newCatDiv").style.display = "none";
                document.getElementById("ditCatDiv").style.display = "none";
            }

            function startEdit(catId, catName) {
                document.getElementById("addBtn").style.display = "none";
                document.getElementById("catTable").style.display = "none";
                document.getElementById("editCatDiv").style.display = "block";
                document.getElementById("cID").value = catId;
                document.getElementById("cID_2").value = catId;
                document.getElementById("cName").value = catName;
            }

            function changeLayout(id) {
                var i;
                if (id !== undefined) {
                    for (i = 1; i <= 5; i++) {
                        if (document.getElementById("p" + i).classList.contains("active"))
                            document.getElementById("p" + i).classList.remove("active");
                    }
                    document.getElementById("p" + id).classList.add("active");
                } else {
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
        <jsp:include page="/AdminCategoryServlet?category=1&page=${CategoryPageNo}"/>

    </head>

    <body class="nav-md" onload="changeLayout(${CategoryPageNo})">
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
                        <div class="page-title">
                            <div class="title_left">
                                <h3>Categories
                                    <small>Listing Categories</small>
                                </h3>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2>Categories</h2>
                                        <ul class="nav navbar-right panel_toolbox">

                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>

                                    <!--========================================== Categories List ================================================-->   

                                    <div class="x_content">
                                        <div data-role="main" class="ui-content">
                                            <button type="button" id="addBtn" onclick="addNew()" class="btn btn-warning btn-sm"><i class="glyphicon glyphicon-plus"></i>Add New Category</button>

                                            <div id="newCatDiv">
                                                <form method="post" action="${pageContext.request.contextPath}/AdminCategoryServlet">
                                                    <button type="button" data-inline="true"  value="Back" class="btn btn-warning btn-sm" onclick="backToTable()">Back</button><br>
                                                    <label for="catnm" class="ui-hidden-accessible">Name</label>
                                                    <input type="text" name="name" id="name" placeholder="Category name"><br>
                                                    <button type="submit" data-inline="true"  value="Add" class="btn btn-warning btn-sm" onclick="backToTable()">Add</button>
                                                </form>
                                            </div>

                                            <div id="editCatDiv">
                                                <h3> Edit Category </h3>
                                                <form method="post" action="${pageContext.request.contextPath}/AdminCategoryServlet">
                                                    <label for="cID" >Category ID</label>
                                                    <input type="text" name="cID" id="cID" disabled>
                                                    <input type="hidden" name="cID_2" id="cID_2">
                                                    <br>
                                                    <label for="cName">Name</label>
                                                    <input type="text" id="cName" name="cName">
                                                    <button type="submit" class="btn btn-danger btn-xs" onclick="backToTable()"> Update</button>
                                                </form>
                                            </div>
                                        </div>

                                        <!-- start project list -->
                                        <table class="table table-striped projects" id="catTable">
                                            <thead>
                                                <tr>
                                                    <th style="width: 10%">#Category ID</th>
                                                    <th style="width: 20%">Category Name</th>
                                                    <th style="width: 21%">Edit</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${Categories}" var="category"> 

                                                    <tr>
                                                        <td>${category.categoryId}</td>
                                                        <td>
                                                            <a>${category.categoryName}</a>
                                                        </td>
                                                        <td>
                                                            <button class="btn btn-info btn-xs" onclick="startEdit(${category.categoryId}, '${category.categoryName}')"><i class="fa fa-pencil"></i> Edit </button>
                                                            <a href="${pageContext.request.contextPath}/AdminCategoryServlet?delete=1&id=${category.categoryId}&page=${pageNo}" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> Delete </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>

                                    </div>
                                    <div class="col-md-12" id="paginationDiv">

                                        <div class="pagination col-md-offset-6" >
                                            <a href="${pageContext.request.contextPath}/AdminPaginationServlet?category=1&page=1" id="p1" onclick="changeLayout(1)">1</a>
                                            <a href="${pageContext.request.contextPath}/AdminPaginationServlet?category=1&page=2" id="p2" onclick="changeLayout(2)">2</a>
                                            <a href="${pageContext.request.contextPath}/AdminPaginationServlet?category=1&page=3" id="p3" onclick="changeLayout(3)">3</a>
                                            <a href="${pageContext.request.contextPath}/AdminPaginationServlet?category=1&page=4" id="p4" onclick="changeLayout(4)">4</a>
                                            <a href="${pageContext.request.contextPath}/AdminPaginationServlet?category=1&page=5" id="p5" onclick="changeLayout(5)">5</a>
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