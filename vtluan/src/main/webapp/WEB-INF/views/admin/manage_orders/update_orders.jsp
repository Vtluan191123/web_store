<%@ page pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="utf-8">
                    <title>Create Product</title>
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
                        #imagePreview {
                            max-width: 200px;
                            height: auto;
                            margin-top: 10px;
                            display: block;
                            /* Initially hidden */
                            border: 1px solid #ddd;
                            padding: 5px;
                            border-radius: 4px;
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
                            <div class="container col-10 mt-5">
                                <div class="bg-white p-4 rounded shadow">
                                    <h1 class="mb-2">Update Order</h1>
                                    <form:form method="post" action="/admin/orders/updated" modelAttribute="order"
                                        enctype="multipart/form-data">
                                        <div class="d-flex justify-content-between">
                                            <div>
                                                <div class="d-none">
                                                    <label for="id" class="form-label">id</label>
                                                    <form:input type="text" path="id" class="form-control" id="id" />
                                                </div>

                                                <h3 class="mt-3">Total:
                                                    <fmt:formatNumber type="number" maxFractionDigits="3"
                                                        value="${order.totalPrice}" />đ
                                                </h3>
                                            </div>
                                            <div class="mx-5">
                                                <h3>Status</h3>
                                                <form:select path="status" cssClass="form-control">
                                                    <form:option value="PENDING">PENDING</form:option>
                                                    <form:option value="SHIPING">SHIPING</form:option>
                                                    <form:option value="COMPLETE">COMPLETE</form:option>
                                                </form:select>
                                            </div>
                                        </div>
                                        <button type="submit" class="btn btn-warning">Update</button>
                                        <a href="/admin/orders" class="btn btn-success">Back</a>
                                    </form:form>
                                </div>
                            </div>

                            <jsp:include page="../../admin/layout/footer.jsp" />

                        </div>
                        <!-- Content End -->


                        <!-- Back to Top -->
                        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i
                                class="bi bi-arrow-up"></i></a>
                    </div>

                    <!-- JavaScript Libraries -->
                    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

                    <!-- Template Javascript -->
                    <script src="js/main.js"></script>

                </body>

                </html>