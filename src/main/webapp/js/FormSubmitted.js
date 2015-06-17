define(function(require) {

    var Table = require('./TableAdd');
    var Items = {};

    Items.tableItems = [];
    var fields = [];
    var l;

    $(document).ready(function () {
        $(function () {
            $.getJSON('dataOut', function (data) {

                $.each(data, function (key, val) {
                    fields.push([key, val]);
                    l = fields.length;
                    Table.addElement(Items.tableItems, fields[l - 1][0], fields[l - 1][1]);
                });

                $("#tableSubmitted").html(Items.tableItems.join(' '));
            });
        });
    });
});