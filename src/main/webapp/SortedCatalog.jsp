<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Category" />
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<%--<!-- Select Basic -->--%>
<%--<div class="form-group">--%>
    <%--<label class="col-md-4 control-label" for="selectbasic">Category</label>--%>
    <%--<div class="col-md-4">--%>
        <%--<select  name="product_category" class="form-control">--%>
            <%--<option value="<fmt:message key="category.skin_care" />"><fmt:message key="SKIN_CARE" /></option>--%>
            <%--<option value="<fmt:message key="category.vitamins_and_supplements" />"><fmt:message key="VITAMINS_AND_SUPPLEMENTS" /></option>--%>
            <%--<option value="<fmt:message key="category.pain_relief_and_managment" />"><fmt:message key="PAIN_RELIEF_AND_MANAGMENT" /></option>--%>
        <%--</select>--%>
    <%--</div>--%>
<%--</div>--%>

<div class="container">
    <div class="row">

        <c:forEach begin="0" end="3" varStatus= "${productList}" var="x">
            <c:out value="${x}}"></c:out>
        </c:forEach>
    </div>



</body>
</html>
