$(document).ready(function() {$(function() {

    $('#clickme').click(function() {


        $.getJSON('data', function(data) {

            var items = [];
            var fields = [];

            $.each(data, function(key, val) {

                items.push('<li id="' + key + '">' + val + '</li>');
                fields.push([key, val]);
                console.log(fields);
            });

            $('<ul/>', {
                'class': 'interest-list',
                html: items.join('')
            }).appendTo('body');

        });
    });

});
});

