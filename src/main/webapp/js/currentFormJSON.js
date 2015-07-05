define(['jquery', 'currentForm'], function($, Table) {


    $(document).ready(function () {
        $.getJSON('forms', Table.createTable);
    });
});