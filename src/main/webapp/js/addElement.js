var form=function()
{
}

form.addElement = function(items, key, val)
{
    items.push('<label for="'+ key + '">'+key+'</label><input class = "pure-input-1-2" name ="' + key + '" type="'+ val + '"></input></p>');
}

//module.exports= form;