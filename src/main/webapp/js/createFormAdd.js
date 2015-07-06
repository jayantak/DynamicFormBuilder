define(['jquery'], function($){


    var errorOut = function (e) {
        console.log(e.responseText);
    };

    var addField = function(fieldNumber){

        var fieldID = 'field' + fieldNumber;
        var typeID = 'type'+ fieldNumber;

        $('#newForm').append('<div class = "pure-control-group"><label for="' + fieldID + '">Field ' + fieldNumber + ':</label><input id ="' + fieldID + '" type="text">' +
            '</input><label for="' + typeID + '"> Type ' + fieldNumber + ':</label><select id ="' + typeID + '" type="text">' +
            '<option value = "text">Text</option>' +
            '<option value = "password">Password</option>' +
            '<option value = "number">Number</option>' +
            '<option value = "date">Date</option>' +
            '<option value = "email">Email</option>' +
            '<option value = "color">Colour</option></select></div>');
    };


    var submitForm = function(fieldCount){

        var formObject = {};

        var formName = $('#formName').val();

        for(i = 1; i <= fieldCount; i++){

            var fieldID = '#field'+i;
            var typeID = '#type'+i+' :selected';

            var attributes = {
                type:($(typeID).val())
            };

            formObject[$(fieldID).val()] = attributes;

        }

        var jsonObject={
            form:formObject,
            formName:formName
        };
        var str1= JSON.stringify(jsonObject);
        var target = 'newForm?form='+formName;

        $.post('sendFields', {"fieldData": str1}, function (response) {
            console.log(str1);
        }).done(function () {
            console.log("done");
            window.location.href = target;
        }).fail(errorOut);
    };

    return {
        addField:addField,
        submitForm:submitForm
    }
});