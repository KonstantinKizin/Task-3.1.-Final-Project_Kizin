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
    <c:set var="products" value="${productList}"></c:set>
</header>

<div class="container">
    <div class="row">
        <c:forEach items = "${productPart}" var = "product">
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="card" style="width: 20rem; margin: auto">
                    <h4 class="card-title"> ${product.productItemMap.get(language).name}</h4>
                    <img class="card-img-top" style="width: 150px; height: 150px" src="ImageServlet?product_id=${product.id}" alt="Card image cap">
                    <div class="card-body">
                        <p class="card-text" >${product.productItemMap.get(language).shortDescription}</p>

                        <%--<c:choose>--%>
                            <%--<c:when test="${user.role eq 'admin'}">--%>
                                <%--<a href="/admin/product/setting?id=${product.id}" class="btn btn-primary">Настроить</a><br>--%>
                            <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                <%--<a href="/frontController?hidden=get_product&product_id=${product.id}" class="btn btn-primary">Подробнее</a><br>--%>
                                <%--<a href="/frontController?hidden=get_product&product_id=${product.id}" class="btn btn-primary">Добавить--%>
                                <%--</c:otherwise>--%>
                        <%--</c:choose>--%>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <%--Pagination--%>

    <c:if test="${currentPage != 1}">
    <td><a href="/frontController?hidden=get_page&page=${currentPage - 1}">Previous</a></td>
    </c:if>

    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="/frontController?hidden=get_page&page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>

    <c:if test="${currentPage lt noOfPages}">
    <td><a href="/frontController?hidden=get_page&page=${currentPage + 1}">Next</a></td>
    </c:if>

</body>
</html>
