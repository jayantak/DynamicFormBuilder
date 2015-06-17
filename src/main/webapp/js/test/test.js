var assert = require('assert');
var forms = require('../addElement.js');

describe('Forms', function()
{

    it('Element should be added', function()
    {
        var items1 = new Object();
        items1.carouselItems = [];
        items1.formItems = [];
        var A = "TestCarouselItem";
        var B = {
            "set" : "carousel",
            "type" : "image",
            "file" : "img.jpg"
        };
        var C = "TestFormItem";
        var D = {
            "set" : "form",
            "type" : "text"
        };
        forms.addElement(items1, C, D);
        forms.addElement(items1, A, B);
        console.log(items1[0]);
        assert.equal(items1.formItems[0],'<label for="' + C + '">' + C + '</label><input name ="' + C + '" type="' + D["type"] + '"></input>');
        assert.equal(items1.carouselItems[0], '<div class="item"><img class="pure-img" src="images/'+B["file"]+'"></div>');

    })

});