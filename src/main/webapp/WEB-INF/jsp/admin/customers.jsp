<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Customers" />
<html>
<head>
    <title>customers</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/additional/AccountHeader.jsp"></jsp:include>

<div id = "ordersBody">
    <table class="table table-bordered">
        <thead >
        <tr>
            <th><fmt:message key="customer.name" /></th>
            <th><fmt:message key="customer.sure_name" /></th>
            <th><fmt:message key="customer.registration_date" /></th>
            <th><fmt:message key="customer.email" /></th>
            <th><fmt:message key="customer.phone_number" /></th>
            <th><fmt:message key="customer.age" /></th>
            <th><fmt:message key="customer.gender" /></th>

            <c:forEach items = "${customerList}" var = "customer">
        <tr>
            <td>${customer.name} </td>
            <td>${customer.sureName} </td>
            <td>${customer.registrationDate} </td>
            <td>${customer.email} </td>
            <td>${customer.phoneNumber} </td>
            <td>${customer.dateOfBirth} </td>
            <td>${customer.gender} </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>
