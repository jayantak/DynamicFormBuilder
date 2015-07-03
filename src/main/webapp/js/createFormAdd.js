define(['jquery'], function($){

    var doneOut = function () {
        console.log("done");
        window.location.href = "newForm";
    };

    var errorOut = function (e) {
        console.log(e.responseText);
    };

    var addField = function(fieldNumber){

        var fieldID = 'field' + fieldNumber;
        var typeID = 'type'+ fieldNumber;


        $('#newForm').append('<div class = "pure-control-group"><label for="' + fieldID + '">Field ' + fieldNumber + ':</label><input id ="' + fieldID + '" type="text">' +
            '</input><label for="' + typeID + '"> Type ' + fieldNumber + ':</label><input id ="' + typeID + '" type="text"></input></div>');
    };


    var submitForm = function(fieldCount){

        var formObject = {};

        for(i = 1; i <= fieldCount; i++){

            var fieldID = '#field'+i;
            var typeID = '#type'+i;

            var attributes = {
                type:($(typeID).val())
            };

            formObject[$(fieldID).val()] = attributes;

        }

        var jsonObject={
            form:formObject
        };
       var str1= JSON.stringify(jsonObject);

        $.post('sendFields', {"param1": str1}, function (response) {
            console.log(str1);
        }).done(doneOut).fail(errorOut);
    };

    return {
        addField:addField,
        submitForm:submitForm
    }
});