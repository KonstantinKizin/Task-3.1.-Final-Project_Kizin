
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Text" />

<html>
<head>
    <title>Login</title>

</head>
<body>

<header>
    <jsp:include page="WEB-INF/jsp/additional/header.jsp"></jsp:include>
</header>

<div style="width: 450px; margin: 0 auto; margin-top: 100px; padding: 10px" class="border rounded" >
    <form method="post" action="/frontController">
        <input type="hidden" name="hidden" value="sing-in">
        <div class="form-group">
            <label for="emailField"><fmt:message key="EMAIL_LABEL" /></label>
            <input type="email" class="form-control" id="emailField" name="email">
        </div>
        <div class="form-group">
            <label for="passwordField"><fmt:message key="PASSWORD_LABEL" /></label>
            <input type="password" class="form-control" id="passwordField"  name="password">
        </div>
        <br>

        <c:set var="par" value="${sing_in_error}" scope="session"></c:set>
        <c:if test="${par.length() > 0}">
            <p style="color: red"><fmt:message key="LOG_IN_MSG_ERROR"/></p>
        </c:if>

        <br>
        <button type="submit" class="btn btn-primary"><fmt:message key="SING_IN_BUTTON" /></button>
    </form>
</div>


</body>
</html>
