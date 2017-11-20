<html>
<body>

<div class="index-singIn">
    <form method="post" action="/frontController">
        <input type="hidden" name="hidden" value="sing-in">
        E-mail: <input type="email" name="email"><br>
        <br>
        Password:  <input type="password" name="password"><br>

        <br>
        ${findOrValidationError}

        <br>
        <input type="submit" value="sing-in">
    </form>



</div>

<div class="index-registration">
    <a href="registration.jsp">Registration</a>
</div>

</body>
</html>
