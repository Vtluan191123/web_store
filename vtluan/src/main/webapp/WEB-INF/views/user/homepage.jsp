<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8">
                <title>LAPTOP123</title>
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


                <!-- Navbar start -->
                <!-- <div class="container-fluid fixed-top">

                    <div class="container px-0">
                        <nav class="navbar navbar-light bg-white navbar-expand-xl">
                            <a href="index.html" class="navbar-brand">
                                <h1 class="text-primary display-6">LAPTOP123</h1>
                            </a>
                            <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarCollapse">
                                <span class="fa fa-bars text-primary"></span>
                            </button>
                            <div class="collapse navbar-collapse bg-white d-flex justify-content-between"
                                id="navbarCollapse">
                                <div class="navbar-nav " style="margin-left: 100px;">
                                    <a href="/" class="nav-item nav-link active">Home</a>
                                    <a href="/shop" class="nav-item nav-link">Shop</a>
                                </div>
                                <c:if test="${pageContext.request.userPrincipal.name != null}">
                                    <div class="d-flex m-3 me-0">

                                        <a href="/cart" class="position-relative me-4 my-auto">
                                            <i class="fa fa-shopping-bag fa-2x"></i>
                                            <span
                                                class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
                                                style="top: -5px; left: 15px; height: 20px; min-width: 20px;">3</span>
                                        </a>

                                        <div class="nav-item dropdown">
                                            <a href="#"><i class="fas fa-user fa-2x"></i></a>
                                            <div
                                                class="dropdown-menu dropdown-menu-end text-center bg-light border-0 rounded-0 rounded-bottom m-0">

                                                <a href="#" class="dropdown-item">Settings</a>
                                                <form id="logoutForm" action="/logout" method="post">
                                                    <input type="hidden" name="${_csrf.parameterName}"
                                                        value="${_csrf.token}" />
                                                    <button class="w-100 border-0" type="submit">Logout</button>
                                                </form>
                                            </div>
                                        </div>

                                    </div>
                                </c:if>
                                <c:if test="${pageContext.request.userPrincipal.name == null}">
                                    <div style="margin-right: 12px;">
                                        <a style="margin-right: 10px;" href="/login">Đăng nhập</a>
                                        <a href="/register">Đăng ký</a>
                                    </div>
                                </c:if>
                            </div>
                        </nav>
                    </div>
                </div> -->
                <!-- Navbar End -->

                <jsp:include page="../user/layout/header.jsp" />





                <!-- Hero Start -->
                <div class="container-fluid py-5 mb-5 hero-header">
                    <div class="container py-5">
                        <div class="row g-5 align-items-center">
                            <div class="col-md-12 col-lg-7">
                                <h1 class="mb-5 display-3 text-primary">lapTop123</h1>
                                <div class="position-relative mx-auto">
                                    <input class="form-control border-2 border-secondary w-75 py-3 px-4 rounded-pill"
                                        type="text" placeholder="Search">
                                    <button type="submit"
                                        class="btn btn-primary border-2 border-secondary py-3 px-4 position-absolute rounded-pill text-white h-100"
                                        style="top: 0; right: 25%;">Submit Now</button>
                                </div>
                            </div>
                            <div class="col-md-12 col-lg-5">
                                <div id="carouselId" class="carousel slide position-relative" data-bs-ride="carousel">
                                    <div class="carousel-inner" role="listbox">

                                        <div class="carousel-item active rounded">
                                            <img src="client/image/lap2.jpg" class="img-fluid w-100 h-100 rounded"
                                                alt="Second slide">
                                            <a href="#" class="btn px-4 py-2 text-white rounded">ZEPHYRUS</a>
                                        </div>
                                        <div class="carousel-item rounded">
                                            <img src="client/image/lap1.webp" class="img-fluid w-100 h-100 rounded"
                                                alt="Second slide">
                                            <a href="#" class="btn px-4 py-2 text-white rounded">ASUS</a>
                                        </div>

                                    </div>
                                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselId"
                                        data-bs-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Previous</span>
                                    </button>
                                    <button class="carousel-control-next" type="button" data-bs-target="#carouselId"
                                        data-bs-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Next</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Hero End -->





                <!-- Fruits Shop Start-->
                <div class="container-fluid fruite ">
                    <div class="container ">
                        <div class="tab-class text-center">
                            <div class="row g-4">

                                <div class="col-lg-8 text-end">
                                    <ul class="nav nav-pills d-inline-flex text-center mb-5">
                                        <li class="nav-item">
                                            <a class="d-flex m-2 py-2 bg-light rounded-pill active"
                                                data-bs-toggle="pill" href="#tab-1">
                                                <span class="text-dark" style="width: 130px;">All Products</span>
                                            </a>
                                        </li>
                                        <!-- <li class="nav-item">
                                        <a class="d-flex py-2 m-2 bg-light rounded-pill" data-bs-toggle="pill"
                                            href="#tab-2">
                                            <span class="text-dark" style="width: 130px;">Vegetables</span>
                                        </a>
                                    </li> -->

                                    </ul>
                                </div>
                            </div>
                            <div class="tab-content">
                                <div id="tab-1" class="tab-pane fade show p-0 active">
                                    <div class="row g-4">
                                        <div class="col-lg-12">
                                            <div class="row g-4">
                                                <c:forEach var="product" items="${products}">
                                                    <!-- item -->

                                                    <div class="col-md-6 col-lg-4 col-xl-3">
                                                        <a href="/product_detail/${product.id}">
                                                            <div class="rounded position-relative fruite-item">
                                                                <div class="fruite-img">
                                                                    <img style="height: 200px;"
                                                                        src="client/image/${product.image}"
                                                                        class="img-fluid w-100 rounded-top" alt="">
                                                                </div>
                                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                                                    style="top: 10px; left: 10px;">New</div>
                                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom"
                                                                    style="height: 288px;position: relative;">
                                                                    <h4>${product.name}</h4>
                                                                    <p>${product.des_short}</p>

                                                                    <div class="d-flex flex-column justify-content-between "
                                                                        style="    position: absolute; bottom: 25px;right: 75px;">
                                                                        <h5 class="text-dark fs-5 fw-bold mb-0">
                                                                            <span
                                                                                style="font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;">
                                                                                <fmt:formatNumber type="number"
                                                                                    maxFractionDigits="3"
                                                                                    value="${product.price}" />đ
                                                                            </span>

                                                                        </h5>
                                                                        <a href="/add_to_cart?productId=${product.id}"
                                                                            class="mt-3 btn border border-secondary rounded-pill px-3 text-primary"><i
                                                                                class="fa fa-shopping-bag me-2 text-primary"></i>
                                                                            Add to cart</a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </a>

                                                    </div>
                                                    <!-- end -->
                                                </c:forEach>
                                                <div class="col-12 mt-4">
                                                    <div class="pagination d-flex justify-content-center mt-5">
                                                        <a href="/?pageNum=${currentPage-1}"
                                                            class="${currentPage eq 1 ? 'd-none' : ''} rounded">&laquo;</a>

                                                        <c:forEach varStatus="loop" begin="0" end="${totalPage-1}">
                                                            <a href="/?pageNum=${loop.index+1}"
                                                                class=" ${loop.count eq currentPage ? 'active' : ''}  rounded">${loop.index+1}</a>
                                                        </c:forEach>
                                                        <a href="/?pageNum=${currentPage+1}"
                                                            class="${currentPage eq totalPage ? 'd-none' : ''} rounded">&raquo;</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <!-- Fruits Shop End-->





                <!-- Vesitable Shop Start-->
                <div class="container-fluid vesitable py-5">
                    <div class="container ">
                        <h1 class="mb-0">NEW PRODUCTS</h1>
                        <div class="owl-carousel vegetable-carousel justify-content-center">
                            <div class="border border-primary rounded position-relative vesitable-item">
                                <div class="vesitable-img">
                                    <img src="client/image/vegetable-item-6.jpg" class="img-fluid w-100 rounded-top"
                                        alt="">
                                </div>
                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute"
                                    style="top: 10px; right: 10px;">Vegetable</div>
                                <div class="p-4 rounded-bottom">
                                    <h4>Parsely</h4>
                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te
                                        incididunt
                                    </p>
                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                        <p class="text-dark fs-5 fw-bold mb-0">$4.99 / kg</p>
                                        <a href="#"
                                            class="btn border border-secondary rounded-pill px-3 text-primary"><i
                                                class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                    </div>
                                </div>
                            </div>
                            <div class="border border-primary rounded position-relative vesitable-item">
                                <div class="vesitable-img">
                                    <img src="client/image/vegetable-item-1.jpg" class="img-fluid w-100 rounded-top"
                                        alt="">
                                </div>
                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute"
                                    style="top: 10px; right: 10px;">Vegetable</div>
                                <div class="p-4 rounded-bottom">
                                    <h4>Parsely</h4>
                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te
                                        incididunt
                                    </p>
                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                        <p class="text-dark fs-5 fw-bold mb-0">$4.99 / kg</p>
                                        <a href="#"
                                            class="btn border border-secondary rounded-pill px-3 text-primary"><i
                                                class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                    </div>
                                </div>
                            </div>
                            <div class="border border-primary rounded position-relative vesitable-item">
                                <div class="vesitable-img">
                                    <img src="client/image/vegetable-item-3.png"
                                        class="img-fluid w-100 rounded-top bg-light" alt="">
                                </div>
                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute"
                                    style="top: 10px; right: 10px;">Vegetable</div>
                                <div class="p-4 rounded-bottom">
                                    <h4>Banana</h4>
                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te
                                        incididunt
                                    </p>
                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                        <p class="text-dark fs-5 fw-bold mb-0">$7.99 / kg</p>
                                        <a href="#"
                                            class="btn border border-secondary rounded-pill px-3 text-primary"><i
                                                class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                    </div>
                                </div>
                            </div>
                            <div class="border border-primary rounded position-relative vesitable-item">
                                <div class="vesitable-img">
                                    <img src="client/image/vegetable-item-4.jpg" class="img-fluid w-100 rounded-top"
                                        alt="">
                                </div>
                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute"
                                    style="top: 10px; right: 10px;">Vegetable</div>
                                <div class="p-4 rounded-bottom">
                                    <h4>Bell Papper</h4>
                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te
                                        incididunt
                                    </p>
                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                        <p class="text-dark fs-5 fw-bold mb-0">$7.99 / kg</p>
                                        <a href="#"
                                            class="btn border border-secondary rounded-pill px-3 text-primary"><i
                                                class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                    </div>
                                </div>
                            </div>
                            <div class="border border-primary rounded position-relative vesitable-item">
                                <div class="vesitable-img">
                                    <img src="client/image/vegetable-item-5.jpg" class="img-fluid w-100 rounded-top"
                                        alt="">
                                </div>
                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute"
                                    style="top: 10px; right: 10px;">Vegetable</div>
                                <div class="p-4 rounded-bottom">
                                    <h4>Potatoes</h4>
                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te
                                        incididunt
                                    </p>
                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                        <p class="text-dark fs-5 fw-bold mb-0">$7.99 / kg</p>
                                        <a href="#"
                                            class="btn border border-secondary rounded-pill px-3 text-primary"><i
                                                class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                    </div>
                                </div>
                            </div>
                            <div class="border border-primary rounded position-relative vesitable-item">
                                <div class="vesitable-img">
                                    <img src="client/image/vegetable-item-6.jpg" class="img-fluid w-100 rounded-top"
                                        alt="">
                                </div>
                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute"
                                    style="top: 10px; right: 10px;">Vegetable</div>
                                <div class="p-4 rounded-bottom">
                                    <h4>Parsely</h4>
                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te
                                        incididunt
                                    </p>
                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                        <p class="text-dark fs-5 fw-bold mb-0">$7.99 / kg</p>
                                        <a href="#"
                                            class="btn border border-secondary rounded-pill px-3 text-primary"><i
                                                class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                    </div>
                                </div>
                            </div>
                            <div class="border border-primary rounded position-relative vesitable-item">
                                <div class="vesitable-img">
                                    <img src="client/image/vegetable-item-5.jpg" class="img-fluid w-100 rounded-top"
                                        alt="">
                                </div>
                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute"
                                    style="top: 10px; right: 10px;">Vegetable</div>
                                <div class="p-4 rounded-bottom">
                                    <h4>Potatoes</h4>
                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te
                                        incididunt
                                    </p>
                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                        <p class="text-dark fs-5 fw-bold mb-0">$7.99 / kg</p>
                                        <a href="#"
                                            class="btn border border-secondary rounded-pill px-3 text-primary"><i
                                                class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                    </div>
                                </div>
                            </div>
                            <div class="border border-primary rounded position-relative vesitable-item">
                                <div class="vesitable-img">
                                    <img src="client/image/vegetable-item-6.jpg" class="img-fluid w-100 rounded-top"
                                        alt="">
                                </div>
                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute"
                                    style="top: 10px; right: 10px;">Vegetable</div>
                                <div class="p-4 rounded-bottom">
                                    <h4>Parsely</h4>
                                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit sed do eiusmod te
                                        incididunt
                                    </p>
                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                        <p class="text-dark fs-5 fw-bold mb-0">$7.99 / kg</p>
                                        <a href="#"
                                            class="btn border border-secondary rounded-pill px-3 text-primary"><i
                                                class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Vesitable Shop End -->


                <!-- Banner Section Start-->
                <div class="container-fluid banner bg-secondary my-5">
                    <div class="container py-5">
                        <div class="row g-4 align-items-center">
                            <div class="col-lg-6">
                                <div class="py-4">
                                    <h1 class="display-3 text-white">Laptop Hiệu Năng Cao Mới Nhất Có Mặt Tại Cửa Hàng
                                    </h1>

                                    <p class="mb-4 text-dark">Khám phá bộ sưu tập laptop mới nhất, phù hợp cho mọi nhu
                                        cầu công việc và giải trí của bạn. Dù bạn đang làm việc, chơi
                                        game hay xem phim, các sản phẩm của chúng tôi luôn mang lại tốc độ, sức mạnh và
                                        độ tin cậy tốt nhất. Mua sắm tự tin với
                                        những sản phẩm chất lượng cao và dịch vụ hỗ trợ khách hàng tuyệt vời.
                                    </p>
                                    <a href="/shop"
                                        class="banner-btn btn border-2 border-white rounded-pill text-dark py-3 px-5">BUY</a>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="position-relative">
                                    <img src="client/image/laptoppng.png" class="img-fluid w-100 rounded" alt="">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Banner Section End -->


                <jsp:include page="../user/layout/footer.jsp" />





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