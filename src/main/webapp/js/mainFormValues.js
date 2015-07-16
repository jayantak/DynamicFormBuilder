requirejs.config({
    baseUrl: '../libraries',
    paths:{
        jquery: 'jquery.min',
        formValues: '../js/formValues',
        formValuesAdd: '../js/formValuesAdd'
    },
    shim: {
        jQuery:{
            exports: '$'
        },
        formValues: {
            deps: ['jquery']
        },
        formValuesAdd: {
            deps: ['jquery']
        }
    }
});

requirejs(["../js/formValues"]);
