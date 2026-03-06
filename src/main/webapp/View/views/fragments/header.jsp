<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages"/>

<body><fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages"/></body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="javascript:void(0)">FamilyExpenseManager
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/tong_quan?lang=cn">Tiếng Việt</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/tong_quan?lang=en">English</a>
                </li>
            </ul>
                <span class="text-white">Xin chào: ${sessionScope.currentUser.tenDangNhap}</span>
        </div>
    </div>
</nav>