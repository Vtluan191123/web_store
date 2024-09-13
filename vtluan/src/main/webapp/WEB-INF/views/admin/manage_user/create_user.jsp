<%@ page pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8">
                <title>Create User</title>
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
                        display: none;
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
                                <h1 class="mb-2">Create New User</h1>
                                <form:form method="post" action="/admin/user/created" modelAttribute="user"
                                    enctype="multipart/form-data">
                                    <div class="row">
                                        <c:set var="passwordError">
                                            <form:errors style="color: red;" path="password" />
                                        </c:set>
                                        <c:set var="nameError">
                                            <form:errors style="color: red;" path="name" />
                                        </c:set>
                                        <c:set var="emailError">
                                            <form:errors style="color: red;" path="email" />
                                        </c:set>
                                        <c:set var="phone_numberError">
                                            <form:errors style="color: red;" path="phone_number" />
                                        </c:set>
                                        <!-- First Column -->
                                        <div class="col-md-4">
                                            <div class="mb-3">
                                                <label for="email" class="form-label">Email</label>
                                                <form:input path="email" type="email"
                                                    class="form-control ${not empty emailError ? 'is-invalid' : ''}"
                                                    id="email" />
                                                ${emailError}
                                            </div>
                                            <div class="mb-3">
                                                <label for="name" class="form-label">Name</label>
                                                <form:input path="name" type="text"
                                                    class="form-control ${not empty nameError ? 'is-invalid' : ''}"
                                                    id="name" />
                                                ${nameError}
                                            </div>

                                        </div>
                                        <!-- Second Column -->
                                        <div class="col-md-4">
                                            <div class="mb-3">
                                                <label for="phone_number" class="form-label">Phone Number</label>
                                                <form:input path="phone_number" type="text"
                                                    class="form-control ${not empty phone_numberError ? 'is-invalid' : ''}"
                                                    id="phone_number" />
                                                ${phone_numberError}
                                            </div>
                                            <div class="mb-3">
                                                <label for="password" class="form-label ">Password</label>
                                                <form:input type="password" path="password"
                                                    class="form-control ${not empty passwordError ? 'is-invalid' : ''}"
                                                    id="password" />
                                                ${passwordError}
                                            </div>


                                        </div>
                                        <div class="col-md-4">
                                            <label for="select" class="form-label">Role</label>
                                            <form:select path="role.name" class="form-select" id="select">
                                                <form:option value="ADMIN" />
                                                <form:option value="USER" />
                                            </form:select>

                                            <div class="mt-3">
                                                <label for="image" class="form-label">Image</label>
                                                <input type="file" class="form-control" id="image" name="file" />
                                            </div>
                                            <img id="imagePreview" src="#" alt="Image Preview" />
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                    <a href="/admin/users" class="btn btn-success">Back</a>
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
                <script>
                    $(document).ready(function () {
                        $('#image').change(function (event) {
                            var file = event.target.files[0];
                            if (file) {
                                var reader = new FileReader();
                                reader.onload = function (e) {
                                    $('#imagePreview').attr('src', e.target.result).show();
                                }
                                reader.readAsDataURL(file);
                            }
                        });
                    });
                </script>
            </body>

            </html>