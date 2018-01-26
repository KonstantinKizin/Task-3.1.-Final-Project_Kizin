<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<c:set var="product_id"  value="${param.id}"></c:set>
<c:set var="product" value="${current_product}"/>
<fmt:setLocale value="${language}" />

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
</head>
<body>

<header>
    <jsp:include page="/WEB-INF/jsp/additional/Header.jsp"></jsp:include>
</header>


<div class="card" style="width: 18rem;">
    <img class="card-img-top" src="/ImageServlet?product_id=${current_product.id}" alt="Card image cap" height="200px" >
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
        <a href="" class="card-link">Buy</a>
        <a href="" class="card-link">Add in bucket</a>
    </div>
</div>





</body>
</html>
