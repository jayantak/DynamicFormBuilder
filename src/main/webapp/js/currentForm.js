define(['jquery'], function($) {
    var addElement = function (key) {
       $('#FormTable').append('<tr><td><a href="/newForm?form='+key+'"> ' + key + '</a></td></tr>');

    };

    var createTable = function (data) {

        var Items = {};
        Items.tableItems = [];

        $.each(data, function (key) {

            addElement(data[key]);
        });

    };

    return {
        addElement : addElement,
        createTable : createTable
    };

});