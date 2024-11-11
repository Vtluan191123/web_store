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
                                    <a href="/admin/users">
                                        <h1 class="h3 text-dark">Manage Order</h1>
                                    </a>
                                    <div class="col-md-4">
                                        <div class="input-group">
                                            <form method="get" action="/admin/users" class="d-flex">
                                                <input type="search" name="search" class="form-control"
                                                    placeholder="Search name" required />
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                                <button class="input-group-text mx-2 border-0" id="search-addon">
                                                    <i class="fas fa-search"></i>
                                                </button>

                                            </form>
                                        </div>
                                    </div>

                                </div>

                                <c:forEach var="order" items="${orders}">
                                    <div class="card mb-4 shadow-sm"
                                        style="border: 1px solid #ddd; border-radius: 8px;">
                                        <div class="d-flex justify-content-around align-items-center p-3">
                                            <!-- Order Information -->
                                            <div class="card-body">
                                                <h3 class="card-title" style="font-size: 1.25rem;">ID: ${order.id}</h3>
                                                <h3 class="card-text" style="font-size: 1.25rem;">
                                                    Total:
                                                    <fmt:formatNumber type="number" maxFractionDigits="3"
                                                        value="${order.totalPrice}" />đ
                                                </h3>
                                                <h3 class="card-text" style="font-size: 1.25rem;">Status:
                                                    ${order.status}</h3>
                                            </div>

                                            <!-- Action Buttons -->
                                            <div class="d-flex flex-column justify-content-center m-4">
                                                <a href="/admin/orders/update/${order.id}"
                                                    class="btn btn-warning mb-2">Update</a>
                                                <a href="/admin/orders/remove/${order.id}"
                                                    class="btn btn-danger">Remove</a>
                                            </div>
                                        </div>


                                        <!-- Table for Order Details -->
                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover mb-0">
                                                <thead class="table-dark">
                                                    <tr>
                                                        <th scope="col">Image</th>
                                                        <th scope="col">Name Product</th>
                                                        <th scope="col">Quantity</th>
                                                        <th scope="col">Price</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="order_details" items="${order.order_details}">
                                                        <tr class="align-middle">
                                                            <td>
                                                                <img class="mt-2 mb-2 rounded-circle"
                                                                    style="width: 100px; height: 100px;"
                                                                    src="image/${order_details.products.image}"
                                                                    alt="Product Image">
                                                            </td>
                                                            <td>${order_details.products.name}</td>
                                                            <td>${order_details.quantity}</td>
                                                            <td>
                                                                <fmt:formatNumber type="number" maxFractionDigits="3"
                                                                    value="${order_details.products.price}" />đ
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </c:forEach>



                            </div>
                        </div>

                        <div>

                        </div>

                        <jsp:include page="../../admin/layout/footer.jsp" />
                    </div>
                    <!-- Content End -->




                    <!-- JavaScript Libraries -->
                    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

                    <!-- Template Javascript -->
                    <script src="js/main.js"></script>
            </body>

            </html>