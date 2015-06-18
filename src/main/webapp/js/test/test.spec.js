define(function(require)
{
    var forms = require('../FormAdd.js');
    it('Carousel element should be added', function() {
        var items1 = {};
        items1.carouselItems = [];
        var A = "TestCarouselItem";
        var B = {
            "set" : "carousel",
            "type" : "image",
            "file" : "img.jpg"
        };
        forms.addElement(items1, A, B);
        assert.equal(items1.carouselItems[0], '<div class="item"><img class="pure-img" src="images/'+B["file"]+'"></div>');
    });
    it('Form element should be added', function() {
        var items1 = {};
        items1.formItems = [];
        var C = "TestFormItem";
        var D = {
            "set" : "form",
            "type" : "text"
        };
        forms.addElement(items1, C, D);
        assert.equal(items1.formItems[0],'<label for="' + C + '">' + C + '</label><input name ="' + C + '" type="' + D["type"] + '"></input>');
    })
});