define(function(require)
{
    form = require('./FormAdd');

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

        var res = '{ ';

        $("input").each(function () {
            output.push([$(this).attr('name'), $(this).val()]);
            res = res + '"' + $(this).attr('name') + '" : "' + $(this).val() + '" ,';
        });

        var res = res + '}';

        $.post('sendForm', {"param1": res}, function (response) {
            console.log(response);
        }).done(doneOut).fail(errorOut);
    };

    return {
        doneOut : doneOut,
        errorOut: errorOut,
        submitForm : submitForm
    }

});