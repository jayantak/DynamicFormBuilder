define(['currentForm'], function(Form){

    it('addElement should be successful', function(){


        append = sinon.stub($.fn, "append");

        Form.addElement("KeyName");

        assert(append.calledOnce);
        append.thi

        append.restore();
    })
})