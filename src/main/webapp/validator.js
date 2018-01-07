function validateForm() {
    var nameField = document.forms["registration_form"]["name"].value;
    var sureNameField = document.forms["registration_form"]["sureName"].value;
    var emailField = document.forms["registration_form"]["email"].value;
    var passwordField = document.forms["registration_form"]["password"].value;
    var ageField = document.forms["registration_form"]["age"].value;

    var nameRegExp = /^[a-zA-Z ]{2,30}$/;

    var passwordRegExp = new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})");

    var countOfErrors = 0;


    if (nameField == "" || nameField.length < 5) {
        document.getElementById('name_error').innerHTML = "* name should have at least one symbol and size not lower then 5 ";
        countOfErrors = countOfErrors + 1;
    }else {
        document.getElementById('name_error').innerHTML = "";
    }


    if(!nameRegExp.test(nameField)){
        document.getElementById('name_error').innerHTML = "* name should not have start with  '_' symbol and " +
            "include numbers";
        countOfErrors = countOfErrors + 1;
    }else {
        document.getElementById('name_error').innerHTML = "";
    }

    if (sureNameField == "" || nameField.length < 5) {
        document.getElementById('second_name_error').innerHTML = "* Second name should have at least one symbol and size not lower then 5 ";
        countOfErrors = countOfErrors + 1;
    }else {
        document.getElementById('second_name_error').innerHTML = "";
    }


    if(!nameRegExp.test(sureNameField)){
        document.getElementById('second_name_error').innerHTML = "* Second name should not have start with  '_' symbol and " +
            "include numbers";
        countOfErrors = countOfErrors + 1;
    }else {
        document.getElementById('second_name_error').innerHTML = "";

    }

    if(ageField < 7 || ageField > 120 ){
        document.getElementById('age_error').innerHTML = "* age must be between 7 and 120";
        countOfErrors = countOfErrors + 1;
    }else {
        document.getElementById('age_error').innerHTML = "";
    }

    if(!passwordRegExp.test(passwordField) || passwordField.length < 6){
        document.getElementById('password_error').innerHTML = "* password must have at least six symbols,\n" +
            "at least  one number and at least one special character ";
        countOfErrors = countOfErrors + 1;

    }else {
        document.getElementById('password_error').innerHTML = "";
    }



    return countOfErrors == 0;

}



