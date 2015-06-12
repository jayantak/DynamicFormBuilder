var items = [];
var fields = [];
var output=[];

$(document).ready(function() {$(function() {

    $.getJSON('data', function(data) {


        $.each(data, function(key, val) {
            fields.push([key, val]);
        });

        for(i = 0; i<fields.length; i++)
        {
            form.addElement(items, fields[i][0], fields[i][1]);
        }

        $('<form/>', {
            html: items.join('')
        }).appendTo(div1);

    });


$(Submit).click(function(){

    var res = '{ ';

    $("input").each(function(){
        output.push([$(this).attr('name'), $(this).val()]);
        res = res + '"' + $(this).attr('name') + '" : "' + $(this).val() + '" ,';
    });

    var res = res + '}';
    console.log(output);
    console.log(res);


    $.post('sendForm', {"param1": res}, function(response) {
        console.log(response);
    }).done(function() {
        console.log("done");
    }).fail(function(e) {
        console.log(e.responseText);
        $(error).html(e.responseText);
    });

})
});
});