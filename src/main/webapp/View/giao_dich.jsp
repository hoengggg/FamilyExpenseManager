<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Giao dịch</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
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
            margin: 30px auto 0 auto;
            max-width: 1200px;
            width: 95%;
            flex-wrap: wrap;
            gap: 60px;
            justify-content: center;
        }

        .menu-bar a {
            text-decoration: none;
            color: black;
        }

        .button-menu a {
            display: flex;
            justify-content: center;  /* căn giữa ngang */
            align-items: center;      /* căn giữa dọc */
            text-decoration: none;
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
            margin: 40px auto;
            max-width: 1200px;
            width: 95%;
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

        .header-actions{
            display: flex;
            gap: 15px;   /* khoảng cách giữa combobox và nút */
            align-items: center;
        }

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

        @media (max-width: 768px) {

            .header {
                margin-left: 20px;
            }

            .menu-bar {
                border-radius: 20px;
            }

            .transaction-header {
                flex-direction: column;
                align-items: flex-start;
                gap: 15px;
            }

            .header-actions {
                flex-direction: column;
                width: 100%;
            }

            .sign-out {
                margin: 20px auto;
            }
        }
    </style>
</head>
<body>
<jsp:include page="/View/views/fragments/header.jsp" />
<div class="header">
    <h1 class="qlct">Quản lý chi tiêu</h1>
    <p>Quản lý các khoản thu chi của gia đình</p>
</div>

<div class="menu-bar">
    <div class="menu-tongquan ">
        <div class="button-menu">
            <a href="${pageContext.request.contextPath}/tong_quan">
                <span><span class="icon">📋</span>Tổng quan</span>
            </a>
        </div>
    </div>

    <div class="menu-tongquan active">
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
    <div class="transaction-header">
        <h3 class="transaction-title">Danh sách giao dịch</h3>

        <div class="header-actions">
            <form method="get" action="${pageContext.request.contextPath}/giao_dich/search">
                <div class="input-group">
                    <input type="text" name="mota" class="form-control" aria-label="Recipient’s username" aria-describedby="button-addon2" oninput="this.value = this.value.replace(/[^a-zA-Z0-9À-ỹ\s]/g,'')" required>
                    <button class="btn btn-outline-secondary" type="button" id="button-addon2">Tìm kiếm</button>
                </div>
            </form>

            <form method="get" action="${pageContext.request.contextPath}/giao_dich/filter">
                <select name="loai" onchange="this.form.submit()" class="form-select" aria-label="Default select example">
                    <option value="" ${loai == null ? "selected" : ""}>Tất cả</option>
                    <option value="Thu vào" ${loai == "Thu vào" ? "selected" : ""}>Thu vào</option>
                    <option value="Chi ra" ${loai == "Chi ra" ? "selected" : ""}>Chi ra</option>
                </select>
            </form>

            <form method="get" action="${pageContext.request.contextPath}/giao_dich/add">
                <button type="submit" class="add-transaction-btn">Thêm giao dịch</button>
            </form>
        </div>

    </div>
    <div class="table-responsive">
        <table class="bangTT">
            <tr>
                <th>Ngày</th>
                <th>Loại</th>
                <th>Danh mục</th>
                <th>Mô tả</th>
                <th>Số tiền</th>
                <th>Người tạo</th>
                <th>Hành động</th>
            </tr>
            <fmt:setLocale value="vi_VN"/>
            <c:forEach items="${giaodichs}" var="gd">
                <tr>
                    <td>${gd.ngayThangFormatted}</td>
                    <td>${gd.loai}</td>
                    <td>${gd.danhMuc}</td>
                    <td>${gd.moTa}</td>
                    <td><fmt:formatNumber value="${gd.soTien}" type="currency" currencyCode="VND"/></td>
                    <td>${gd.createById.tenDangNhap}</td>
                    <td>
                        <div class="action-buttons">
                            <a href="${pageContext.request.contextPath}/giao_dich/detail?id=${gd.id}" class="btn-view"
                               onclick="return checkUpdate(${gd.createById.id}, '${currentUser.phanQuyen}', ${currentUser.id})">Xem</a>
                            <a href="${pageContext.request.contextPath}/giao_dich/delete?id=${gd.id}" class="btn-delete"
                               onclick="return checkDelete(${mt.createById.id}, '${currentUser.phanQuyen}', ${currentUser.id})">Xóa</a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="sign-out">
    <a href="${pageContext.request.contextPath}/dang_xuat">Đăng xuất</a>
</div>

<jsp:include page="/View/views/fragments/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
    function checkDelete(createdById, role, currentUserId) {
        if (role === 'Parents' || createdById === currentUserId) {
            return confirm('Bạn có chắc muốn xóa ko');
        } else {
            alert('Bạn không có quyền xóa');
            return false;
        }
    }

    function checkUpdate(createdById, role, currentUserId) {
        if (role === 'Parents' || createdById === currentUserId) {
            return confirm('Bạn có chắc muốn sửa ko');
        } else {
            alert('Bạn không có quyền sửa');
            return false;
        }
    }
</script>

</body>
</html>
