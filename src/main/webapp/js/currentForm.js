define(['jquery'], function($) {
    var addElement = function (key) {
       $('#FormTable').append('<tr><td><a href=""> ' + key + '</a></td></tr>');

    };

    var createTable = function (data) {

        var Items = {};
        Items.tableItems = [];
        console.log(data);

        $.each(data, function (key) {

            addElement(data[key]);
        });

    };

    return {
        addElement : addElement,
        createTable : createTable
    };

});