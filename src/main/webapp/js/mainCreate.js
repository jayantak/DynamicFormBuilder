requirejs.config({
    baseUrl: '../libraries',
    paths:{
        jquery: 'jquery.min',
        createForm: '../js/createForm',
        createFormAdd: '../js/createFormAdd'
    },
    shim: {
        jQuery:{
            exports: '$'
        },
        createForm: {
            deps: ['jquery']
        },
        createFormAdd: {
            deps: ['jquery']
        }
    }
});

requirejs(["../js/createForm"]);
