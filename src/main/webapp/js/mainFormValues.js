requirejs.config({
    baseUrl: '../libraries',
    paths:{
        jquery: 'jquery.min',
        formValues: '../js/FormValues/formValues',
        formValuesAdd: '../js/FormValues/formValuesAdd'
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

requirejs(["../js/FormValues/formValues"]);
