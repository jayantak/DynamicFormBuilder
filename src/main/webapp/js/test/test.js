/**
 * Created by sudeepna on 12/06/15.
 */
var assert = require('assert');
var forms = require('../addElement.js');

describe('Forms', function()
{

    it('Element should be added', function()
    {
        var items1 = new Array();
        forms.addElement(items1,"A","B");
        console.log(items1[0]);
        assert(items1[0],"<p>A:    <input id=\"A\"type=\"B\"></input></p>")

    })

});
