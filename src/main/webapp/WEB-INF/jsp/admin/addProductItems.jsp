
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<form class="form-horizontal" method="post" action="/frontController?${param.id}">

    <input type="hidden" name="hidden" value="add_new_product_items">

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
            <input  name="product_name" type="text" placeholder="" class="form-control input-md" required="">

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
