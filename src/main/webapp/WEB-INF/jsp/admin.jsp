
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello Admin</title>
</head>
<body>

<H1>Hello admin ${customer.getName()}</H1>\

<a href="/frontController?hidden=showCustomers">Show all customers</a>
<br>
<a href="/frontController?hidden=logout">Sing out</a>

</body>
</html>
