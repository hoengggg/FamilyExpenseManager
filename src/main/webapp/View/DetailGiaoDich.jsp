<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Xem/Sửa giao dịch</title>
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
<body class="body">
<jsp:include page="/View/views/fragments/header.jsp" />
<h1 class="qlct">Xem và sửa giao dịch</h1>

<form method="post" action="${pageContext.request.contextPath}/giao_dich/detail"
      onsubmit="return validateUpdateForm()">
    <div class="transaction-form">
        <input type="hidden" name="id" value="${giaoDichXem.id}"/>

        <label>Loại</label>
        <select name="loai" class="form-select" required>
            <option value="Thu vào" ${giaoDichXem.loai == 'Thu vào' ? 'selected' : ''}>Thu vào</option>
            <option value="Chi ra" ${giaoDichXem.loai == 'Chi ra' ? 'selected' : ''}>Chi ra</option>
        </select>

        <label>Số tiền</label>
        <input type="number" name="soTien" class="form-input" value="${giaoDichXem.soTien}" min="0" required/>

        <label>Danh mục</label>
        <select name="danhMuc" class="form-select">
            <option value="Lương" ${giaoDichXem.danhMuc == "Lương" ? "selected" : ""}>Lương</option>
            <option value="Thu nhập thêm" ${giaoDichXem.danhMuc == "Thu nhập thêm" ? "selected" : ""}>Thu nhập thêm</option>
            <option value="Thực phẩm" ${giaoDichXem.danhMuc == "Thực phẩm" ? "selected" : ""}>Thực phẩm</option>
            <option value="Y tế" ${giaoDichXem.danhMuc == "Y tế" ? "selected" : ""}>Y tế</option>
            <option value="Học tập" ${giaoDichXem.danhMuc == "Học tập" ? "selected" : ""}>Học tập</option>
            <option value="Điện nước" ${giaoDichXem.danhMuc == "Điện nước" ? "selected" : ""}>Điện nước</option>
            <option value="Đi lại" ${giaoDichXem.danhMuc == "Đi lại" ? "selected" : ""}>Đi lại</option>
            <option value="Tiền nhà" ${giaoDichXem.danhMuc == "Tiền nhà" ? "selected" : ""}>Tiền nhà</option>
            <option value="Tiết kiệm gia đình" ${giaoDichXem.danhMuc == "Tiết kiệm gia đình" ? "selected" : ""}>Tiết kiệm gia đình</option>
            <option value="Giải trí" ${giaoDichXem.danhMuc == "Giải trí" ? "selected" : ""}>Giải trí</option>
            <option value="Mua sắm" ${giaoDichXem.danhMuc == "Mua sắm" ? "selected" : ""}>Mua sắm</option>
            <option value="Du lịch" ${giaoDichXem.danhMuc == "Du lịch" ? "selected" : ""}>Du lịch</option>
            <option value="Khác" ${giaoDichXem.danhMuc == "Khác" ? "selected" : ""}>Khác</option>
        </select>

        <label>Mô tả</label>
        <input type="text" name="moTa" class="form-input" value="${giaoDichXem.moTa}" oninput="this.value = this.value.replace(/[^a-zA-Z0-9À-ỹ\s]/g,'')" required/>

        <label>Thời gian</label>
        <input type="date" name="ngayThang" class="form-input" value="${giaoDichXem.ngayThang}" required/>

        <button type="submit" class="update-transaction-btn">Cập nhật</button>
    </div>
</form>


<div class="sign-out">
    <a href="${pageContext.request.contextPath}/giao_dich">Quay lại</a>
</div>
<script>
    function validateUpdateForm() {
        const soTien = document.querySelector('[name="soTien"]').value;
        if (soTien <= 0) {
            alert("Số tiền phải lớn hơn 0!");
            return false;
        }
        return confirm("Bạn có chắc muốn cập nhật giao dịch này?");
    }
</script>


<jsp:include page="/View/views/fragments/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
