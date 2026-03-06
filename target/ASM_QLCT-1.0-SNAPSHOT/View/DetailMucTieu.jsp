<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Xem/Sửa mục tiêu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .transaction-form {
            display: flex;
            flex-direction: column;
            gap: 10px;
            width: 300px;
            margin: 40px auto;
            font-family: Arial;
        }

        .qlct {
            color: darkblue;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .form-select, .form-input {
            padding: 5px 8px;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 13px;
            width: 100%;
        }
        .update-transaction-btn {
            background-color: darkgreen;
            color: white;
            border: none;
            padding: 8px 12px;
            border-radius: 6px;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .update-transaction-btn:hover {
            background-color: #4CAF50;
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
        .sign-out a { text-decoration: none; color: #ecebed; }
        .sign-out:hover { background-color: #2196F3; transform: scale(1.02); }
    </style>
</head>
<body>
<jsp:include page="/View/views/fragments/header.jsp"/>
<div class="header">
    <h1 class="qlct">Xem và sửa mục tiêu</h1>
</div>

<form method="post" action="${pageContext.request.contextPath}/muc_tieu/update" class="transaction-form">
    <!-- Bỏ input hidden id -->
    <input type="hidden" name="id" value="${mucTieuXem.id}"/>

    <label>Tên mục tiêu</label>
    <input type="text" name="ten" class="form-input" value="${mucTieuXem.ten}" oninput="this.value = this.value.replace(/[^a-zA-Z0-9À-ỹ\s]/g,'')" required/>

    <label>Tiền mục tiêu</label>
    <input type="number" name="tienMucTieu" class="form-input"
           value="<fmt:formatNumber value='${mucTieuXem.tienMucTieu}' pattern='#################'/>"
           min="0" required/>

    <label>Tiền hiện tại</label>
    <input type="number" name="tienHienTai" class="form-input"
           value="<fmt:formatNumber value='${mucTieuXem.tienHienTai}' pattern='#################'/>"
           min="0" required/>

    <label>Thời hạn</label>
    <input type="date" name="thoiHan" class="form-input" value="${mucTieuXem.thoiHan}" required/>

    <button type="submit" class="update-transaction-btn"
            onclick="return checkUpdate(${mucTieuXem.createById}, '${currentUser.phanQuyen}', ${currentUser.id})">
        Cập nhật
    </button>
</form>

<div class="sign-out">
    <a href="${pageContext.request.contextPath}/muc_tieu">Quay lại</a>
</div>

<script>
    function checkUpdate() {
            return confirm('Bạn có chắc muốn cập nhật mục tiêu này?');
    }
</script>

<jsp:include page="/View/views/fragments/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
