$(document).ready(function() {$(function() {

        $.getJSON('data', function(data) {

            var items = [];
            var fields = [];

            $.each(data, function(key, val) {
                fields.push([key, val]);
                items.push('<p id="' + key + 'p">'+ key + ':    ' + '<input type="'+ val + '"></input></p>');
            });

            $('<form/>', {
                html: items.join('')
            }).appendTo('body');

        });
    });
});