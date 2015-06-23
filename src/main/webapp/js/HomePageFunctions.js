define(['jquery', 'FormAdd'], function($, form)
{
    var doneOut = function () {
        console.log("done");
        $("#form1")[0].reset();
        window.location.href = "formsubmitted";
    };

    var errorOut = function (e) {
        console.log(e.responseText);
    };

    var submitForm = function () {
        var output = [];
        var checkRB={

        };

        var res = '{ ';

        $("input").each(function () {

            if($(this).attr('type')=="radio")
            {
                var rbName=$(this).attr('name');
                checkRB[rbName]=0;
                if(($(this).is(':checked')) && (checkRB[rbName]==0))
                {
                    output.push([$(this).attr('name'), $(this).val()]);
                    res = res + '"' + $(this).attr('name') + '" : "' + $(this).val() + '" ,';
                    checkRB[rbName]=1;
                }

            }
            else {

                output.push([$(this).attr('name'), $(this).val()]);
                res = res + '"' + $(this).attr('name') + '" : "' + $(this).val() + '" ,';
            }

        });

        var res = res + '}';
        console.log("res=",res);

        $.post('sendForm', {"param1": res}, function (response) {
            console.log(response);
        }).done(doneOut).fail(errorOut);


        console.log(res);
        return res;
    };

    return {
        doneOut : doneOut,
        errorOut: errorOut,
        submitForm : submitForm
    }

});