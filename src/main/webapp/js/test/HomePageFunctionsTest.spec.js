define(['HomePageFunctions', 'FormAdd'], function(Page, Form){

    it('POST HTTPRequest must succeed', function(){

        //var HTMLElements = {};
        //document.getElementById = jasmine.createSpy('HTML Element').and.callFake(function(ID) {
        //    if(!HTMLElements[ID]) {
        //        var newElement = document.createElement('div');
        //        HTMLElements[ID] = newElement;
        //    }
        //    return HTMLElements[ID];
        //});
        //
        //document.getElementById('Hello').setAttribute('name', 'Hellllloo');
        //
        //$('#Hello').html('Blank');
        //
        //console.log(document.getElementById('Hello').getAttribute('name'));
        //console.log(document.getElementById('Hello').());


        //// Fake jQuery methods
        //var jQueryMethods = { toggleClass: 5, siblings: 5 };
        //
        //// First the methods that can be chained
        //sinon.stub(jQueryMethods, 'toggleClass').returns(jQueryMethods);
        //sinon.stub(jQueryMethods, 'siblings').returns(jQueryMethods);

        // Then all jQuery function

        var b = $.parseHTML('<div id ="Jayanta">');

        console.log(b);

        $(b).append('Sudeep');

        $(b).append('Hello');

        $('#Jayanta').append('Third');

        console.log(b);
    })
});