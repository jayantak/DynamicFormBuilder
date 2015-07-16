requirejs.config({
    baseUrl: '../../libraries',
    paths:{
        jquery: 'jquery.min',
        FormSubmitted: '../js/FormSubmitted/FormSubmitted',
        TableAdd: '../js/FormSubmitted/TableAdd'
    },
    shim: {
        jQuery:{
            exports: '$'
        },
        FormSubmitted: {
            deps: ['jquery']
        },
        TableAdd: {
            deps: ['jquery']
        }
    }
});

requirejs(["../js/FormSubmitted/FormSubmitted"]);
