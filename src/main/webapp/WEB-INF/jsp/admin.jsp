<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Text" />
<c:set var="page" value="/WEB-INF/jsp/admin.jsp" scope="page">
</c:set>

<html>
<head>
    <title>Hello Admin</title>
</head>
<body>

<c:set var="current_page" value="/admin_autho" scope="session" />



    <Header>
        <a href="/frontController?hidden=switch_language&language=en">en</a>
        <a href="/frontController?hidden=switch_language&language=ru">rus</a>
    </Header>


<H1><fmt:message key="WELCOME_TO_ADMIN_PAGE"/> ${user.getName()}</H1>

<a href="/frontController?hidden=go_to_customers"><fmt:message key="SHOW_ALL_CUSTOMERS_LABEL"/></a>
<a href="/saveProduct"><fmt:message key="SAVE_PRODUCT_PAGE_REDIRECT"/></a>
<br>
<a href="/frontController?hidden=logout"><fmt:message key="SING_OUT_LABEL"/></a>

</body>
</html>
