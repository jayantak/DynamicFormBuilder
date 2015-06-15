var items = [];
var fields = [];
var output=[];

$(document).ready(function() {$(function() {

    $("image").attr('src', '/image.jpg');

    $.getJSON('formData', function(data) {


        $.each(data, function(key, val) {
            fields.push([key, val]);
        });



        for(i = 0; i<fields.length; i++)
        {
            form.addElement(items, fields[i][0], fields[i][1]);
        }

        //items.push('<button id ="Submit" type="submit" class="pure-button">Submit</button>');

        $("form").html(items.join(' '));

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
        $("form")[0].reset();
        window.location.href="formsubmitted";
    }).fail(function(e) {
        console.log(e.responseText);
        $(error).html(e.responseText);
    });

})
});
});