<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>customers</title>
</head>
<body>

<div id = "ordersBody">
    <table class="table">
        <thead class="thead-inverse">
        <tr>
            <th>Name</th>
            <th>Second Name</th>
            <th>Registration</th>
            <th>E-mail</th>
            <th>Phone number</th>
            <th>Date of birth</th>
            <th>Gender</th>
            <c:forEach items = "${customerList}" var = "customer">
        <tr>
            <td>${customer.getName()} </td>
            <td>${customer.getSureName()} </td>
            <td>${customer.getRegistrationDate()} </td>
            <td>${customer.getEmail()} </td>
            <td>${customer.getPhoneNumber()} </td>
            <td>${customer.getDateOfBirth()} </td>
            <td>${customer.getGender()} </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>
