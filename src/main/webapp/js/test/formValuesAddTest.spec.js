define(['jquery', 'formValuesAdd'], function($, Form){
    it('createHeaders generates proper HTML', function(){


        append = sinon.stub($.fn, "append");
        Form.createHeaders(['ABC', 'DEF']);

        assert.equal(append.callCount, 2);
        append.thisValues[0].selector.should.be.equal('#fields');
        append.thisValues[1].selector.should.be.equal('#fields');
        assert(append.calledWith('<th>ABC</th>'));
        assert(append.calledWith('<th>DEF</th>'));
        append.restore();
    })
})