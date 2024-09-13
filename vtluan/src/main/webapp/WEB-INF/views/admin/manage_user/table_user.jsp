<%@ page pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">


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
                                    <h1 class="h3 text-dark">Manage User</h1>
                                </a>
                                <div class="col-md-4">
                                    <div class="input-group">
                                        <form method="get" action="/admin/users" class="d-flex">
                                            <input type="search" name="search" class="form-control"
                                                placeholder="Search name" required />
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                            <button class="input-group-text mx-2 border-0" id="search-addon">
                                                <i class="fas fa-search"></i>
                                            </button>

                                        </form>
                                    </div>
                                </div>
                                <a href="/admin/user/create" class="btn btn-primary">Create User</a>
                            </div>
                            <table class="table table-striped table-hover">
                                <thead class="table-dark">
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Password</th>
                                        <th scope="col">Image</th>
                                        <th scope="col">Phone Number</th>
                                        <th scope="col">Role Id</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <c:if test="${empty search}">
                                    <tbody>
                                        <c:forEach var="user" items="${users}">
                                            <c:set var="image" value="${user.image}" />
                                            <tr>
                                                <td>${user.id}</td>
                                                <td>${user.email}</td>
                                                <td>${user.name}</td>
                                                <td>${user.password}</td>
                                                <td>
                                                    <c:set var="image" scope="session" value="${user.image}" />
                                                    <c:if test="${not empty image}">
                                                        <img class="mt-4"
                                                            style="width: 100px;height: 100px;border-radius: 50%;"
                                                            src="image/${user.image}" alt="">
                                                    </c:if>
                                                    <c:if test="${empty image}">
                                                        <img class="mt-4"
                                                            style="width: 100px;height: 100px;border-radius: 50%;"
                                                            src="image/normal_avt.png" alt="">
                                                    </c:if>
                                                </td>
                                                <td>${user.phone_number}</td>
                                                <td>${user.role.name}</td>
                                                <td class="h-100 d-flex justify-content-around flex-column">
                                                    <a class="btn btn-success my-2"
                                                        href="/admin/user/view/${user.id}">View</a>
                                                    <a class="btn btn-warning my-2"
                                                        href="/admin/user/edit/${user.id}">Edit</a>
                                                    <a class="btn btn-danger my-2"
                                                        href="/admin/user/delete/${user.id}">Delete</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </c:if>

                                <!-- search -->
                                <c:if test="${not empty search}">
                                    <tbody>
                                        <c:if test="${not empty results}">
                                            <c:forEach var="result" items="${results}">
                                                <tr>
                                                    <td>${result.id}</td>
                                                    <td>${result.email}</td>
                                                    <td>${result.name}</td>
                                                    <td>${result.password}</td>
                                                    <td>
                                                        <img class="mt-4"
                                                            style="width: 100px;height: 100px;border-radius: 50%;"
                                                            src="image/${result.image}" alt="">
                                                    </td>
                                                    <td>${result.phone_number}</td>
                                                    <td>${result.role.id}</td>
                                                    <td class="h-100 d-flex justify-content-around flex-column">
                                                        <a class="btn btn-success my-2"
                                                            href="/admin/user/view/${result.id}">View</a>
                                                        <a class="btn btn-warning my-2"
                                                            href="/admin/user/edit/${result.id}">Edit</a>
                                                        <a class="btn btn-danger my-2"
                                                            href="/admin/user/delete/${result.id}">Delete</a>
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
                            </table>
                            <c:if test="${empty search}">
                                <div></div>
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination justify-content-center">
                                        <li class="page-item ">
                                            <a class="page-link ${currentPage eq 1 ? 'd-none' : ''}"
                                                href="/admin/users?page=${currentPage-1}" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                        <c:forEach varStatus="loop" begin="0" end="${totalPage-1}">
                                            <li class="page-item ${loop.count eq currentPage ? 'active' : ''}"><a
                                                    class="page-link"
                                                    href="/admin/users?page=${loop.index+1}">${loop.index+1}</a>
                                            </li>
                                        </c:forEach>

                                        <a class="page-link ${currentPage eq totalPage ? 'd-none' : ''}"
                                            href="/admin/users?page=${currentPage+1}" aria-label="Next"
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


            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
            </div>

            <!-- JavaScript Libraries -->
            <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

            <!-- Template Javascript -->
            <script src="js/main.js"></script>
        </body>

        </html>