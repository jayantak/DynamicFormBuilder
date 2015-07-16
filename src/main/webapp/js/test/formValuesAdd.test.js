define(['jquery', 'formValuesAdd'], function($, Form){
    it('createHeaders should generate proper HTML', function(){


        append = sinon.stub($.fn, "append");
        Form.createHeaders(['ABC', 'DEF']);

        assert.equal(append.callCount, 2);
        append.thisValues[0].selector.should.be.equal('#fields');
        append.thisValues[1].selector.should.be.equal('#fields');
        assert(append.calledWith('<th>ABC</th>'));
        assert(append.calledWith('<th>DEF</th>'));
        append.restore();
    })

    it('createTable should generate proper HTML', function(){

        append = sinon.stub($.fn, "append");
        Form.createTable([["ABC", "123"], ["DEF", "456"]]);

        assert.equal(append.callCount, 6);
        assert(append.calledWith('<td>123</td>'));
        assert(append.calledWith('<td>ABC</td>'));
        assert(append.calledWith('<td>DEF</td>'));
        assert(append.calledWith('<td>456</td>'));
        assert(append.calledWith('<tr id = "row0"></tr>'));
        assert(append.calledWith('<tr id = "row1"></tr>'));
        append.thisValues[0].selector.should.be.equal('#data');
        append.thisValues[1].selector.should.be.equal('#row0');
        append.thisValues[2].selector.should.be.equal('#row0');
        append.thisValues[3].selector.should.be.equal('#data');
        append.thisValues[4].selector.should.be.equal('#row1');
        append.thisValues[5].selector.should.be.equal('#row1');
    });
})