<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Text" />

<html>
<head>
    <title>Customer</title>
</head>
<body>
<c:set var="current_page" value="/customer_autho" scope="session" />
<jsp:include page="additional/AccountHeader.jsp"></jsp:include>
<Header>
</Header>


<H1><fmt:message key="WELCOME_TO_CUSTOMER_PAGE"/> , ${user.getLogin()}</H1>
<br>
<a href="/frontController?hidden=logout"><fmt:message key="SING_OUT_LABEL"/></a>
</body>
</html>
