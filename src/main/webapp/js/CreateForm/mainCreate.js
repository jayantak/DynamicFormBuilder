requirejs.config({
    baseUrl: '../../libraries',
    paths:{
        jquery: 'jquery.min',
        createForm: '../js/CreateForm/createForm',
        createFormAdd: '../js/CreateForm/createFormAdd'
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

requirejs(["../js/CreateForm/createForm"]);
