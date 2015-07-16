define(['jquery', 'currentForm'], function($, Table) {


    $(document).ready(function () {
        $.getJSON('forms', Table.createTable);

        $('#CreateForm').click(function(){
            window.location.href = "createForm";
        })
    });

});