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

    var createForm =  function(data) {
        var Items = {};
        var fields = [];
        Items.formItems = [];
        Items.carouselItems = [];
        console.log(data);
        $.each(data, function(key, val) {

            fields.push([key, val]);
        });

        for(i = 0; i<fields.length; i++)
        {
            form.addElement(Items, fields[i][0], fields[i][1]);
        }

        $("form").html(Items.formItems.join(' '));

        $("#carousel1")
            .html(Items.carouselItems.join(' '))
            .owlCarousel({
                navigation : true,
                slideSpeed : 300,
                paginationSpeed : 400,
                singleItem:true
            });

        return Items;
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
        submitForm : submitForm,
        createForm : createForm
    }

});