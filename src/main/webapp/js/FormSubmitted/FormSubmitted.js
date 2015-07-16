define(function(require) {

    var Table = require('./TableAdd');

    $(document).ready(function () {
            $.getJSON('dataOut', Table.createTable);
    });
});