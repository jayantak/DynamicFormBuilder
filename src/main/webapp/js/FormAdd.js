define(function()
{
    var addElement = function(items, key, val)
    {
        if(val["set"] == "carousel")
        {
            items.carouselItems.push('<div class="item"><img class="pure-img" src="images/'+val["file"]+'"></div>');
        }
        else if(val["set"]=="form") {
            items.formItems.push('<label for="' + key + '">' + key + '</label><input name ="' + key + '" type="' + val["type"] + '"></input>');
        }
    };

    return {
        addElement : addElement
    };
});
