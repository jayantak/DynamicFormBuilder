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

    var fields1=[];


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


        $.post('sendForm', formData, function (response) {
            console.log(formData);
        }).done(doneOut).fail(errorOut);

        return res;

    };

    return {
        doneOut : doneOut,
        errorOut: errorOut,
        submitForm : submitForm,
    }

});