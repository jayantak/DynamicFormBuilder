$(document).ready(function() {$(function() {

        $.getJSON('data', function(data) {

            var items = [];
            var fields = [];

            $.each(data, function(key, val) {
                fields.push([key, val]);
            });

            for(i = 0; i<fields.length; i++)
            {
                form.addElement(items, fields[i][0], fields[i][1]);
            }

            $('<form/>', {
                html: items.join('')
            }).appendTo('body');

        });
    });
});