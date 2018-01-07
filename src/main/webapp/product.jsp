<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>

<Header>
    <a href="/product.jsp?language=en">en</a>
    <a href="/product.jsp?language=ru">rus</a>
</Header>

<div class="product_page_image">
    <img src="/ImageServlet?product_id=${certain_product.getId()}" width="150" height ="150"  border="0">
</div>

<div class="product_page_name"><h1>${certain_product.getProductItemMap().get(language).getName()}</h1></div>
<div class="product_page_manufacture">${certain_product.getProductItemMap().get(language).getManufacture()}</div>
<div class="product_page_description">${certain_product.getProductItemMap().get(language).getDescription()}</div>
<div class="product_page_price">${certain_product.getPrice()}</div>



</body>
</html>
