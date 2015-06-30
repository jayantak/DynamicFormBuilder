define(["TableAdd", "ObjectCompare"], function(table, objCompare){

    it('Table element must be added', function(){

        var items = [];

        table.addElement(items, "TESTVAL1", "TESTVAL2");

        assert.equal(items[0], '<tr><td>TESTVAL1</td><td>TESTVAL2</td></tr>');
    });
});