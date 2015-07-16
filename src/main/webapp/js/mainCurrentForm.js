requirejs.config({
    baseUrl: '../libraries',
    paths:{
        jquery: 'jquery.min',
        currentFormJSON: '../js/CurrentForm/currentFormJSON',
        currentForm: '../js/CurrentForm/currentForm'
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

requirejs(["../js/CurrentForm/currentFormJSON"]);
