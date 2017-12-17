<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>




<div class="container">
    <form class="form-horizontal" role="form" action="/frontController?hidden=save_product" method="post">

       Eng name : <input type="text" name="eng_name"><br>
       Rus name : <input type="text" name="rus_name"><br>
       Rus Description : <input type="text" name="rus_description"><br>
       Eng Description : <input type="text" name="eng_description"><br>
       Rus Category : <input type="text" name="rus_category"><br>
       Eng Category :  <input type="text" name="eng_category"><br>
       Rus Manufacture : <input type="text" name="rus_manufacture"><br>
       Eng manufacture : <input type="text" name="eng_manufacture"><br>

       Prescription :
        <select>
            <option name="prescription" value="true">yes</option>
            <option name="prescription" value="false">no</option>
        </select>

        Count : <input type="text" name="count"><br>

        Price : <input type="text" name="price"><br>

        Dosage : <input type="text" name="dosage"><br>

        Image : <input type="text" name="image_path"><br>






        <input type="submit" value="Отправить ">
    </form> <!-- /form -->
</div> <!-- ./container -->





</body>
</html>
