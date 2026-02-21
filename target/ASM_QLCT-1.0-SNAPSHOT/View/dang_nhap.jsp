<%--
  Created by IntelliJ IDEA.
  User: LAPTOP LE SON
  Date: 11/22/2025
  Time: 12:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Title</title>
  </head>

  <style>
      /* Đảm bảo body và html chiếm hết màn hình */
      html,
      body {
          margin: 0;
          padding: 0;
          width: 100%;
          height: 100%;
          overflow: hidden;
          /* Loại bỏ cuộn dọc nếu có */
      }

      /* Ảnh nền full màn hình */
      .background-image {
          position: absolute;
          top: 0;
          left: 0;
          width: 100%;
          height: 100%;
          object-fit: cover;
          /* Đảm bảo ảnh phủ toàn bộ màn hình mà không bị kéo dãn */
          z-index: -1;
          /* Đặt ảnh nền dưới các phần tử khác */
      }

      /* Form đăng nhập */
      .form-container {
          width: 400px;
          padding: 20px;
          border-radius: 10px;
          background: rgba(255, 255, 255, 0.2);
          /* Làm nền mờ nhạt */
          backdrop-filter: blur(5px);
          /* Làm mờ nền phía sau */
          box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.2);
          margin: 0 auto;
          position: absolute;
          top: 50%;
          left: 50%;
          transform: translate(-50%, -50%);
          z-index: 1;
          border: 3px solid rgba(255, 255, 255, 0.3);
          /* Viền nhẹ giúp nổi bật */
          justify-content: space-around;
      }

      .form-container label {
          display: block;
          margin-top: 10px;
          font-weight: bold;
          color: darkblue;
      }

      .form-container input {
          width: 95%;
          padding: 8px;
          margin-top: 5px;
          border: 1px solid #ccc;
          border-radius: 5px;
      }

      /* Nút đăng nhập */
      .form-container button {
          width: 98.6%;
          padding: 10px;
          margin-top: 15px;
          border: none;
          background-color: darkolivegreen;
          color: white;
          font-size: 16px;
          border-radius: 5px;
          cursor: pointer;
      }

      .form-container button:hover {
          background-color: darkblue;
      }

      h1 {
          text-align: center;
          margin-bottom: 20px;
          color: darkblue;
      }

      .form-container button a {
          text-decoration: none;
          color: white;
      }

      .logo {
          font-size: 25px;
          font-family: 'Playfair Display', serif;
          font-weight: bold;
          margin: 0;
          padding: 10px;
          mix-blend-mode: multiply;
          /* Biến nền trắng thành trong suốt */
      }

      .tieude{
          margin-top: 70px;
          font-family: Arial;
      }

      html, body {
          margin: 0;
          padding: 0;
          width: 100%;
          height: 100%;
          overflow: hidden;
          /* Gradient nhiều màu */
          background: linear-gradient(45deg, #2196F3, #77ffff, aquamarine, cyan);
      }

  </style>

  <body>

  <h1 class="tieude">Đăng nhập hệ thống</h1>

  <c:if test="${not empty error}">
      <script>
          alert("${error}");
      </script>
  </c:if>

    <div class="form-container">
        <form action="dang_nhap" method="post">
            <label>Tên đăng nhập</label>
            <input type="text" name="username" required>
            <br>
            <label>Mật khẩu</label>
            <input type="password" name="password" required>
            <br>
            <button type="submit">Đăng nhập</button>
        </form>
    </div>
  </body>
</html>
