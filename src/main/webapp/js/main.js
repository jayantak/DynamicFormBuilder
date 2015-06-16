var Items = {};

Items.formItems = [];
Items.carouselItems = [];

var fields = [];
var output=[];

$(document).ready(function() {$(function() {

    $.getJSON('formData', function(data) {


        $.each(data, function(key, val) {

            fields.push([key, val]);
        });



        for(i = 0; i<fields.length; i++)
        {
            form.addElement(Items, fields[i][0], fields[i][1]);
        }

        $("#carousel1").html(Items.carouselItems.join(' '));
        $("form").html(Items.formItems.join(' '));
        $("#carousel1").owlCarousel({

            navigation : true, // Show next and prev buttons
            slideSpeed : 300,
            paginationSpeed : 400,
            singleItem:true

        });

    });

    $(Submit).click(function(){

        var res = '{ ';

        $("input").each(function(){
            output.push([$(this).attr('name'), $(this).val()]);
            res = res + '"' + $(this).attr('name') + '" : "' + $(this).val() + '" ,';
        });

        var res = res + '}';


        $.post('sendForm', {"param1": res}, function(response) {
            console.log(response);
        }).done(function() {
            console.log("done");
            $("#form1")[0].reset();
            window.location.href="formsubmitted";
        }).fail(function(e) {
            console.log(e.responseText);
            $(error).html(e.responseText);
        });

    })
});
});