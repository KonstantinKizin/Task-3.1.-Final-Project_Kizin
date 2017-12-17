
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Text" />
<html lang="${language}">
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="WEB-INF/css/bootstrap/bootstrap.min.css">

</head>
<body>
<header>
<form>

    <Header>
        <a href="/registration.jsp?language=en">en</a>
        <a href="/registration.jsp?language=ru">rus</a>
    </Header>

</form>
</header>


<div class="container">
    <form class="form-horizontal" role="form" action="/frontController" method="post">
        <input type="hidden" name="hidden" value="registration">
        <h2><fmt:message key="REGISTRATION_FORM_LABEL" /></h2>
        <div class="form-group">
            <label for="name" class="col-sm-3 control-label"><fmt:message key="REGISTRATION_FIRST_NAME_LABEL" /></label>
            <div class="col-sm-9">
                <input type="text" id="name"  class="form-control" autofocus name="name">
            </div>
        </div>

        <div class="form-group">
            <label for="sureName" class="col-sm-3 control-label"><fmt:message key="REGISTRATION_SECOND_NAME_LABEL" /></label>
            <div class="col-sm-9">
                <input type="text" id="sureName"  class="form-control" autofocus name="sureName">
            </div>
        </div>


        <div class="form-group">
            <label for="phone" class="col-sm-3 control-label"><fmt:message key="REGISTRATION_PHONE_NUMBER_LABEL" /></label>
            <div class="col-sm-9">
                <input type="text" id="phone"  class="form-control" autofocus name="phone">
            </div>
        </div>



        <div class="form-group">
            <label for="email" class="col-sm-3 control-label"><fmt:message key="EMAIL_LABEL" /></label>
            <div class="col-sm-9">
                <input type="email" id="email" placeholder="Email" class="form-control" name="email">
            </div>
        </div>


        <div class="form-group">
            <label for="login" class="col-sm-3 control-label"><fmt:message key="REGISTRATION_LOGIN_LABEL" /></label>
            <div class="col-sm-9">
                <input type="text" id="login" placeholder="Login" class="form-control" name="login">
            </div>
        </div>



        <div class="form-group">
            <label for="password" class="col-sm-3 control-label"><fmt:message key="PASSWORD_LABEL" /></label>
            <div class="col-sm-9">
                <input type="password" id="password" placeholder="Password" class="form-control" name="password">
            </div>
        </div>


        <div class="form-group">
            <label for="birthDate" class="col-sm-3 control-label"><fmt:message key="REGISTRATION_DATE_OF_BIRTH_LABEL" /></label>
            <div class="col-sm-9">
                <input type="date" id="birthDate" class="form-control" name="birthDate">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-3"><fmt:message key="REGISTRATION_GENDER" /></label>
            <div class="col-sm-6">
                <div class="row">
                    <div class="col-sm-4">
                        <label class="radio-inline">
                            <input type="radio" id="femaleRadio" value="Female" name="gender">
                            <fmt:message key="REGISTRATION_FEMALE_LABEL" />
                        </label>
                    </div>
                    <div class="col-sm-4">
                        <label class="radio-inline">
                            <input type="radio" id="maleRadio" value="Male" name="gender">
                            <fmt:message key="REGISTRATION_MALE_LABEL" />
                        </label>
                    </div>
                </div>
            </div>

        </div>

        <input type="submit" value="<fmt:message key="REGISTRATION_BUTTON" />">
    </form> <!-- /form -->
</div> <!-- ./container -->


<a href="/index.jsp">Go to main </a>

</body>
</html>
