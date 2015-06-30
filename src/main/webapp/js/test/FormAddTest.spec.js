define(["FormAdd", "ObjectCompare"], function(forms, objCompare)
{
    it('formText element should be generated', function(){

        var textHTML=  forms.formTextHTML("testField","text");
        assert.equal('<input name ="testField" type="text"></input>' , textHTML);

    });

    it('Carousel element should be generated', function(){

        var carHTML=  forms.carouselHTML("img1.jpg");
        assert.equal('<div class="item"><img class="pure-img" src="images/img1.jpg"></div>' , carHTML);

    });

    it('Div element should be generated', function(){

        var divHTML=  forms.formDivHTML("name1");
        assert.equal('<div class="item" id = name1><label for="name1">name1</label></div>' , divHTML);

    });

    it('radioButton element should be generated', function(){


        var radioHTML=  forms.formRadioHTML("btn1","option1");
        assert.equal('<input type="radio" id ="option1" name="btn1" value="option1"> option1</input>' , radioHTML);

    });

    it('Form Elements should be added', function() {

        var A = "form";
        var B = {
            TestCarouselItem:{
                type:"image",
                file:"img.jpg"
            }
        };

        var append = sinon.stub($.fn, "append");
        var formTextHTML1 = sinon.stub(forms, "formTextHTML");
        var formDivHTML1 = sinon.stub(forms, "formDivHTML");

        keys = Object.keys(B);
        forms.addElement([A, B]);

        assert.equal(append.callCount, 2);
        append.thisValues[0].selector.should.be.equal('#form1');
        append.thisValues[1].selector.should.be.equal('#TestCarouselItem');
        assert.equal(formTextHTML1.callCount, 1);
        assert.equal(formDivHTML1.callCount, 1);

    });

});
