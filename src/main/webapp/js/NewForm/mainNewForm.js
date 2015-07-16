requirejs.config({
    baseUrl: '../../libraries',
    paths:{
        jquery: 'jquery.min',
        FormAdd: '../js/NewForm/FormAdd',
        HomePage: '../js/NewForm/HomePage',
        HomePageFunctions: '../js/NewForm/HomePageFunctions',
        owlCarousel: 'owl.carousel'
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
        },
        owlCarousel: {
            deps: ['jquery']
        }
    }
});

requirejs(["../js/NewForm/HomePage"]);