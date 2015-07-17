define(['jquery', 'TableAdd'], function($, Table) {

    $(document).ready(function () {
            $.getJSON('dataOut', Table.createTable);
    });
});