<%--
  Created by IntelliJ IDEA.
  User: LAPTOP LE SON
  Date: 11/18/2025
  Time: 9:38 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
    <title>Quản lý chi tiêu - Tổng quan</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
</head>

<style>
    /* === BƯỚC 1: FIX STICKY FOOTER (KHÔNG ẢNH HƯỞNG KÍCH THƯỚC NGANG) === */
    html, body {
        height: 100%;
        margin: 0;
        padding: 0;
    }

    .body{
        background-color: gainsboro;
        min-height: 100%;
        display: flex;
        flex-direction: column;
    }

    .content-wrapper {
        flex-grow: 1;
    }

    /* === BƯỚC 2: GIỮ NGUYÊN KÍCH THƯỚC VÀ BỐ CỤC CŨ === */
    .header {
        margin: 0;
        padding: 10px;
        font-family: Arial;
        font-weight: bold;
        margin-left: 110px;
    }

    .qlct{
        color: darkblue;
    }

    .menu-bar {
        margin-top: 30px;
        display: flex;
        gap: 90px; /* KHOẢNG CÁCH GIỮA CÁC NÚT */
        background: #ecebed; /* NỀN XÁM NHẠT */
        padding: 1px 1px;
        border-radius: 80px; /* VIỀN BO TRÒN */
        width: 1200px;
        margin-left: 90px;
        justify-content: center;
    }

    .button-menu a {
        display: flex;
        justify-content: center;  /* căn giữa ngang */
        align-items: center;      /* căn giữa dọc */
        text-decoration: none;
    }

    .menu-bar a {
        text-decoration: none;
        color: black;
    }

    .menu-tongquan {
        display: flex;
        align-items: center;
        gap: 20px;
        padding: 5px 12px;
        border-radius: 22px;
        font-size: 18px;
        cursor: pointer;
        transition: 0.2s;
        width: 145px;
        height: 37px;
    }

    .menu-tongquan:hover {
        background: rgba(255, 255, 255, 0.4);
    }

    .menu-tongquan.active {
        background: white;
        font-weight: bold;
        box-shadow: 0 2px 6px #0001; /* HIỆU ỨNG NỔI BẬT CHO NÚT ACTIVE */
        width: 145px;
    }

    .icon {
        font-size: 18px;
    }

    .summary-container {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 40px; /* KHOẢNG CÁCH GIỮA 3 Ô */
        margin: 40px 0;
        margin-top: 60px; /* KHOẢNG CÁCH DỌC */
    }

    .summary-box {
        width: 250px; /* CHIỀU RỘNG Ô */
        height: 120px; /* CHIỀU CAO Ô */
        border-radius: 12px;
        text-align: center;
        padding: 20px;
        color: white;
        font-family: Arial;
        box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        transition: transform 0.3s ease;
    }

    .summary-box:hover {
        transform: scale(1.05);
    }

    .thu {
        background-color: #4CAF50;
    }

    .chi {
        background-color: #F44336;
    }

    .tien {
        background-color: #2196F3;
    }

    .sign-out {
        border: 0.3px solid black;
        border-radius: 10px;
        width: fit-content;
        background-color: #F44336;
        font-family: Arial;
        color: #ecebed;
        margin: 20px 105px;
        padding: 5px 15px;
        transition: background-color 0.3s;
    }

    .sign-out a {
        text-decoration: none;
        color: #ecebed;
    }

    .sign-out:hover {
        background-color: #2196F3;
        transform: scale(1.02);
    }

</style>

<body class="body">
<jsp:include page="/View/views/fragments/header.jsp" />

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages"/>
<div class="content-wrapper">

    <div class="header">
        <h1 class="qlct"><fmt:message key="tongquanthuchi"/></h1>
        <p><fmt:message key="theodoitongthu"/></p>
    </div>

    <div class="menu-bar">
        <div class="menu-tongquan active">
            <div class="button-menu">
                <a href="${pageContext.request.contextPath}/tong_quan">
                    <span><span class="icon">📋</span>Tổng quan</span>
                </a>
            </div>
        </div>

        <div class="menu-tongquan">
            <div class="button-menu">
                <a href="${pageContext.request.contextPath}/giao_dich">
                    <span><span class="icon">💳</span>Giao dịch</span>
                </a>
            </div>
        </div>

        <div class="menu-tongquan">
            <div class="button-menu">
                <a href="${pageContext.request.contextPath}/muc_tieu">
                    <span><span class="icon">🎯</span>Mục tiêu</span>
                </a>
            </div>
        </div>

        <div class="menu-tongquan">
            <div class="button-menu">
                <a href="${pageContext.request.contextPath}/vi_ca_nhan">
                    <span><span class="icon">👛</span>Ví cá nhân</span>
                </a>
            </div>
        </div>

        <div class="menu-tongquan">
            <div class="button-menu">
                <a href="${pageContext.request.contextPath}/ThongKe">
                    <span><span class="icon">📊</span>Thống kê</span>
                </a>
            </div>
        </div>
    </div>


    <div class="summary-container">
        <div class="summary-box thu">
            <fmt:setLocale value="vi_VN"/>

            <h3><fmt:message key="tongthu"/></h3>
            <p><fmt:formatNumber value="${tongThu}" type="currency" currencyCode="VND"/></p>
        </div>
        <div class="summary-box chi">
            <h3><fmt:message key="tongchi"/></h3>
            <p><fmt:formatNumber value="${tongChi}" type="currency" currencyCode="VND"/></p>
        </div>
        <div class="summary-box tien">
            <h3><fmt:message key="tongtien"/></h3>
            <p><fmt:formatNumber value="${tongTien}" type="currency" currencyCode="VND"/></p>
        </div>
    </div>

    <div class="sign-out">
        <a href="${pageContext.request.contextPath}/dang_nhap">
            <p><fmt:message key="dangxuat"/></p>
        </a>
    </div>

</div> <jsp:include page="/View/views/fragments/footer.jsp"/>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>