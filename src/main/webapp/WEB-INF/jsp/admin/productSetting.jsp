<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>

<c:set var="product_id" value="${not empty param.id ? param.id : found_product.id}"></c:set>
<jsp:include page="/frontController?hidden=find_product&product_id=${product_id}"></jsp:include>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
</head>
<body>
<Header>
    <jsp:include page="/WEB-INF/jsp/additional/AccountHeader.jsp"></jsp:include>
</Header>
<c:choose>
        <c:when test="${empty current_product}">
           <c:redirect url="/home"></c:redirect>
        </c:when>
    <c:otherwise>
        <div class="card" style="width: 18rem;">
            <img class="card-img-top" src="/ImageServlet?product_id=${found_product.id}" alt="Card image cap" height="200px" >
            <div class="card-body">
                <h5 class="card-title">${current_product.productItemMap.get(language).name}</h5>
                <p class="card-text">${current_product.productItemMap.get(language).description}.</p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item"><h3>Price: </h3>${current_product.price}</li>
                <li class="list-group-item"><h3>Count: </h3>${current_product.count}</li>
                <li class="list-group-item"><h3>Manufacture: </h3>${current_product.productItemMap.get(language).manufacture}</li>
            </ul>
            <div class="card-body">
                <a href="/admin/add/product/items?id=${current_product.id}" class="card-link">Add items in new Language</a>
                <a href="/admin/update/product?id=${current_product.id}" class="card-link">Update</a>
            </div>
        </div>

    </c:otherwise>
</c:choose>
</body>
</html>

