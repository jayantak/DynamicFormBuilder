var items = [];
var fields = [];


$(document).ready(function() {$(function() {
    $.getJSON('dataOut', function(data) {

        $.each(data, function(key, val) {
            fields.push([key, val]);
        });



        for(i = 0; i<fields.length; i++)
        {
            form.addElement1(items, fields[i][0], fields[i][1]);
        }

        $("tbody").html(items.join(' '));


});
});
});