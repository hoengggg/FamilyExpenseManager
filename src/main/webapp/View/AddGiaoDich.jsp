<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm giao dịch</title>
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
<h1 class="qlct">Thêm giao dịch</h1>

<form method="post" action="${pageContext.request.contextPath}/giao_dich/add"
      onsubmit="return validateAddForm()">
    <div class="transaction-form">
        <label>Loại</label>
        <select name="loai" class="form-select" required>
            <option value="Thu vào">Thu vào</option>
            <option value="Chi ra">Chi ra</option>
        </select>

        <label>Số tiền</label>
        <input type="number" name="soTien" class="form-input" min="0" required/>

        <label>Danh mục</label>
        <select name="danhMuc" class="form-select">
            <option value="Lương">Lương</option>
            <option value="Thu nhập thêm">Thu nhập thêm</option>
            <option value="Thực phẩm">Thực phẩm</option>
            <option value="Y tế">Y tế</option>
            <option value="Học tập">Học tập</option>
            <option value="Điện nước">Điện nước</option>
            <option value="Đi lại">Đi lại</option>
            <option value="Tiền nhà">Tiền nhà</option>
            <option value="Tiết kiệm gia đình">Tiết kiệm gia đình</option>
            <option value="Giải trí">Giải trí</option>
            <option value="Mua sắm">Mua sắm</option>
            <option value="Du lịch">Du lịch</option>
            <option value="Khác">Khác</option>
        </select>

        <label>Mô tả</label>
        <input type="text" name="moTa" class="form-input" required/>

        <label>Thời gian</label>
        <input type="date" name="ngayThang" class="form-input" required/>

        <button type="submit" class="add-transaction-btn">Thêm</button>
    </div>
</form>

<div class="sign-out">
    <a href="${pageContext.request.contextPath}/giao_dich">Quay lại</a>
</div>

<jsp:include page="/View/views/fragments/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
