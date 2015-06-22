define(function(){

    var isEquivalent = function(a, b) {
        var aProps = Object.getOwnPropertyNames(a);
        var bProps = Object.getOwnPropertyNames(b);

        if (aProps.length != bProps.length) {
            return false;
        }

        for (var i = 0; i < aProps.length; i++) {
            var propName = aProps[i];

            if(typeof(a[propName])=="object"){
                if(isEquivalent(a[propName], b[propName])==false){
                    return false;
                }
            }
            else if (a[propName] !== b[propName]) {
                return false;
            }

        }

        return true;
    };

    return{
        isEquivalent:isEquivalent
    }
});