define(['currentForm'], function(Form){

    it('addElement should be successful', function(){


        append = sinon.stub($.fn, "append");

        Form.addElement("KeyName");

        assert(append.calledOnce);
        append.thisValues[0].selector.should.be.equal('#FormTable');
        assert(append.calledWith('<tr><td><a href="/newForm?form=KeyName"> KeyName</a></td></tr>'))

        append.restore();
    })
})