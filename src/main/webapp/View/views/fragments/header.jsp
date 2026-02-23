<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages"/>

<body><fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages"/></body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="javascript:void(0)">ASM_QLCT</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/tong_quan?lang=cn">Deutsch</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/tong_quan?lang=en">English</a>
                </li>
            </ul>
<%--            <form class="d-flex">--%>
<%--                <input class="form-control me-2" type="text" placeholder="Search">--%>
<%--                <button class="btn btn-primary" type="button">Search</button>--%>
<%--            </form>--%>
        </div>
    </div>
</nav>