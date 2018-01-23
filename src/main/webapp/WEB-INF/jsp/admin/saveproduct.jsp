
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





<form class="form-horizontal" enctype="multipart/form-data"  method="post" action="/saveProduct" >

        <!-- Form Name -->
        <legend>Save Product</legend>

    <c:forEach items = "${ProductErrorsList}" var = "error">

        <div class="alert alert-danger" role="alert" style="width: 700px">
            <strong>Mistake!</strong> ${error.message}
        </div>

    </c:forEach>

        <!-- Multiple Radios -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="radios">Language</label>
            <div class="col-md-4">
                <div class="radio">
                    <label for="radios-0">
                        <input type="radio" name="product_language" id="radios-0" value="ru" checked="checked">
                        Rus
                    </label>
                </div>
                <div class="radio">
                    <label for="radios-1">
                        <input type="radio" name="product_language" id="radios-1" value="en">
                        Eng
                    </label>
                </div>
            </div>
        </div>



        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="selectbasic">Category</label>
            <div class="col-md-4">
                <select  name="product_category" class="form-control">
                    <option value="<fmt:message key="SKIN_CARE" />"><fmt:message key="SKIN_CARE" /></option>
                    <option value="<fmt:message key="VITAMINS_AND_SUPPLEMENTS" />"><fmt:message key="VITAMINS_AND_SUPPLEMENTS" /></option>
                    <option value="<fmt:message key="PAIN_RELIEF_AND_MANAGMENT" />"><fmt:message key="PAIN_RELIEF_AND_MANAGMENT" /></option>
                </select>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="textinput">Name</label>
            <div class="col-md-4">
                <input  name="product_name" type="text" placeholder="" class="form-control input-md" required="">

            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="selectbasic">Manufacture</label>
            <div class="col-md-4">
                <select id="selectbasic" name="product_manufacture" class="form-control">
                    <option value="rus">Russia</option>
                    <option value="fr">France</option>
                    <option value="usa">USA</option>
                    <option value="sweed">Sweeden</option>
                    <option value="japan">Japan</option>
                    <option value="poland">Poland</option>
                    <option value="cro">Croatia</option>
                </select>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="textinput">Count</label>
            <div class="col-md-4">
                <input  name="product_count" type="text" placeholder="placeholder" class="form-control input-md" required="">
                <span class="help-block">help</span>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="textinput">Price</label>
            <div class="col-md-4">
                <input  name="product_price" type="text" placeholder="placeholder" class="form-control input-md">
                <span class="help-block">help</span>
            </div>
        </div>

        <!-- Multiple Radios -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="radios">Prescription</label>
            <div class="col-md-4">
                <div class="radio">
                    <label for="radios-2">
                        <input type="radio" name="product_prescription" id="radios-2" value="true" checked="checked">
                        Yes
                    </label>
                </div>
                <div class="radio">
                    <label for="radios-3">
                        <input type="radio" name="product_prescription" id="radios-3" value="false">
                        No
                    </label>
                </div>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="textinput">Image</label>
            <div class="col-md-4">
                <input type="file" id="file" name="product_image" multiple class="form-control input-md">

            </div>
        </div>

        <!-- Textarea -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="textarea">Description</label>
            <div class="col-md-4">
                <textarea class="form-control" id="textarea" name="product_description">default text</textarea>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <input type="submit" class="btn btn-primary" value="Save">
            </div>
        </div>
</form>











</body>
</html>
