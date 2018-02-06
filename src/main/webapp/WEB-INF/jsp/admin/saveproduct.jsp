
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Product" />
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<header>
    <jsp:include page="/WEB-INF/jsp/additional/AccountHeader.jsp"></jsp:include>
</header>

<body>
<form class="form-horizontal" enctype="multipart/form-data"  method="post" action="/DispatcherServlet" >

    <input type="hidden" name="hidden" value="save_product">

        <!-- Multiple Radios -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="radios"><fmt:message key="product.language" /></label>
            <div class="col-md-4">
                <div class="radio">
                    <label for="radios-0">
                        <input type="radio" name="product_language" id="radios-0" value="ru" checked="checked">
                        <fmt:message key="product.category.language.rus" />
                    </label>
                </div>
                <div class="radio">
                    <label for="radios-1">
                        <input type="radio" name="product_language" id="radios-1" value="en">
                        <fmt:message key="product.category.language.eng" />
                    </label>
                </div>
            </div>
        </div>



        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="selectbasic"><fmt:message key="product.category" /></label>
            <div class="col-md-4">
                <select  name="product_category" class="form-control">
                    <option value="<fmt:message key="product.category.skin_care" />"></option>
                    <option value="<fmt:message key="product.category.vitamins_and_supplements" />"></option>
                    <option value="<fmt:message key="product.category.pain_relief_and_managment" />"></option>
                </select>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="textinput"><fmt:message key="product.name" /></label>
            <div class="col-md-4">
                <input  name="product_name" type="text" placeholder="" class="form-control input-md" required="">

            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="selectbasic"><fmt:message key="product.manufacture" /></label>
            <div class="col-md-4">
                <select id="selectbasic" name="product_manufacture" class="form-control">
                    <option value="rus"><fmt:message key="product.manufacture.rus" /></option>
                    <option value="fr"><fmt:message key="product.manufacture.fr" /></option>
                    <option value="usa"><fmt:message key="product.manufacture.usa" /></option>
                    <option value="sweed"><fmt:message key="product.manufacture.swe" /></option>
                    <option value="japan"><fmt:message key="product.manufacture.jap" /></option>
                    <option value="japan"><fmt:message key="product.manufacture.fr" /></option>
                </select>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="textinput"><fmt:message key="product.count" /></label>
            <div class="col-md-4">
                <input  name="product_count" type="text"  class="form-control input-md" required="">
                <span class="help-block">help</span>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="textinput"><fmt:message key="product.price" /></label>
            <div class="col-md-4">
                <input  name="product_price" type="text"  class="form-control input-md">
                <span class="help-block">help</span>
            </div>
        </div>

        <!-- Multiple Radios -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="radios"><fmt:message key="product.prescription" /></label>
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
            <label class="col-md-4 control-label" for="textinput"><fmt:message key="product.image" /></label>
            <div class="col-md-4">
                <input type="file" id="file" name="product_image" multiple class="form-control input-md">

            </div>
        </div>

        <!-- Textarea -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="textarea"><fmt:message key="product.description" /></label>
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
