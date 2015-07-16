define(['jquery'], function($){

    var createHeaders = function(data){

        var fields = [];
        $.each(data, function(index, name){

            fields.push(name);
        })
        for(i = 0; i < fields.length; i++){

            $('#fields').append('<th>'+fields[i]+'</th>');
            $('#filterForm').append('<input name = "'+fields[i]+'" id = "value'+i+'" type = "text" placeholder = "'+fields[i]+'"/>');
        }
    }

    var createTable = function(data){
        var fields = [];

        $.each(data, function(data, name){
            fields.push(name);
        })
        console.log(fields);
        for(i = 0; i<fields.length; i++){

            $('#data').append('<tr id = "row'+i+'"></tr>');
            for(j = 0; j<fields[i].length; j++){
                $('#row'+i).append('<td>'+fields[i][j]+'</td>');
            }
        }

    }

    return {
        createHeaders: createHeaders,
        createTable: createTable
    }
})