define(['jquery', 'formValuesAdd'], function($, Data){

    $(document).ready(function() {

        $.getJSON('formFieldNames', Data.createHeaders);

        $.getJSON('allFormResponses', Data.createTable);
    });
});