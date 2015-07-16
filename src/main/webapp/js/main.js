requirejs.config({
    baseUrl: '../libraries',
    paths:{
        jquery: 'jquery.min',
        FormAdd: '../js/FormAdd',
        HomePage: '../js/HomePage',
        HomePageFunctions: '../js/HomePageFunctions'
    },
    shim: {
        jQuery:{
            exports: '$'
        },
        FormAdd: {
            deps: ['jquery']
        },
        HomePage: {
            deps: ['jquery']
        },
        HomePageFunctions: {
            deps: ['jquery']
        }
    }
});

requirejs(["../js/HomePage"]);