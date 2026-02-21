<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm ví cá nhân</title>
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
        .add-transaction-btn {
            background-color: darkblue;
            color: white;
            border: none;
            padding: 8px 12px;
            border-radius: 6px;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .add-transaction-btn:hover {
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
        .sign-out a { text-decoration: none; color: #ecebed; }
        .sign-out:hover { background-color: #2196F3; transform: scale(1.02); }
    </style>
</head>
<body class="body">
<jsp:include page="/View/views/fragments/header.jsp" />
<h1 class="qlct">Sửa giao dịch cá nhân</h1>

<form method="post" action="${pageContext.request.contextPath}/vi_ca_nhan/detail"
      onsubmit="return validateAddForm()">
    <div class="transaction-form">
        <input type="text" name="id" value="${VCN_Tim.id}" hidden>
        <label>Loại</label>
        <select name="loai" class="form-select" required>
            <option value="Thu vào" ${VCN_Tim.loai == "Thu vào" ? "checked" : ""}>Thu vào</option>
            <option value="Chi ra" ${VCN_Tim.loai == "Chi ra" ? "checked" : ""}>Chi ra</option>
        </select>

        <label>Số tiền</label>
        <input type="number" name="soTien" class="form-input" min="0" value="${VCN_Tim.soTien}" required/>

        <label>Danh mục</label>
        <select name="danhMuc" class="form-select">
            <option value="Lương" ${VCN_Tim.danhMuc == "Lương" ? "selected" : ""}>Lương</option>
            <option value="Thu nhập thêm" ${VCN_Tim.danhMuc == "Thu nhập thêm" ? "selected" : ""}>Thu nhập thêm</option>
            <option value="Thực phẩm" ${VCN_Tim.danhMuc == "Thực phẩm" ? "selected" : ""}>Thực phẩm</option>
            <option value="Y tế" ${VCN_Tim.danhMuc == "Y tế" ? "selected" : ""}>Y tế</option>
            <option value="Học tập" ${VCN_Tim.danhMuc == "Học tập" ? "selected" : ""}>Học tập</option>
            <option value="Điện nước" ${VCN_Tim.danhMuc == "Điện nước" ? "selected" : ""}>Điện nước</option>
            <option value="Đi lại" ${VCN_Tim.danhMuc == "Đi lại" ? "selected" : ""}>Đi lại</option>
            <option value="Tiền nhà" ${VCN_Tim.danhMuc == "Tiền nhà" ? "selected" : ""}>Tiền nhà</option>
            <option value="Tiết kiệm gia đình" ${VCN_Tim.danhMuc == "Tiết kiệm gia đình" ? "selected" : ""}>Tiết kiệm gia đình</option>
            <option value="Giải trí" ${VCN_Tim.danhMuc == "Giải trí" ? "selected" : ""}>Giải trí</option>
            <option value="Mua sắm" ${VCN_Tim.danhMuc == "Mua sắm" ? "selected" : ""}>Mua sắm</option>
            <option value="Du lịch" ${VCN_Tim.danhMuc == "Du lịch" ? "selected" : ""}>Du lịch</option>
            <option value="Khác" ${VCN_Tim.danhMuc == "Khác" ? "selected" : ""}>Khác</option>
        </select>

        <label>Mô tả</label>
        <input type="text" name="moTa" class="form-input" value="${VCN_Tim.moTa}" required/>

        <label>Thời gian</label>
        <input type="date" name="ngayThang" class="form-input" value="${VCN_Tim.ngayThang}" required/>

        <button type="submit" class="add-transaction-btn" onclick="return checkUpdate()">Cập nhật</button>
    </div>
</form>

<div class="sign-out">
    <a href="${pageContext.request.contextPath}/vi_ca_nhan">Quay lại</a>
</div>

<jsp:include page="/View/views/fragments/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

<script>
    function checkUpdate() {
        return confirm('Bạn có chắc muốn cập nhật giao dịch này?');
    }
</script>
</html>
