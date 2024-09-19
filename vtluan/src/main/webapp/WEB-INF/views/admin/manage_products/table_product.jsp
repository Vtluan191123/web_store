<%@ page pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8">
                <title>DASHMIN - Bootstrap Admin Template</title>
                <meta content="width=device-width, initial-scale=1.0" name="viewport">
                <meta content="" name="keywords">
                <meta content="" name="description">

                <!-- Favicon -->
                <link href="img/favicon.ico" rel="icon">

                <!-- Google Web Fonts -->
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap"
                    rel="stylesheet">

                <!-- Icon Font Stylesheet -->
                <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
                    rel="stylesheet">
                <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                    rel="stylesheet">


                <!-- Customized Bootstrap Stylesheet -->
                <link href="css/bootstrap.min.css" rel="stylesheet">

                <!-- Template Stylesheet -->
                <link href="css/style.css" rel="stylesheet">
                <style>
                    table {
                        width: 100%;
                        border-collapse: collapse;
                    }


                    th,
                    td {
                        text-align: center;
                        padding: 8px;
                        width: 200px;
                        max-width: 200px;
                        overflow: hidden;
                        overflow-wrap: break-word;
                        word-break: break-word;
                    }
                </style>
            </head>

            <body>
                <div class="container-xxl position-relative bg-white d-flex p-0">
                    <!-- Spinner Start -->
                    <div id="spinner"
                        class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
                        <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                            <span class="sr-only">Loading...</span>
                        </div>
                    </div>
                    <!-- Spinner End -->


                    <jsp:include page="../../admin/layout/navbar.jsp" />


                    <!-- Content Start -->
                    <div class="content">
                        <jsp:include page="../../admin/layout/header.jsp" />


                        <!-- content -->
                        <div class="container col-12 p-2 mt-3 mb-5 pb-5">
                            <div class="bg-white p-4 rounded shadow">
                                <div class="d-flex justify-content-around align-items-center mb-4">
                                    <a href="/admin/products">
                                        <h1 class="h3 text-dark">Manage Products</h1>
                                    </a>
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <form method="get" action="/admin/products" class="d-flex">
                                                <input type="search" name="search" class="form-control"
                                                    placeholder="Search product" required />
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                                <button class="input-group-text mx-2 border-0" id="search-addon">
                                                    <i class="fas fa-search"></i>
                                                </button>

                                            </form>
                                        </div>
                                    </div>
                                    <a href="/admin/product/create" class="btn btn-primary">Create Product</a>
                                </div>
                                <table class="table table-striped table-hover">
                                    <thead class="table-dark">
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Brand</th>
                                            <th scope="col">Description</th>
                                            <th scope="col">Des_short</th>
                                            <th scope="col">Price</th>
                                            <th scope="col">Image</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <!-- table -->
                                    <c:if test="${empty search}">
                                        <tbody>
                                            <c:forEach var="product" items="${products}">
                                                <!-- <c:set var="image" value="${product.image}" /> -->
                                                <tr>
                                                    <td>${product.id}</td>
                                                    <td>${product.name}</td>
                                                    <td>${product.brand}</td>
                                                    <td
                                                        style="    overflow: auto;white-space: nowrap;word-wrap: break-word;">
                                                        ${product.description}
                                                    </td>
                                                    <td style="    overflow: auto;white-space: nowrap;">
                                                        ${product.des_short}
                                                    </td>
                                                    <td>
                                                        <fmt:formatNumber type="number" maxFractionDigits="3"
                                                            value="${product.price}" />đ
                                                    </td>
                                                    <td>
                                                        <img class="mt-4"
                                                            style="width: 100px;height: 100px;border-radius: 50%;"
                                                            src="/admin/image/${product.image}" alt="">
                                                    </td>

                                                    <td class="h-100 d-flex justify-content-around flex-column">
                                                        <a class="btn btn-success my-2"
                                                            href="/admin/product/view/${product.id}">View</a>
                                                        <a class="btn btn-warning my-2"
                                                            href="/admin/product/edit/${product.id}">Edit</a>
                                                        <a class="btn btn-danger my-2"
                                                            href="/admin/product/delete/${product.id}">Delete</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </c:if>
                                    <!-- search -->
                                    <c:if test="${not empty search}">
                                        <c:if test="${not empty results}">
                                            <tbody>
                                                <c:forEach var="result" items="${results}">
                                                    <!-- <c:set var="image" value="${product.image}" /> -->
                                                    <tr>
                                                        <td>${result.id}</td>
                                                        <td>${result.name}</td>
                                                        <td>${result.brand}</td>
                                                        <td
                                                            style="    overflow: auto;white-space: nowrap;word-wrap: break-word;">
                                                            ${result.description}
                                                        </td>
                                                        <td style="    overflow: auto;white-space: nowrap;">
                                                            ${result.des_short}
                                                        </td>
                                                        <td>
                                                            <fmt:formatNumber type="number" maxFractionDigits="3"
                                                                value="${result.price}" />đ
                                                        </td>
                                                        <td>
                                                            <img class="mt-4"
                                                                style="width: 100px;height: 100px;border-radius: 50%;"
                                                                src="/admin/image/${result.image}" alt="">
                                                        </td>

                                                        <td class="h-100 d-flex justify-content-around flex-column">
                                                            <a class="btn btn-success my-2"
                                                                href="/admin/product/view/${result.id}">View</a>
                                                            <a class="btn btn-warning my-2"
                                                                href="/admin/product/edit/${result.id}">Edit</a>
                                                            <a class="btn btn-danger my-2"
                                                                href="/admin/product/delete/${result.id}">Delete</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                        </c:if>
                                        <!-- not find -->
                                        <c:if test="${empty results}">
                                            <tr>
                                                <td colspan="8">
                                                    <h3>Không tìm thấy</h3>
                                                </td>
                                            </tr>
                                        </c:if>
                                        </tbody>
                                    </c:if>
                                </table>
                                <c:if test="${empty search}">
                                    <div>
                                        <nav aria-label="Page navigation example">
                                            <ul class="pagination justify-content-center">
                                                <li class="page-item ">
                                                    <a class="page-link ${currentPage eq 1 ? 'd-none' : ''}"
                                                        href="/admin/products?page=${currentPage-1}"
                                                        aria-label="Previous">
                                                        <span aria-hidden="true">&laquo;</span>
                                                    </a>
                                                </li>
                                                <c:forEach varStatus="loop" begin="0" end="${totalPage-1}">
                                                    <li class="page-item ${loop.count eq currentPage ? 'active' : ''}">
                                                        <a class="page-link"
                                                            href="/admin/products?page=${loop.index+1}">${loop.index+1}</a>
                                                    </li>
                                                </c:forEach>

                                                <a class="page-link ${currentPage eq totalPage ? 'd-none' : ''}"
                                                    href="/admin/products?page=${currentPage+1}" aria-label="Next"
                                                    style="border-top-right-radius: 5px;border-bottom-right-radius: 5px;">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                                </li>
                                            </ul>
                                        </nav>
                                    </div>
                                </c:if>
                            </div>
                        </div>

                        <div>

                        </div>

                        <jsp:include page="../../admin/layout/footer.jsp" />
                    </div>
                    <!-- Content End -->



                </div>

                <!-- JavaScript Libraries -->
                <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

                <!-- Template Javascript -->
                <script src="js/main.js"></script>
            </body>

            </html>