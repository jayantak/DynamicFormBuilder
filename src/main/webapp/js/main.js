var Items = {};

Items.formItems = [];
Items.carouselItems = [];

var fields = [];
var output=[];

var doneOut = function() {
    console.log("done");
    $("#form1")[0].reset();
    window.location.href="formsubmitted";
};

var errorOut = function(e) {
    console.log(e.responseText);
};

var submitForm = function(){

    var res = '{ ';

    $("input").each(function(){
        output.push([$(this).attr('name'), $(this).val()]);
        res = res + '"' + $(this).attr('name') + '" : "' + $(this).val() + '" ,';
    });

    var res = res + '}';

    $.post('sendForm', {"param1": res}, function(response) {
        console.log(response);
    }).done(doneOut).fail(errorOut);
};

$(document).ready(function() {$(function() {

    $.getJSON('formData', function(data) {

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
            navigation : true, // Show next and prev buttons
            slideSpeed : 300,
            paginationSpeed : 400,
            singleItem:true
        });
    });

    $("#SubmitButton").click(submitForm);

    })
});
