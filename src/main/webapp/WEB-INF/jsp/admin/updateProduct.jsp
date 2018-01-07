<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<form class="form-horizontal" enctype="multipart/form-data"  method="post" action="/updateProduct" >

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



    <!-- Text input-->
    <div class="form-group">
        <label class="col-md-4 control-label" for="textinput">Name</label>
        <div class="col-md-4">
            <input  name="product_name" type="text"  class="form-control input-md" >

        </div>
    </div>


    <!-- Text input-->
    <div class="form-group">
        <label class="col-md-4 control-label" for="textinput">Count</label>
        <div class="col-md-4">
            <input  name="product_count" type="text"  class="form-control input-md" >

        </div>
    </div>


    <!-- Text input-->
    <div class="form-group">
        <label class="col-md-4 control-label" for="textinput">Price</label>
        <div class="col-md-4">
            <input  name="product_price" type="text"  class="form-control input-md">
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
            <textarea class="form-control" id="textarea" name="product_description"></textarea>
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
