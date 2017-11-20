
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer</title>
</head>
<body>

<H1>Hello , ${user.getLogin()}</H1>
<br>
<a href="/frontController?hidden=logout">Sing out</a>
</body>
</html>
