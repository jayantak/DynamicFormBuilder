define(['jquery', 'formValuesAdd'], function($, Data){

    var doneOut = function () {
        console.log("done");
//        $("#form1")[0].reset();
//        window.location.href = "formsubmitted";
    };

    var errorOut = function (e) {
        console.log(e.responseText);
    };

    $(document).ready(function() {

        $('#filterOption').hide();

        $.getJSON('formFieldNames', Data.createHeaders);

        $.getJSON('allFormResponses', Data.createTable);

        $('#enableFilter').click(function(){
            $('#filterOption').toggle();
        });

        $('#SubmitFilter').click(function(){
            var formData={};
            var fieldsNo = 0;


            $("input").each(function () {
                fieldsNo++;
            });

            for(i = 0; i<fieldsNo; i++){
//                formData[$('#value'+i).attr('name')] = $('#value'+i).val();
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

//            $.post('sendFilter', formData, function (response) {
//                console.log(formData);
//            }).done(doneOut).fail(errorOut);
        });
    });
});