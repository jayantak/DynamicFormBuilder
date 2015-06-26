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
        var a={

        };
        var output = [];
        var checkRB={

        };

        var res = '{ ';

        $("input").each(function () {

            if($(this).attr('type')=="radio")
            {
                var rbName=$(this).attr('name');
                console.log(rbName);
                checkRB[rbName]=0;
                if(($(this).is(':checked')) && (checkRB[rbName]==0))
                {
                    output.push([$(this).attr('name'), $(this).val()]);
                    res = res + '"' + $(this).attr('name') + '" : "' + $(this).val() + '" ,';
                    checkRB[rbName]=1;
                    a[$(this).attr('name')]=$(this).val();
                }

            }
            else {

                output.push([$(this).attr('name'), $(this).val()]);
                res = res + '"' + $(this).attr('name') + '" : "' + $(this).val() + '" ,';
                a[$(this).attr('name')]=$(this).val();
            }

        });

        var res = res + '}';
        console.log(a.toString());

        $.post('sendForm', a, function (response) {
            console.log(a);
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