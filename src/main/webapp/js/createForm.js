define(['jquery', 'createFormAdd'], function ($, Create) {
    $(document).ready(function() {$(function() {

        var fieldCount = 1;
        Create.addField(1);

        $('#PlusButton').click(function(){
            fieldCount++;
            Create.addField(fieldCount);
        });

        $("#CreateButton").click(function(){
            Create.submitForm(fieldCount);
        });
    })
    });
});