<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : sessionScope.locale.language}" scope="session" />
<fmt:setLocale value="${language}" />
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"></script>
</head>
<body >
<header>
<jsp:include page="/WEB-INF/jsp/additional/Header.jsp"></jsp:include>
</header>
<c:choose>
    <c:when test="${user.role eq 'admin'}">
<div class="container">
    <div class="row">
        <c:forEach items = "${productList}" var = "product">
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card" style="width: 20rem; margin: auto">
                    <h4 class="card-title"> ${product.productItemMap.get(language).name}</h4>
                    <img class="card-img-top" style="width: 150px; height: 150px" src="ImageServlet?product_id=${product.id}" alt="Card image cap">
                    <div class="card-body">
                        <p class="card-text" >${product.productItemMap.get(language).shortDescription}</p>
                        <a href="/admin/product/setting?id=${product.id}" class="btn btn-primary">Настроить</a><br>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    </c:when>
    <c:otherwise>
    <div class="container">
        <div class="row">
            <c:forEach items = "${productList}" var = "product">
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card" style="width: 20rem; margin: auto">
                        <h4 class="card-title"> ${product.productItemMap.get(language).name}</h4>
                        <img class="card-img-top" style="width: 150px; height: 150px" src="ImageServlet?product_id=${product.id}" alt="Card image cap">
                        <div class="card-body">
                            <p class="card-text" >${product.productItemMap.get(language).shortDescription}</p>
                            <a href="/frontController?hidden=get_product&product_id=${product.id}" class="btn btn-primary">Подробнее</a><br>
                            <a href="/frontController?hidden=get_product&product_id=${product.id}" class="btn btn-primary">Добавить в корзину</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

    </c:otherwise>
</c:choose>

</body>
</html>
