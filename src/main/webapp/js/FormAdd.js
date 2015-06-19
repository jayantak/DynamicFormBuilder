define(function()
{
    var addElement = function(field, items)
    {
        var keys = Object.keys(field[1]);

        if(field[0] == "carousel")
        {
            for(i = 0; i<keys.length; i++){
                items.carouselItems.push('<div class="item"><img class="pure-img" src="images/'+field[1][keys[i]]["file"]+'"></div>');
            }
        }
        else if(field[0] == "form") {
            for(i = 0; i<keys.length; i++) {
                items.formItems.push('<label for="' + keys[i] + '">' + keys[i] + '</label><input name ="' + keys[i] + '" type="' + field[1][keys[i]]["type"] + '"></input>');
            }
        }
    };

    return {
        addElement : addElement
    };
});
