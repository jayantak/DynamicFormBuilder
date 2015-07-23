define(['jquery', 'formValuesAdd'], function($, Data){

    var doneOut = function () {
        console.log("done");
    };

    var errorOut = function (e) {
        console.log(e.responseText);
    };

    $(document).ready(function() {

        var filter = 'Filter &#x25B6';

        $.getJSON('formFieldNames', Data.createHeaders);

        $.getJSON('allFormResponses', Data.createTable);

        $('#enableFilter').click(function(){
            $('#filterOption').slideToggle();

            filter = (filter == 'Filter &#x25BC'? 'Filter &#x25B6': 'Filter &#x25BC');
            $('#enableFilter').html(filter);
        });

        $('#SubmitFilter').click(function(){
            var formData={};
            var fieldsNo = 0;

            $("input").each(function () {
                fieldsNo++;
            });

            for(i = 0; i<fieldsNo; i++){
                formData[i] = $('#value'+i).val();
            }

            $.getJSON('allFormResponses', function(data){

                $('#data').html('');
                var fields = [];
                var filter  = 1;

                $.each(data, function(data, name){
                    fields.push(name);
                })

                for(i = 0; i<fields.length; i++){

                    filter = 1;

                    for(j = 0; j<fields[i].length; j++){
                        if((fields[i][j] != formData[j])&&(formData[j]!="")) {
                            filter = 0;
                        }

                    }
                    if(filter){
                        $('#data').append('<tr id = "row'+i+'"></tr>');

                        for(j = 0; j<fields[i].length; j++){
                            $('#row'+i).append('<td>'+fields[i][j]+'</td>');
                        }
                    }
                }
            })
        });
    });
});