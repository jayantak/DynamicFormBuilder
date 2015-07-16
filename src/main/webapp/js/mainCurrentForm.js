requirejs.config({
    baseUrl: '../libraries',
    paths:{
        jquery: 'jquery.min',
        currentFormJSON: '../js/currentFormJSON',
        currentForm: '../js/currentForm'
    },
    shim: {
        jQuery:{
            exports: '$'
        },
        currentForm: {
            deps: ['jquery']
        },
        currentFormJSON: {
            deps: ['jquery']
        }
    }
});

requirejs(["../js/currentFormJSON"]);
