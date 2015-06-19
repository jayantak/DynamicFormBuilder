define(function(require)
{
    var forms = require('../FormAdd.js');
    it('Carousel element should be added', function() {
        var items1 = {};
        items1.carouselItems = [];
        var A = "carousel";
        var B = {
            TestCarouselItem:{
                type:"image",
                file:"img.jpg"
            }
        };
        keys = Object.keys(B);
        forms.addElement([A, B], items1);
        assert.equal(items1.carouselItems[0], '<div class="item"><img class="pure-img" src="images/'+B[keys[0]]["file"]+'"></div>');
    });
    it('Form element should be added', function() {
        var items1 = {};
        items1.formItems = [];
        var C = "form";
        var D = {
            TestFormItem:{
                type:"text"
            }
        };
        keys = Object.keys(D);
        forms.addElement([C, D], items1);
        assert.equal(items1.formItems[0],'<label for="' + keys[0] + '">' + keys[0] + '</label><input name ="' + keys[0] + '" type="' + D[keys[0]]["type"] + '"></input>');
    });

});