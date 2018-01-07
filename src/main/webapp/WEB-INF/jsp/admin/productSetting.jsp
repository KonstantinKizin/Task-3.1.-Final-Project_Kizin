<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="product" value="${current_product}"/>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
</head>
<body>

<Header>
    <a href="/product.jsp?language=en">en</a>
    <a href="/product.jsp?language=ru">rus</a>
</Header>


<div class="card" style="width: 18rem;">
    <img class="card-img-top" src="/ImageServlet?product_id=${product.getId()}" alt="Card image cap" height="200px" >
    <div class="card-body">
        <h5 class="card-title">${product.getProductItemMap().get(language).getName()}</h5>
        <p class="card-text">${product.getProductItemMap().get(language).getDescription()}.</p>
    </div>
    <ul class="list-group list-group-flush">
        <li class="list-group-item"><h3>Price: </h3>${product.getPrice()}</li>
        <li class="list-group-item"><h3>Count: </h3>${product.getCount()}</li>
        <li class="list-group-item"><h3>Manufacture: </h3>${product.getProductItemMap().get(language).getManufacture()}</li>
    </ul>
    <div class="card-body">
        <a href="/admin/addItems?product_id=${product.getId()}" class="card-link">Add items in new Language</a>
        <a href="/admin/update_product" class="card-link">Update</a>
        <a href="/frontController?hidden=delete_product&product_id=${product.getId()
        }" class="card-link">Delete</a>
    </div>
</div>



</body>
</html>
