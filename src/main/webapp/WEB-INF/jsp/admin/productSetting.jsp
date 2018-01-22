<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<jsp:include page="/frontController?hidden=get_product&product_id=${param.id}"></jsp:include>
<c:set var="product" value="${current_product}"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
</head>
<body>

<Header>
</Header>

<div class="card" style="width: 18rem;">
    <img class="card-img-top" src="/ImageServlet?product_id=${product.id}" alt="Card image cap" height="200px" >
    <div class="card-body">
        <h5 class="card-title">${product.productItemMap.get(language).name}</h5>
        <p class="card-text">${product.productItemMap.get(language).description}.</p>
    </div>
    <ul class="list-group list-group-flush">
        <li class="list-group-item"><h3>Price: </h3>${product.price}</li>
        <li class="list-group-item"><h3>Count: </h3>${product.count}</li>
        <li class="list-group-item"><h3>Manufacture: </h3>${product.productItemMap.get(language).manufacture}</li>
    </ul>
    <div class="card-body">
        <a href="/admin/add/product/items?id=${product.id}" class="card-link">Add items in new Language</a>
        <a href="/admin/product/update?id=${product.id}" class="card-link">Update</a>
    </div>
</div>



</body>
</html>

