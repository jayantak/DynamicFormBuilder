define(['jquery', 'owlCarousel'], function($) {

    var carouselHTML = function(source) {
        return '<div class="item"><img class="pure-img" src="images/'+ source +'"></div>';
    };

    var formTextHTML = function(name, fieldName) {
    var pattern,max=999999,min=0,maxlength=999,pattern=".*";
    if("max" in fieldName)
         max=fieldName["max"];
    if("min" in fieldName)
        min=fieldName["min"];
    if("maxlength" in fieldName)
        maxlength=fieldName["maxlength"];
    if("pattern" in fieldName)
            pattern=fieldName["pattern"];

    if("validation" in fieldName){
    var noOfValidations = fieldName["validation"].length;
    for(k=0;k<noOfValidations;k++)
            {
                if(fieldName["validation"][k] == "necessary")
                {
                if(fieldName["type"] == "number")
                     {
                         return '<input name ="' + name + '" type="' + fieldName["type"] + '" id = "' + name + '" min="' + min +'" max="' + max +'" required pattern="' + pattern +'"></input>';
                     }
                    return '<input name ="' + name + '" type="' + fieldName["type"] + '" id = "' + name + '" required maxlength="' + maxlength + '" pattern="' + pattern +'"></input>';
                }

            }
            if(fieldName["type"] == "number")
            {
               return '<input name ="' + name + '" type="' + fieldName["type"] + '" id = "' + name + '" min="' + min +'" max="' + max +'"pattern="' + pattern +'"></input>';
             }
             }

return '<input name ="' + name + '" type="' + fieldName["type"] + '" id = "' + name + '" required maxlength="' + maxlength + '" pattern="' + pattern +'"></input>';
}
    var formDivHTML = function(name) {
        return '<div class="item" id = '+ name + 'div><label for="' + name + '">' + name + '</label></div>';
    };

    var formRadioHTML = function(name, option){
        return '<input type="radio" id ="'+ option + '" name="' + name + '" value="' +option+ '"> '+ option + '</input>';
    };

    var addElement = function(field) {

        var keys = Object.keys(field[1]);

        var formLocation = '#' + field[0] + '1';

        if(field[0] == "carousel") {
            for(i = 0; i<keys.length; i++) {
                $(formLocation).append(carouselHTML(field[1][keys[i]]["file"]));
            }
        }
        else if(field[0] == "form") {

            for(i = 0; i<keys.length; i++) {

                $(formLocation).append(formDivHTML(keys[i]));

                if(field[1][keys[i]]["type"]=="radio") {

                    for(j=0;j<field[1][keys[i]]["options"].length;j++) {

                        $('#'+ keys[i]).append(formRadioHTML(keys[i],field[1][keys[i]]["options"][j]));
                    }
                }


                else {
                    $('#'+ keys[i] +'div').append(formTextHTML(keys[i], field[1][keys[i]]));
                }

            }
        }
        else if(field[0] == "formName") {
            $('#formName').append(field[1]);
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
//$('input[type="submit"]').attr('enabled','enabled')
        return Items;
    };

    return {
        formRadioHTML : formRadioHTML,
        formDivHTML : formDivHTML,
        carouselHTML : carouselHTML,
        formTextHTML : formTextHTML,
        addElement : addElement,
        createForm : createForm
    };
});
