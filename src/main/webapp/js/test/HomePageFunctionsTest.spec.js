//define(function(require){
//
//    var page = require("../HomePageFunctions.js");
//
//    it('Items object created', function(){
//        var data = {
//            form:{
//                Name:{
//                    type:"text"
//                },
//                Parent:{
//                    type:"password"
//                }
//            },
//            carousel:{
//                Lena:{
//                    type:"image",
//                    file:"img.jpg"
//                },
//                Pikachu:{
//                    type:"image",
//                    file:"img2.jpg"
//                }
//            }
//        };
//
//
//        var ItemsTest = {
//            carouselItems:['<div class="item"><img class="pure-img" src="images/img2.jpg"></div>', '<div class="item"><img class="pure-img" src="images/img.jpg"></div>'],
//            formItems:['<label for="Parent">Parent</label><input name ="Parent" type="password"></input>', '<label for="Name">Name</label><input name ="Name" type="text"></input>']
//
//        };
//
//        assert.equal(ItemsTest, page.createForm(data));
//    })
//});