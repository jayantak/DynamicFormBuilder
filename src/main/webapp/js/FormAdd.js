define(['jquery', 'owlCarousel'], function($)
{
    var addElement = function(field)
    {

        var keys = Object.keys(field[1]);

        var formLocation = '#' + field[0] + '1';


        if(field[0] == "carousel")
        {
            for(i = 0; i<keys.length; i++){
                $(formLocation).append('<div class="item"><img class="pure-img" src="images/'+field[1][keys[i]]["file"]+'"></div>');
            }
        }
        else if(field[0] == "form") {

            for(i = 0; i<keys.length; i++) {
                $(formLocation).append('<div class="item" id = '+ keys[i] + '><label for="' + keys[i] + '">' + keys[i] + '</label></div>');

                if(field[1][keys[i]]["type"]=="radio")
                {
                    for(j=0;j<field[1][keys[i]]["options"].length;j++)
                    {
                        $('#'+ keys[i]).append('<input type="radio" id ="'+ field[1][keys[i]]["options"][j] + '" name="' + keys[i] + '" value="' +field[1][keys[i]]["options"][j]+ '"> '+ field[1][keys[i]]["options"][j] + '');
                    }
                }

                else
                {
                    $('#'+ keys[i]).append('<input name ="' + keys[i] + '" type="' + field[1][keys[i]]["type"] + '"></input>')
                }


            }
        }
    };

    var createForm =  function(data) {
        var Items = {};
        var fields = [];
        Items.formItems = [];
        Items.carouselItems = [];

        $.each(data, function(key, val) {
            fields.push([key, val]);
        });

        var i = 0;

        while(i<fields.length)
        {
            addElement(fields[i]);
            i++;
        }

        $("#carousel1").owlCarousel({
                navigation : true,
                slideSpeed : 300,
                paginationSpeed : 400,
                singleItem:true
            });
        return Items;
    };

    return {
        addElement : addElement,
        createForm : createForm
    };
});
