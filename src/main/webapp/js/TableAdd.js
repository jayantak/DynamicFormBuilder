define(function() {


    var addElement = function (items, key, val) {
        items.push('<tr><td>' + key + '</td><td>' + val + '</td></tr>');
    };

    return {
        addElement : addElement
    };
});