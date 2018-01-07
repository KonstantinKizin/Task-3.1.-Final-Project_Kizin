<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">

</head>
<body >

<Header>
    <a href="/catalog.jsp?language=en">eng</a>
    <a href="/catalog.jsp?language=ru">rus</a>
</Header>



<div class="container">
    <div class="row">

<c:forEach items = "${productList}" var = "product">

    <div class="col-lg-4 col-md-6 mb-4">
    <div class="card" style="width: 20rem; margin: auto">
        <h4 class="card-title"> ${product.getProductItemMap().get(language).getName()}</h4>
        <img class="card-img-top" style="width: 150px; height: 150px" src="/ImageServlet?product_id=${product.getId()}" alt="Card image cap">
        <div class="card-body">
            <p class="card-text" >${product.getProductItemMap().get(language).getShortDescription()}</p>
            <a href="/frontController?product_id=${product.getId()}&hidden=get_product" class="btn btn-primary">Подробнее</a>
            <a href="/frontController?hidden=go_to_product_setting&product_id=${product.getId()}" class="btn btn-primary">Настроить</a>
        </div>
    </div>
    </div>

    <br>
    <br>


</c:forEach>
    </div>

</div>



</body>
</html>
