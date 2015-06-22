define(function(require, jquery)
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

    it('Object "Items" should be created correctly', function(){
        var data = {
            form:{
                Name:{
                    type:"text"
                },
                Parent:{
                    type:"password"
                }
            },
            carousel:{
                Lena:{
                    type:"image",
                    file:"img.jpg"
                },
                Pikachu:{
                    type:"image",
                    file:"img2.jpg"
                }
            }
        };


        var ItemsTest = {
            carouselItems:['<div class="item"><img class="pure-img" src="images/img2.jpg"></div>', '<div class="item"><img class="pure-img" src="images/img.jpg"></div>'],
            formItems:['<label for="Parent">Parent</label><input name ="Parent" type="password"></input>', '<label for="Name">Name</label><input name ="Name" type="text"></input>']

        };

        assert.equal(ItemsTest, forms.createForm(data));
    })
    });
