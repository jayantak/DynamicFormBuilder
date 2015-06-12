var form=function()
{
}

form.addElement = function(items, key, val)
{
    items.push('<p>'+ key + ':    <input name ="Field' + key + '" type="'+ val + '"></input></p>');
}

//module.exports= form;