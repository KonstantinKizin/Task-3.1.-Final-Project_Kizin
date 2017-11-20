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
            <c:forEach items = "${customerList}" var = "customers">
        <tr>
            <td>${customers.getName()} </td>
            <td>${customers.getSureName()} </td>
            <td>${customers.getRegistrationDate()} </td>
            <td>${customers.getEmail()} </td>
            <td>${customers.getPhoneNumber()} </td>
            <td>${customers.getDateOfBirth()} </td>
            <td>${customers.getGender()} </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>
