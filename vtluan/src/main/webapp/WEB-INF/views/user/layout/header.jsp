<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!-- Navbar start -->
        <div class="container-fluid fixed-top">

            <div class="container px-0">
                <nav class="navbar navbar-light bg-white navbar-expand-xl">
                    <a href="/" class="navbar-brand">
                        <h1 class="text-primary display-6">LAPTOP123</h1>
                    </a>
                    <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars text-primary"></span>
                    </button>
                    <div class="collapse navbar-collapse bg-white d-flex justify-content-between" id="navbarCollapse">
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
                                        style="top: -5px; left: 15px; height: 20px; min-width: 20px;">${sessionScope.total}</span>
                                </a>

                                <div class="nav-item dropdown">
                                    <a href="#"><i class="fas fa-user fa-2x"></i></a>
                                    <div
                                        class="dropdown-menu dropdown-menu-end text-center bg-light border-0 rounded-0 rounded-bottom m-0">

                                        <a href="#" class="dropdown-item">Settings</a>
                                        <form id="logoutForm" action="/logout" method="post">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
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
        </div>
        <!-- Navbar End -->