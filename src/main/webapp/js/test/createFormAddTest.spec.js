define(['createFormAdd'], function(Create){

    it('Add field should add proper tags', function(){

            append = sinon.stub($.fn, "append");

            Create.addField(1);

            assert(append.calledOnce);
            append.thisValues[0].selector.should.be.equal('#newForm');
            append.restore();
    })

    it('submitForm should successfully call the correct functions', function(){

        val = sinon.stub($.fn, "val");

        Create.submitForm(2);

        val.thisValues[0].selector.should.be.equal('#formName');
        val.thisValues[1].selector.should.be.equal('#type1 :selected');
        val.thisValues[2].selector.should.be.equal('#field1');
        val.thisValues[3].selector.should.be.equal('#type2 :selected');
        val.thisValues[4].selector.should.be.equal('#field2');

        assert(error.notCalled);

        val.restore();

    })

})