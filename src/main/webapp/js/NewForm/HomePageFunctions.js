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
        var checkRB={};

        $("input").each(function () {

            if($(this).attr('type')=="submit") {}
            else if($(this).attr('type')=="radio") {

                var rbName=$(this).attr('name');
                checkRB[rbName]=0;
                if(($(this).is(':checked')) && (checkRB[rbName]==0)) {

                    checkRB[rbName]=1;
                    formData[$(this).attr('name')]=$(this).val();
                }
            }
            else {
                formData[$(this).attr('name')]=$(this).val();
            }
        });


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