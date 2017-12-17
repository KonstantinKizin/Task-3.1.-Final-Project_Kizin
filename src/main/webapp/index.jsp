<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Text" />

<html lang="${language}">
<body>

<%--change to JSTL this screplet!!--%>
<Header>
    <a href="/index.jsp?language=en">en</a>
    <a href="/index.jsp?language=ru">rus</a>
</Header>


<div class="index-singIn">
    <form method="post" action="/frontController">
        <input type="hidden" name="hidden" value="sing-in">
        <label for="email"><fmt:message key="EMAIL_LABEL" />:</label>
        <input type="email" name="email" id="email"><br>
        <br>
        <label for="email"><fmt:message key="PASSWORD_LABEL" />:</label>
        <input type="password" name="password"><br>

        <br>

        <c:set var="par" value="${sing_in_error}" scope="request"></c:set>
        <c:if test="${par.length() > 0}">
            <p><fmt:message key="LOG_IN_MSG_ERROR"/></p>
        </c:if>



        <br>
        <fmt:message key="SING_IN_BUTTON" var="buttonValue" />
        <input type="submit" value="${buttonValue}">
    </form>

</div>

<div class="index-registration">
    <a href="registration.jsp"><fmt:message key="REGISTRATION_LABEL"/></a>
</div>

</body>
</html>
