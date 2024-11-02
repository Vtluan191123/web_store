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





                <!-- Fruits Shop Start-->
                <div class="container-fluid fruite py-5">
                    <div class="container py-5">
                        <h1 class="mb-4">Filter Products</h1>
                        <div class="row g-4">
                            <div class="col-lg-12">

                                <div class="row g-4">
                                    <div class="col-lg-3">
                                        <div class="row g-4">
                                            <div class="col-lg-12">
                                                <div class="mb-3" id="factory-filter">
                                                    <h4>Hãng sản xuất:</h4>

                                                    <div class="item "><input class="item-check" name="" type="checkbox"
                                                            value="apple" />Apple</div>
                                                    <div class="item"><input class="item-check" type="checkbox"
                                                            value="asus" />Asus
                                                    </div>
                                                    <div class="item"><input class="item-check" type="checkbox"
                                                            value="lenovo" />Lenovo
                                                    </div>
                                                    <div class="item"><input class="item-check" type="checkbox"
                                                            value="dell" />Dell</div>
                                                    <div class="item"><input class="item-check" type="checkbox"
                                                            value="lg" />LG</div>
                                                    <div class="item"><input class="item-check" type="checkbox"
                                                            value="acer" />Acer</div>

                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="mb-3" id="target-filter">
                                                    <h4>Mục đích sử dụng:</h4>

                                                    <div class="item"><input class="item-check" type="checkbox"
                                                            value="gaming" />Gaming
                                                    </div>
                                                    <div class="item"><input class="item-check" type="checkbox"
                                                            value="sv-vp" />Sinh viên -
                                                        Văn Phòng</div>
                                                    <div class="item"><input class="item-check" type="checkbox"
                                                            value="mongnhe" />Mỏng nhẹ
                                                    </div>
                                                    <div class="item"><input class="item-check" type="checkbox"
                                                            value="tkdh" />Thiết kế đồ
                                                        họa</div>

                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="mb-3" id="price-filter">
                                                    <h4>Mức giá:</h4>

                                                    <div class="item"><input class="item-check" type="checkbox"
                                                            value="&lt10" /> &lt 10
                                                        triệu</div>
                                                    <div class="item"><input class="item-check" type="checkbox"
                                                            value="16-20" />16 - 20
                                                        triệu</div>
                                                    <div class="item"><input class="item-check" type="checkbox"
                                                            value="21-25" />21 - 25
                                                        triệu
                                                    </div>
                                                    <div class="item"><input class="item-check" type="checkbox"
                                                            value="26-30" />26 - 30
                                                        triệu</div>
                                                    <div class="item"><input class="item-check" type="checkbox"
                                                            value="&gt30" /> &gt 30
                                                        triệu
                                                    </div>

                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="mb-3" id="sort-filter">
                                                    <h4>Sắp xếp:</h4>

                                                    <div class="item"><input class="item-check" id="sort-nomal"
                                                            name="sort" type="radio" value="normal" checked /> <label
                                                            for="sort-nomal">Không sắp xếp</label>
                                                    </div>
                                                    <div class="item"><input class="item-check" id="sort-giam"
                                                            name="sort" type="radio" value="gia-giam-dan" /> <label
                                                            for="sort-giam">Cao
                                                            đến thấp</label>
                                                    </div>
                                                    <div class="item"><input class="item-check" id="sort-tang"
                                                            name="sort" type="radio" value="gia-tang-dan" /> <label
                                                            for="sort-tang">Thấp đến cao</label>
                                                    </div>


                                                </div>
                                            </div>

                                            <div class="mt-0 ">
                                                <button id="btn-filter"
                                                    class="btn btn-primary border-2 border-secondary py-3 px-4 position-absolute rounded-pill text-white"
                                                    type="submit">Lọc dữ liệu</button>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="col-lg-9">
                                        <div class="row g-4 mb-5">
                                            <c:if test="${empty products}">
                                                <h4 style="text-align: center;" class="mx-auto">Không tìm thấy sản phẩm
                                                    theo yêu cầu</h4>
                                            </c:if>
                                            <c:forEach var="product" items="${products}">
                                                <div class="col-md-6 col-lg-3 col-xl-4">
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
                                                                style="height: 288px; position: relative;">
                                                                <h4>${product.name}</h4>
                                                                <p>${product.des_short}</p>
                                                                <div class="d-flex flex-column justify-content-between"
                                                                    style="position: absolute; bottom: 25px; right: 75px; align-items: center; margin: 0 -20px;">
                                                                    <h5 class="text-dark fs-5 fw-bold mb-0">
                                                                        <span
                                                                            style="font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;">
                                                                            <fmt:formatNumber type="number"
                                                                                maxFractionDigits="3"
                                                                                value="${product.price}" />đ
                                                                        </span>
                                                                    </h5>
                                                                    <a href="/add_to_cart?productId=${product.id}"
                                                                        class="mt-3 btn border border-secondary rounded-pill px-3 text-primary">
                                                                        <i
                                                                            class="fa fa-shopping-bag me-2 text-primary"></i>
                                                                        Add to cart
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </a>
                                                </div>
                                            </c:forEach>


                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Fruits Shop End-->


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