define(function() {

    var addElement = function (items, key, val) {
        items.push('<tr><td>' + key + '</td><td>' + val + '</td></tr>');
    };

    var createTable = function (data) {

        var Items = {};
        Items.tableItems = [];
        var fields = [];
        var l;

        $.each(data, function (key, val) {
            fields.push([key, val]);
            l = fields.length;
            addElement(Items.tableItems, fields[l - 1][0], fields[l - 1][1]);
        });

        $("#tableSubmitted").html(Items.tableItems.join(' '));
    };

    return {
        addElement : addElement,
        createTable : createTable
    };
});