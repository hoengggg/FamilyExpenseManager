<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Thống kê</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Toàn bộ CSS giữ nguyên từ muc_tieu.jsp */
        body {
            background-color: gainsboro;
            font-family: Arial;
        }
        .header {
            margin: 20px 110px;
            font-weight: bold;
        }
        .qlct { color: darkblue; }
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
            margin: 40px 105px;
            padding: 20px;
            border-radius: 20px;
            background-color: cornsilk;
            border: 1px solid #ccc;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }
        .transaction-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }
        .transaction-title {
            font-size: 22px;
            color: darkblue;
            margin: 0;
        }
        .add-transaction-btn {
            background-color: darkblue;
            color: white;
            border: none;
            padding: 8px 14px;
            border-radius: 6px;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .add-transaction-btn:hover { background-color: #2196F3; }
        .bangTT {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            border-radius: 8px;
            overflow: hidden;
        }
        .bangTT th, .bangTT td {
            padding: 12px 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        .bangTT th {
            background-color: #f0f0f0;
            color: darkblue;
        }
        .bangTT tr:hover { background-color: #f9f9f9; }
        .action-buttons { display: flex; gap: 10px; }
        .btn-view, .btn-delete {
            padding: 6px 12px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 13px;
            transition: background-color 0.3s;
            color: white;
        }
        .btn-view { background-color: #2196F3; }
        .btn-view:hover { background-color: #1976D2; }
        .btn-delete { background-color: #F44336; }
        .btn-delete:hover { background-color: #D32F2F; }
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
        .sign-out a { text-decoration: none; color: #ecebed; }
        .sign-out:hover { background-color: #2196F3; transform: scale(1.02); }
    </style>
    </style>
</head>
<body>
<jsp:include page="/View/views/fragments/header.jsp"/>
<div class="header">
    <h1 class="qlct">Theo Dõi Mục Tiêu Dài Hạn</h1>
    <p>Quản lý các mục tiêu tài chính của bạn</p>
</div>

<div class="menu-bar">
    <div class="menu-tongquan">
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

    <div class="menu-tongquan active">
        <div class="button-menu">
            <a href="${pageContext.request.contextPath}/ThongKe">
                <span><span class="icon">📊</span>Thống kê</span>
            </a>
        </div>
    </div>
</div>

<div class="summary-container">
    <h3>Biểu đồ thu chi theo tháng</h3>
    <canvas id="thuChiChart" width="900" height="400"></canvas>
</div>

<div class="sign-out">
    <a href="${pageContext.request.contextPath}/dang_xuat">Đăng xuất</a>
</div>

<jsp:include page="/View/views/fragments/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>


<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    // Lấy dữ liệu từ Servlet (mảng double[]) và chuyển thành mảng JS
    const tongThu = [
        <c:forEach var="item" items="${tongThu}" varStatus="loop">
        ${item}<c:if test="${!loop.last}">,</c:if>
        </c:forEach>
    ];
    const tongChi = [
        <c:forEach var="item" items="${tongChi}" varStatus="loop">
        ${item}<c:if test="${!loop.last}">,</c:if>
        </c:forEach>
    ];

    const ctx = document.getElementById('thuChiChart').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Tháng 1','Tháng 2','Tháng 3','Tháng 4','Tháng 5','Tháng 6',
                'Tháng 7','Tháng 8','Tháng 9','Tháng 10','Tháng 11','Tháng 12'],
            datasets: [
                {
                    label: 'Thu vào',
                    data: tongThu,
                    backgroundColor: 'rgba(75, 192, 192, 0.7)'
                },
                {
                    label: 'Chi ra',
                    data: tongChi,
                    backgroundColor: 'rgba(255, 99, 132, 0.7)'
                }
            ]
        },
        options: {
            responsive: true,
            plugins: {
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            return context.dataset.label + ': ' + context.formattedValue.toLocaleString() + ' VND';
                        }
                    }
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        callback: function(value) {
                            return value.toLocaleString() + ' VND';
                        }
                    }
                }
            }
        }
    });
</script>

</body>
</html>
