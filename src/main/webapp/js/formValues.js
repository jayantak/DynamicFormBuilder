define(['jquery', 'formValuesAdd'], function($, Data){

    $(document).ready(function() {

        $('#filterForm').hide();

        $.getJSON('formFieldNames', Data.createHeaders);

        $.getJSON('allFormResponses', Data.createTable);

        $('#enableFilter').click(function(){
            $('#filterForm').toggle();
        });

        $('#SubmitFilter').click(function(){
            var formData={};
            var output = [];
            var checkRB={};

            var res = '{ ';

            $("input").each(function () {
                output.push([$(this).attr('name'), $(this).val()]);
                res = res + '"' + $(this).attr('name') + '" : "' + $(this).val() + '" ,';
                formData[$(this).attr('name')]=$(this).val();
            });

            var res = res + '}';

            $.post('sendFilter', formData, function (response) {
                console.log(formData);
            }).done(doneOut).fail(error Out);

            return res;
        })
    });
});