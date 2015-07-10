define(['createFormAdd'], function(Create){

    it('Add field should add proper tags', function(){

            New = sinon.stub($.fn, "append");

            Create.addField(1);


            assert(New.calledOnce);
    })
})