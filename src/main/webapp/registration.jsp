
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="WEB-INF/css/bootstrap/bootstrap.min.css">

</head>
<body>

<div class="container">
    <form class="form-horizontal" role="form" action="/frontController" method="post">
        <input type="hidden" name="hidden" value="registration">
        <h2>Registration Form</h2>
        <div class="form-group">
            <label for="name" class="col-sm-3 control-label">Name</label>
            <div class="col-sm-9">
                <input type="text" id="name"  class="form-control" autofocus name="name">
            </div>
        </div>

        <div class="form-group">
            <label for="sureName" class="col-sm-3 control-label">Second Name</label>
            <div class="col-sm-9">
                <input type="text" id="sureName"  class="form-control" autofocus name="sureName">
            </div>
        </div>


        <div class="form-group">
            <label for="phone" class="col-sm-3 control-label">Phone number</label>
            <div class="col-sm-9">
                <input type="text" id="phone"  class="form-control" autofocus name="phone">
            </div>
        </div>



        <div class="form-group">
            <label for="email" class="col-sm-3 control-label">Email</label>
            <div class="col-sm-9">
                <input type="email" id="email" placeholder="Email" class="form-control" name="email">
            </div>
        </div>


        <div class="form-group">
            <label for="login" class="col-sm-3 control-label">Login</label>
            <div class="col-sm-9">
                <input type="text" id="login" placeholder="Password" class="form-control" name="login">
            </div>
        </div>



        <div class="form-group">
            <label for="password" class="col-sm-3 control-label">Password</label>
            <div class="col-sm-9">
                <input type="password" id="password" placeholder="Password" class="form-control" name="password">
            </div>
        </div>


        <div class="form-group">
            <label for="birthDate" class="col-sm-3 control-label">Date of Birth</label>
            <div class="col-sm-9">
                <input type="date" id="birthDate" class="form-control" name="birthDate">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-3">Gender</label>
            <div class="col-sm-6">
                <div class="row">
                    <div class="col-sm-4">
                        <label class="radio-inline">
                            <input type="radio" id="femaleRadio" value="Female" name="gender">Female
                        </label>
                    </div>
                    <div class="col-sm-4">
                        <label class="radio-inline">
                            <input type="radio" id="maleRadio" value="Male" name="gender">Male
                        </label>
                    </div>
                    <div class="col-sm-4">
                        <label class="radio-inline">
                            <input type="radio" id="uncknownRadio" value="Unknown" name="gender">Unknown
                        </label>
                    </div>
                </div>
            </div>

        </div>

        <input type="submit" value="Registration">
    </form> <!-- /form -->
</div> <!-- ./container -->

</body>
</html>
