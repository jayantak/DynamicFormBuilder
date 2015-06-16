var form=function()
{
}

form.addElement = function(items, key, val)
{
    if(val["set"] == "carousel")
    {
        items.carouselItems.push('<div class="item"><img src="images/'+val["file"]+'" alt = "Hi"></div>');
    }
    else if(val["set"]=="form") {
        items.formItems.push('<label for="' + key + '">' + key + '</label><input class = "pure-input-1-2" name ="' + key + '" type="' + val["type"] + '"></input>');
    }
}

//module.exports= form;