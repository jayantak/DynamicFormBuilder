addElement = function(items, key, val)
{
    items.push('<p>'+ key + ':    ' + '<input id="' + key + '"type="'+ val + '"></input></p>');
}