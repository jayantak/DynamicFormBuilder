define(['jquery', 'FormAdd'], function($, form)
{
var valid=1;
    var doneOut = function () {
        console.log("done");
        $("#form1")[0].reset();
        window.location.href = "formsubmitted";
    };

    var errorOut = function (e) {
        console.log(e.responseText);
    };

    var validation=function(field)
    {
        var keys = Object.keys(field[1]);
        var i;
        if(field[0]=="form")
        {
            for(i=0;i<keys.length;i++)
            {
                    var fieldElement= '#'+keys[i];
                    var value = $('#' + keys[i]).val();

                var noOfValidations = field[1][keys[i]]["validation"].length;

                for(k=0;k<noOfValidations;k++)
                {

                if(field[1][keys[i]]["validation"][k] == "noNumerals")
                 {

                      if(/^[a-zA-Z- ]*$/.test(value) == false) {
                               $(".errors").append("<label>" + keys[i]+ " contains illegal characters</label>");
                               valid=0;
                       }
                }
                if(field[1][keys[i]]["validation"][k] == "numerals")
                {

                        if(/^[a-zA-Z0-9- ]*$/.test(value) == false) {
                             $(".errors").append("<label>" + keys[i]+ " contains illegal characters</label>");
                             valid=0;
                        }
                }

                if(field[1][keys[i]]["validation"][k] == "necessary")
                {
                        if(field[1][keys[i]]["type"] == "text")
                        {
                            if(value.length > 0){
                                 $(".errors").append("<label>" + keys[i]+ " cannot be empty</label>");
                                 valid=0;
                                 }
                        }
                }

                if(field[1][keys[i]]["validation"][k] == "size")
                {

                    if(value.length>8){
                        $(".errors").append("<label>" + keys[i]+ " is too big.Limit to 20 characters</label>");
                        valid=0;
                        }
                }

                if(field[1][keys[i]]["validation"][k] == "email")
                {
                    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
                        if(re.test(value) == false){
                            $(".errors").append("<label>Invalid Email ID</label>");
                            valid=0;
                            }
                }

                if(field[1][keys[i]]["validation"][k] == "password")
                {
                    var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/;
                    if(re.test(value) == false){
                        $(".errors").append("<label>Password should contain at least one number, one lowercase and one uppercase letter and 6 characters/label>");
                        valid=0;
                        }

                }
               }
            }
        }
    };
    var fields1=[];


    var check = function()
    {
           $.getJSON('formData' , function(data)
                {
                console.log(data);
                    $.each(data, function(key,val)
                    {
                            fields1.push([key,val]);
                    });
                    console.log(fields1);

                    var i=0, j=0;

                    while(i<fields1.length)
                    {
                        validation(fields1[i]);
                        i++;
                    }
                });
    };

    var submitForm = function () {
        var formData={};
        var output = [];
        var checkRB={};

        var res = '{ ';

        $("input").each(function () {

            if($(this).attr('type')=="radio") {

                var rbName=$(this).attr('name');
                checkRB[rbName]=0;
                if(($(this).is(':checked')) && (checkRB[rbName]==0)) {

                    output.push([$(this).attr('name'), $(this).val()]);
                    res = res + '"' + $(this).attr('name') + '" : "' + $(this).val() + '" ,';
                    checkRB[rbName]=1;
                    formData[$(this).attr('name')]=$(this).val();
                }
            }
            else {
                output.push([$(this).attr('name'), $(this).val()]);
                res = res + '"' + $(this).attr('name') + '" : "' + $(this).val() + '" ,';
                formData[$(this).attr('name')]=$(this).val();
            }


        });




        var res = res + '}';
        if(valid==1)
        {

        $.post('sendForm', formData, function (response) {
            console.log(formData);
        }).done(doneOut).fail(errorOut);

        return res;
        }
    };

    return {
        doneOut : doneOut,
        errorOut: errorOut,
        submitForm : submitForm,
        check : check,
        validation : validation
    }

});