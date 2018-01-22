<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Text" />


<html>
<head>
    <title>Admin Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"></script>
</head>
<body>

<header>
    <jsp:include page="/WEB-INF/jsp/additional/AccountHeader.jsp"></jsp:include>
</header>



<a class="nav-link" href="/frontController?hidden=go_to_customers"><fmt:message key="SHOW_ALL_CUSTOMERS_LABEL"/></a><br>
<a class="nav-link" href="/admin/save/product"><fmt:message key="SAVE_PRODUCT_PAGE_REDIRECT"/></a><br>


</body>
</html>
