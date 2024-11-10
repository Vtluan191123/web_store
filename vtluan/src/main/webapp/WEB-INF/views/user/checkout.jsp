<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8">
                <title>Fruitables - Vegetable Website Template</title>
                <meta content="width=device-width, initial-scale=1.0" name="viewport">
                <meta content="" name="keywords">
                <meta content="" name="description">

                <!-- Google Web Fonts -->
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link
                    href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
                    rel="stylesheet">

                <!-- Icon Font Stylesheet -->
                <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
                <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
                <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                    rel="stylesheet">

                <!-- Libraries Stylesheet -->
                <link href="client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
                <link href="client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


                <!-- Customized Bootstrap Stylesheet -->
                <link href="client/css/bootstrap1.min.css" rel="stylesheet">

                <!-- Template Stylesheet -->
                <link href="client/css/style1.css" rel="stylesheet">
            </head>

            <body>

                <!-- Spinner Start -->
                <div id="spinner"
                    class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
                    <div class="spinner-grow text-primary" role="status"></div>
                </div>
                <!-- Spinner End -->



                <jsp:include page="../user/layout/header.jsp" />






                <!-- Cart Page Start -->
                <div class="container-fluid py-5">
                    <div class="container py-5">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Products</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Quantity</th>
                                        <th scope="col">Total</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${listCart_details}">
                                        <tr>
                                            <th scope="row">
                                                <div class="d-flex align-items-center">
                                                    <img src="client/image/${item.products.image}"
                                                        class="img-fluid me-5 rounded-circle"
                                                        style="width: 80px; height: 80px;" alt="">
                                                </div>
                                            </th>
                                            <td>
                                                <p class="mb-0 mt-4">${item.products.name}</p>
                                            </td>
                                            <td>
                                                <p class="mb-0 mt-4 ">
                                                    <fmt:formatNumber type="number" maxFractionDigits="3"
                                                        value="${item.products.price}" />đ
                                                </p>
                                            </td>
                                            <td>
                                                <div class="input-group quantity mt-4" style="width: 100px;">

                                                    <input type="text" data-product-id="${item.products.id}"
                                                        data-price="${item.products.price}" disabled
                                                        class="form-control form-control-sm text-center border-0"
                                                        value="${item.quantity}">
                                                </div>
                                            </td>
                                            <td>
                                                <p data-total-id="${item.products.id}" class="mb-0 mt-4 total ">
                                                    <fmt:formatNumber type="number" maxFractionDigits="3"
                                                        value="${item.quantity * item.products.price}" />đ
                                                </p>

                                            </td>



                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty listCart_details}">
                                        <tr>
                                            <h3>Giỏ hàng trống</h3>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                        <c:if test="${ not empty listCart_details}">
                            <form action="/order" method="post">
                                <div class="row g-4 d-flex justify-content-end mb-5">
                                    <div class="col-sm-4 col-md-7 col-lg-6 col-xl-4">
                                        <div class="bg-light rounded p-4 h-100">
                                            <h3>Thông tin người nhận</h3>
                                            <label class="mb-1" for="hoten">Họ tên</label><br>
                                            <input required class="mb-3 w-100 rounded border border-dark p-2"
                                                type="text" id="hoten" name="receiverName"
                                                placeholder="Họ tên ..." /><br>
                                            <label class="mb-1" for="sdt">Số điện thoại</label><br>
                                            <input required class="mb-3 w-100 rounded border border-dark p-2"
                                                type="text" id="sdt" name="receiverPhoneNumber"
                                                placeholder="Số điện thoại ..." /><br>
                                            <label class="mb-1" for="diachi">Địa chỉ</label><br>
                                            <input required class="mb-3 w-100 rounded border border-dark p-2"
                                                type="text" id="diachi" name="receiverAddress"
                                                placeholder="Địa chỉ ..." /><br>
                                            <a href="/cart"><i class="fas fa-arrow-left"></i><span class="ms-2">Trở
                                                    lại giỏ
                                                    hàng</span></a>

                                        </div>
                                    </div>
                                    <div class="col-sm-8 col-md-7 col-lg-6 col-xl-8">
                                        <div class="bg-light rounded h-100">
                                            <div class="p-4">
                                                <h3 class="text-center">Thanh toán</h3>
                                                <div class="d-flex justify-content-between mb-4">
                                                    <h5 class="mb-0 me-4">Tổng :</h5>
                                                    <p data-total-cart-price="${totalPrice}" class="mb-0">
                                                        <fmt:formatNumber type="number" maxFractionDigits="3"
                                                            value="${totalPrice}" />đ
                                                    </p>
                                                </div>

                                                <div class="d-flex justify-content-between mb-4">
                                                    <h5 class="mb-0 me-4">Tổng :</h5>
                                                    <p data-total-cart-price="${totalPrice}" class="mb-0">
                                                        <fmt:formatNumber type="number" maxFractionDigits="3"
                                                            value="${totalPrice}" />đ
                                                    </p>
                                                </div>
                                            </div>
                                            <div
                                                class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                                                <h5 class="mb-0 ps-4 me-4">Tổng</h5>
                                                <p data-total-cart-price="${totalPrice}" class="mb-0 pe-4">
                                                    <fmt:formatNumber type="number" maxFractionDigits="3"
                                                        value="${totalPrice}" />đ
                                                </p>
                                                <input class="d-none" type="number"
                                                    value="<fmt:formatNumber value='${totalPrice}' type='number' groupingUsed='false'  maxFractionDigits='0'/>"
                                                    name="amount">
                                            </div>

                                            <c:forEach var="item" items="${listCart_details}">
                                                <div class="mb-5 d-none">
                                                    <label for="">id:</label><br>
                                                    <input type="text" value="${item.products.id}"><br>
                                                    <label for="">quantity:</label><br>
                                                    <input type="text" quantity-dynamic-id=${item.products.id}
                                                        value="${item.quantity}">
                                                </div>
                                            </c:forEach>
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                            <button
                                                class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4"
                                                type="submit">Proceed Checkout</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </c:if>
                    </div>
                </div>
                <!-- Cart Page End -->


                <jsp:include page="../user/layout/header.jsp" />




                <!-- Back to Top -->
                <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
                        class="fa fa-arrow-up"></i></a>


                <!-- JavaScript Libraries -->
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                <script src="client/lib/easing/easing.min.js"></script>
                <script src="client/lib/waypoints/waypoints.min.js"></script>
                <script src="client/lib/lightbox/js/lightbox.min.js"></script>
                <script src="client/lib/owlcarousel/owl.carousel.min.js"></script>

                <!-- Template Javascript -->
                <script src="client/js/main1.js"></script>
            </body>

            </html>