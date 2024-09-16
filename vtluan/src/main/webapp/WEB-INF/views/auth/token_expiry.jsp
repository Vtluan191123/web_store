<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <title>Token Error</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f4f4;
                    margin: 0;
                    padding: 0;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    height: 100vh;
                }

                .error-container {
                    background-color: white;
                    padding: 30px;
                    border-radius: 10px;
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                    text-align: center;
                }

                .error-message {
                    font-size: 18px;
                    color: #e74c3c;
                }

                .back-link {
                    margin-top: 20px;
                    display: inline-block;
                    padding: 10px 20px;
                    background-color: #3498db;
                    color: white;
                    text-decoration: none;
                    border-radius: 5px;
                }

                .back-link:hover {
                    background-color: #2980b9;
                }
            </style>
        </head>

        <body>
            <div class="error-container">
                <!-- Sử dụng Expression Language để hiển thị thông báo lỗi -->
                <c:if test="${not empty tokenNotFound}">
                    <p class="error-message">${tokenNotFound}</p>
                </c:if>

                <c:if test="${not empty tokenExpired}">
                    <p class="error-message">${tokenExpired}</p>
                </c:if>
                <a href="/login" class="back-link">Back to Login</a>
            </div>
        </body>

        </html>